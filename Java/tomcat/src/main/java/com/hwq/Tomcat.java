package com.hwq;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:HWQ
 * @DateTime:2023/11/22 10:42
 * @Description: Tomcat启动类
 **/
public class Tomcat {

    public void start() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ServerSocket serverSocket = new ServerSocket(8080);
        // 为什么要死循环？如果没有循环，那这个tomcat服务器只能处理一次主线程就退出了
        while (true) {
            // 阻塞，监听端口
            Socket socket = serverSocket.accept();
            // 为什么要用线程池处理socket？如果不使用线程池，那么在当前线程处理socket的时候其它socket无法被处理，也就是没有并发能力
            executorService.execute(new SocketProcessor(socket));
        }
    }


    public static void main(String[] args) throws IOException {
        Tomcat tomcat = new Tomcat();
        tomcat.start();

    }


}
