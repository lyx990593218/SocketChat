package com.lyx;

import com.lyx.ChatServer.ChatServer;

import java.io.IOException;

/**
 * @ClassName ChatServerStart
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 11:55
 * @Version 1.0
 */
public class ChatServerStart {
    public static void main(String[] args) {
        //运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ChatServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
