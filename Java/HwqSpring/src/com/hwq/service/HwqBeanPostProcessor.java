package com.hwq.service;

import com.hwq.spring.BeanPostProcessor;
import com.hwq.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 22:22
 * @Description:
 **/
@Component
public class HwqBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object instance) {
        if (beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(HwqBeanPostProcessor.class.getClassLoader(),instance.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理对象执行中");
                    return method.invoke(instance, args);
                }
            });
            return proxyInstance;
        }
        return instance;
    }

    @Override
    public Object beforeProcessBeforeInitialization(String beanName, Object instance) {
        if (beanName.equals("userService")) {
            System.out.println("before userService");
        }
        return instance;
    }
}
