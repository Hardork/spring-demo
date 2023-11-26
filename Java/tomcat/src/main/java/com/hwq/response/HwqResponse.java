
package com.hwq.response;

import com.hwq.request.Request;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HWQ
 * @DateTime:2023/11/22 17:22
 * @Description: 请求响应类
 **/
public class HwqResponse extends AbstractHttpServletResponse {
    // 响应状态码
    private int status = 200;
    // 响应信息
    private String message = "ok";
    private byte SP = ' ';
    private byte CR = '\r';
    private byte LF = '\n';
    // 响应头
    private Map<String, String> headers = new HashMap<>();
    // 用处：获取请求信息
    private Request request;
    // 用处：往socket写响应信息
    private OutputStream socketOutputStream;
    // 存储当前类型的响应体
    private AbstractServletOutputStream abstractServletOutputStream = new AbstractServletOutputStream();



    public HwqResponse(Request request) {
        this.request = request;
        try {
            this.socketOutputStream = request.getSocket().getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setStatus(int sc, String sm) {
        this.status = sc;
        this.message = sm;
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void addCookie(Cookie cookie) {
        super.addCookie(cookie);
    }

    @Override
    public AbstractServletOutputStream getOutputStream() throws IOException {
        return abstractServletOutputStream;
    }


    public void complete() throws IOException {
        // 发送响应
        sendResponseLine();
        sendResponseHeader();
        sendResponseBody();
    }

    // 设置响应体
    private void sendResponseBody() throws IOException {

        socketOutputStream.write(getOutputStream().getBytes());
    }

    // 设置响应头
    private void sendResponseHeader() throws IOException {
        if (!headers.containsKey("Content-Length")) {
            // 设置返回字节数
            addHeader("Content-Length", String.valueOf(getOutputStream().getPos()));
        }

        if (!headers.containsKey("Content-Type")) {
            // 默认返回纯文本
            addHeader("Content-Type", "text/plain;charset=utf-8");
        }

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            socketOutputStream.write(key.getBytes());
            socketOutputStream.write(":".getBytes());
            socketOutputStream.write(value.getBytes());
            socketOutputStream.write(CR);
            socketOutputStream.write(LF);
        }
        socketOutputStream.write(CR);
        socketOutputStream.write(LF);
    }

    // 设置响应行
    private void sendResponseLine() throws IOException {
        socketOutputStream.write(request.getProtocol().getBytes());
        socketOutputStream.write(SP);
        socketOutputStream.write(status);
        socketOutputStream.write(SP);
        socketOutputStream.write(message.getBytes());
        socketOutputStream.write(CR);
        socketOutputStream.write(LF);
    }


}
