package com.hwq.service;

import com.hwq.spring.BeanPostProcessor;
import com.hwq.spring.Component;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 22:22
 * @Description:
 **/
@Component
public class HwqBeanPostProcessor implements BeanPostProcessor {
    @Override
    public void postProcessBeforeInitialization() {

    }

    @Override
    public void beforeProcessBeforeInitialization() {
        System.out.println("开始初始化啦");
    }
}
