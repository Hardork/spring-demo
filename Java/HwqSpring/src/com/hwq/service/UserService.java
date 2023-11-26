package com.hwq.service;

import com.hwq.spring.Autowired;
import com.hwq.spring.BeanNameAware;
import com.hwq.spring.Component;
import com.hwq.spring.Scope;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 15:30
 * @Description:
 **/
@Component("userService")
@Scope("prototype")
public class UserService implements BeanNameAware {
    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = beanName;
    }
}
