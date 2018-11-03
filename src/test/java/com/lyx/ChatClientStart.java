package com.lyx;

import com.lyx.ChatClient.ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;

/**
 * @ClassName ChatClientStart
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 11:56
 * @Version 1.0
 */
public class ChatClientStart {
    public static void main(String[] args) {
        final Random random = new Random(System.currentTimeMillis());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatClient chatClient = new ChatClient("lyx","fang");
                Timer timer = new Timer();
                timer.schedule(chatClient, 1000, 2000);
                while (true) {
                    //Scanner取得的输入以space, tab, enter 键为结束符，
                    //要想取得包含space在内的输入，可以用java.io.BufferedReader类来实现
                    //使用BufferedReader的readLine( )方法
                    //必须要处理java.io.IOException异常
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
                    //java.io.InputStreamReader继承了Reader类
                    String read = null;
                    System.out.print("输入数据：");
                    try {
                        read = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.out.println("输入数据："+read);
                    chatClient.sendMsg(read);
                    try {
                        Thread.sleep(random.nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
