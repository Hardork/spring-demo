package com.hwq.service;

import com.hwq.spring.*;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 15:30
 * @Description:
 **/
@Component("userService")
@Scope("")
public class UserService implements BeanNameAware, InitializingBean, UserInterface {
//    @Autowired
//    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertySet() {

    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
