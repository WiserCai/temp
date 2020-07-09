package com.well.nio;

import java.nio.IntBuffer;

/**
 * @Author CYC
 * @Version 1.0.0
 * @Description 举例说明Buffer的使用
 **/
public class BasicBuffer {

    public static void main(String[] args) {

        //创建一个Buffer,大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向Buffer中存放数据
//        intBuffer.put(7);
//        intBuffer.put(8);
//        intBuffer.put(9);
//        intBuffer.put(10);
//        intBuffer.put(11);

        for (int i = 0; i < intBuffer.capacity(); i++) {

            intBuffer.put(i*2);

        }

        //将Buffer转换，读写切换
        intBuffer.flip();
        intBuffer.position(1); //指定获取的数据的下标
        System.out.println("position："+intBuffer.get());

        intBuffer.limit(3); //只能获取前3个数据
        while (intBuffer.hasRemaining()){

            //这个时候intBuffer.get()类似跌打器，每次get一次就会往下移动一个指针
            System.out.println(intBuffer.get());

        }

    }
}





















