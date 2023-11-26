package com.hwq.request;

import com.hwq.request.AbstractHttpServletRequest;

import java.net.Socket;

/**
 * @Author:HWQ
 * @DateTime:2023/11/22 11:54
 * @Description: 自定义Request类
 **/
public class Request extends AbstractHttpServletRequest {

    private String method;
    private String url;
    private String protocol;
    private Socket socket;

    public Request(String method, String url, String protocol, Socket socket) {
        this.method = method;
        this.url = url;
        this.protocol = protocol;
        this.socket = socket;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public StringBuffer getRequestURL() {
        return new StringBuffer(url);
    }
}
