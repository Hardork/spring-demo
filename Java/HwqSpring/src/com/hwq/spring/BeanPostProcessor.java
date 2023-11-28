package com.hwq.spring;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 22:11
 * @Description:
 **/
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(String beanName, Object instance);

    Object beforeProcessBeforeInitialization(String beanName, Object instance);
}
