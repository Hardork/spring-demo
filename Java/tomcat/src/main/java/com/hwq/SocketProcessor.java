package com.hwq;

import com.hwq.request.Request;
import com.hwq.response.HwqResponse;
import com.hwq.servlet.HwqServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author:HWQ
 * @DateTime:2023/11/22 10:50
 * @Description: Socket处理类
 **/
public class SocketProcessor implements Runnable{
    private Socket socket;

    public SocketProcessor(Socket socket) {
        this.socket = socket;
    }

    // 处理socket
    /**
     * 处理socket，从socket中获取信息
     * @param socket
     */
    private void processSocket(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);

            // 获取请求方法
            int pos = 0;
            int begin = 0, end = 0;
            for(; pos < bytes.length; pos++,end++) {
                if (bytes[pos] == ' ') break;
            }

            // 获取方法
            StringBuilder method = new StringBuilder();
            // 拼接字符
            for(; begin < end; begin++) {
                method.append((char) bytes[begin]);
            }
            System.out.println(method);
            pos++;
            end++;
            begin++;

            // 获取url
            for(; pos < bytes.length; pos++,end++) {
                if (bytes[pos] == ' ') break;
            }
            StringBuilder url = new StringBuilder();
            // 拼接字符
            for(; begin < end; begin++) {
                url.append((char) bytes[begin]);
            }
            System.out.println(url);
            pos++;
            end++;
            begin++;

            // 获取方法
            for(; pos < bytes.length; pos++,end++) {
                if (bytes[pos] == '\r') break;
            }
            StringBuilder protocol = new StringBuilder();
            // 拼接字符
            for(; begin < end; begin++) {
                protocol.append((char) bytes[begin]);
            }
            System.out.println(protocol);

            // 自定义request和response
            Request request = new Request(method.toString(), url.toString(), protocol.toString(), socket);
            HwqResponse hwqResponse = new HwqResponse(request);
            HwqServlet hwqServlet = new HwqServlet();

            // 匹配Servlet
            // 帮我们去匹配是调用doGet还是doPost
            hwqServlet.service(request, hwqResponse);
            // 业务结束，给客户端返回响应体
            hwqResponse.complete();

        } catch (IOException | ServletException e) {
            // 处理异常，比如返回500 ...
//            new HwqResponse(request);
        }

    }

    @Override
    public void run() {
        processSocket(socket);
    }
}
