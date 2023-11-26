package com.hwq.spring;

/**
 * @Author:HWQ
 * @DateTime:2023/11/26 17:27
 * @Description:
 **/
public class BeanDefinition {
    private Class type;
    private String scope; // 单例还是多例

    public Class getType() {
        return type;
    }

    public String getScope() {
        return scope;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
