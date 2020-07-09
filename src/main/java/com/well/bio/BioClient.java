package com.well.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author CYC
 * @Version 1.0.0
 * @Description
 **/
public class BioClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OutputStream outputStream = null;

        //1.创建一个客户端
        try {
            Socket socket = new Socket("127.0.0.1",6666);
            System.out.println("客户端启动");
            outputStream = socket.getOutputStream();
            System.out.println("客户端输入消息：");
            while (true){
                String s = scanner.nextLine();
                outputStream.write(s.getBytes());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
