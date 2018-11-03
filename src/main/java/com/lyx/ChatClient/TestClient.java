package com.lyx.ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName TestClient
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 11:21
 * @Version 1.0
 */
public class TestClient {
    private String IP = "127.0.0.1";

    private Integer PORT = 6666;

    private String userName;

    private final AtomicBoolean STOP = new AtomicBoolean(false);



    public TestClient(String name,String targetUserName) {
        this.userName = name;
        startChat(targetUserName);
        sendMsg(targetUserName);
    }


    public void startChat(final String targetUserName) {
        // 先获取消息
        new Thread(()->{
            System.out.println(STOP.get());
            while(!STOP.get()) {
                BufferedReader reader = null;
                PrintWriter writer = null;
                Socket socket = null;
                try {
                    socket = new Socket(IP,PORT);
                    socket.getInputStream();
                    writer = new PrintWriter(socket.getOutputStream());
                    writer.write("query|" + this.userName + "|" + targetUserName + "|query");
                    writer.flush();
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = "";
                    while((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != writer){
                        writer.close();
                    }
                    if (null != reader) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null != socket) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMsg(String targetUserName) {
        String msgInput = new String();
        while (!"exit".equals(msgInput)) {
//            System.out.println("请输入聊天信息：");
//            Scanner scanner = new Scanner(System.in);
//            msgInput = scanner.nextLine();
            msgInput = new Random().nextInt() + "";
            System.out.println("sengMsg：" + msgInput);
            BufferedReader reader = null;
            PrintWriter writer = null;
            Socket socket = null;
            try {
                socket = new Socket(IP, PORT);
                socket.getInputStream();
                writer = new PrintWriter(socket.getOutputStream());
                writer.write("chat|" + this.userName + "|" + targetUserName + "|" + msgInput);
                writer.flush();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != socket) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
