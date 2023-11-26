package com.hwq.spring;


import java.io.File;
import java.net.URL;
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
                for (File f : files) {
                    // get all files in ComponentScan
                    String fileName = f.getAbsolutePath();
                    System.out.println("ComponentScan files:");
                    System.out.println(fileName);

                    if (fileName.endsWith(".class")) {
                        // 获取class类的相对路径
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace('\\', '.');

                        try { // create bean
                            Class<?> clazz = classLoader.loadClass(className);

                            if (clazz.isAnnotationPresent(Component.class)) { // having Component annotation
                                // get beanName
                                String beanName = clazz.getAnnotation(Component.class).value();

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
                        }

                    }


                }
            }

        }
    }

    public Object getBean(String name) {
        return new Object();
    }
}
