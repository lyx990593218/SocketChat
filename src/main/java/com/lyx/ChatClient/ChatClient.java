package com.lyx.ChatClient;

import com.lyx.ChatServer.ChatInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * @ClassName ChatClient
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 11:12
 * @Version 1.0
 */
@Slf4j
public class ChatClient extends TimerTask {

    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 6666;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    private String fromUser;
    private String targetUser;

    public ChatClient(String fromUser, String targetUser) {
        this.fromUser = fromUser;
        this.targetUser = targetUser;
        receiveMsg();
        //sendMsg(targetUser);
    }

    @Override
    public void run() {
        //Date date = new Date(this.scheduledExecutionTime());
        //System.out.println("本次执行该线程的时间为：" + date);
        receiveMsg();
    }

    public void sendMsg(String content) {
        Socket socket = null;

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(fromUser + "%@SENDMSG@%" + targetUser + "%@SENDMSG@%" + content);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println((targetUser + " ：" + in.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {
                out.close();
                out = null;

            }

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }
    }

    private void receiveMsg() {
        Socket socket = null;

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(fromUser + "%@CONFIRM_USER@%" + targetUser);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //System.out.println((targetUser + " ：" + in.readLine()));
            String returnContent = in.readLine();
            if (returnContent != null && !"".equals(returnContent)){
                System.out.println(targetUser + ":");
                System.out.println(returnContent.split("@")[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {
                out.close();
                out = null;

            }

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }
    }

    private void receiveObjectMsg() {
        Socket socket = null;

        BufferedReader in = null;

        PrintWriter out = null;

        ObjectOutputStream write = null;
        ObjectInputStream inl = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(fromUser + "%@CONFIRM_USER@%" + targetUser);

            inl = new ObjectInputStream(socket.getInputStream());

            ChatInfo inChatInfo = (ChatInfo) inl.readObject();

            if (inChatInfo != null){
                if (targetUser.equals(inChatInfo.getFromUser())){
                    System.out.println(targetUser + ":");
                    System.out.println(inChatInfo.getChatContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {
                out.close();
                out = null;

            }

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }
    }


    public static void send(String content) {
        send(DEFAULT_SERVER_PORT, content);
    }

    public static void send(int port, String content) {
        System.out.println(("聊天内容为：" + content));
        Socket socket = null;

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(content);
            //System.out.println(("返回聊天内容为：" + in.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {
                out.close();
                out = null;

            }

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }
    }

}
