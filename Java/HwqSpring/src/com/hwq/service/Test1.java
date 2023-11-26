package com.hwq.service;

import com.hwq.spring.HwqApplicationContext;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 16:14
 * @Description:
 **/
public class Test1 {
    public static void main(String[] args) {
        HwqApplicationContext hwqApplicationContext = new HwqApplicationContext(AppConfig.class);
        System.out.println(hwqApplicationContext.getBean("userService"));
        System.out.println(hwqApplicationContext.getBean("orderService"));
    }
}
