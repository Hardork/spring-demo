package com.hwq.spring;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 22:11
 * @Description:
 **/
public interface BeanPostProcessor {
    void postProcessBeforeInitialization();

    void beforeProcessBeforeInitialization();
}
