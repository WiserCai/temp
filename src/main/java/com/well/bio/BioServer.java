package com.well.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CYC
 * @Version 1.0.0
 * @Description 演示BIO，采用线程池机制
 **/
public class BioServer {

    public static void main(String[] args) throws IOException {

        //1.创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //2.如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器端启动！");

        while (true){
            System.out.println("等待客户端连接。。。。。。");

            //会阻塞，监听，等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //创建一个线程与之通讯
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //与客户端通讯
                    handler(socket);
                }
            });

        }


    }

    //编写一个handler方法与客户端通讯
    public static void handler(Socket socket){
        String name = Thread.currentThread().getName();
        System.out.println("线程信息 ID="+Thread.currentThread().getId() + "，线程名字："+name);

        try {
            //用来接收数据
            byte[] bytes = new byte[8024];

            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

            while (true){
                System.out.println("等待客户端发送消息。。。。。。。。");
                //每次读1024b，阻塞等待
                int read = inputStream.read(bytes);

                if (read != -1){
                    System.out.println("客户端发送的数据:"+new String(bytes,0,read)+",线程是："+name);

                }else {
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

}





























