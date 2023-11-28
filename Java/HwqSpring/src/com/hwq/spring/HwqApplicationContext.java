package com.hwq.spring;


import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 16:15
 * @Description:
 **/
public class HwqApplicationContext {

    private Class configClass;

    // container use for storing bean
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionConcurrentHashMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonObject = new ConcurrentHashMap<>();
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    public HwqApplicationContext(Class appConfigClass) {
        this.configClass = appConfigClass;

        // 扫描配置
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan ComponentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = ComponentScanAnnotation.value(); // 扫描路径 com.hwq.service


            path = path.replace(".", "/"); // com/hwq/service

            ClassLoader classLoader = HwqApplicationContext.class.getClassLoader();
            // 获取当前注解所在的绝对路径
            URL resource = classLoader.getResource(path); // D:\Java\HwqSpring\out\production\HwqSpring\com\hwq\service
            File file = new File(resource.getFile());
            System.out.println(file);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                System.out.println("ComponentScan files:");
                for (File f : files) {
                    // get all files in ComponentScan
                    String fileName = f.getAbsolutePath();
                    System.out.println(fileName);
                    if (fileName.endsWith(".class")) {
                        // 获取class类的相对路径
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace('\\', '.');

                        try { // create bean

                            Class<?> clazz = classLoader.loadClass(className);



                            if (clazz.isAnnotationPresent(Component.class)) { // having Component annotation

                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) { // 添加代理对象
                                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(instance);
                                }

                                // get beanName
                                Component component = clazz.getAnnotation(Component.class);
                                String beanName = component.value();

                                if (beanName.equals("")) {
                                    // Component没有指定beanName的时候，自动分配一个beanName 例如：UserService -> userService
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }

                                // BeanDefinition
                                BeanDefinition beanDefinition = new BeanDefinition();

                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope annotation = clazz.getAnnotation(Scope.class);
                                    beanDefinition.setScope(annotation.value());
                                } else { // 默认单例
                                    beanDefinition.setScope("singleton");
                                }

                                beanDefinition.setType(clazz);
                                beanDefinitionConcurrentHashMap.put(beanName, beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }

        // 初始化bean对象
        for (String beanName : beanDefinitionConcurrentHashMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionConcurrentHashMap.get(beanName);

            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanDefinition, beanName);
                singletonObject.put(beanName, bean);
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition, String beanName) {
        Class type = beanDefinition.getType();
        try {
            Object instance = type.getConstructor().newInstance();

            // 注入依赖
            Field[] declaredFields = type.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 依赖名
                    String name = field.getName();
                    field.setAccessible(true);
                    field.set(instance, getBean(name));
                }
            }

            // 用户自定义beanName
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.beforeProcessBeforeInitialization(beanName, instance);
            }

            // 用户自定义初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertySet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取bean对象
     * @param name
     * @return
     */
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionConcurrentHashMap.get(name);
        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if (scope.equals("singleton")) {
                Object bean = singletonObject.get(name);
                if (bean == null) {
                    bean = createBean(beanDefinition, name);
                    singletonObject.put(name, bean);
                }
                return bean;
            } else {
                return createBean(beanDefinition, name);
            }
        }
    }
}
