package com.lyx.ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChatServerHandler
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 11:10
 * @Version 1.0
 */
public class ChatServerHandler implements Runnable {
    private Socket socket;

    public ChatServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String content;
            int result;
            while (true) {
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                if ((content = in.readLine()) == null) break;
                System.out.println(("服务端收到信息：" + content));

                //对服务器收到的消息进行处理,数据持久化等
                ContentHandler contentHandler = new ContentHandler(content);
                //将连接此线程socket的用户对应的持久层消息发回
                List<String> SERVER_SENDMSG_FROM = contentHandler.queryAndSendMsgFromToUser();
                System.out.println("SERVER_SENDMSG_FROM.size();" + SERVER_SENDMSG_FROM.size());
                if (SERVER_SENDMSG_FROM.size() < 1){
                    out.println("");
                }else{
                    out.println(SERVER_SENDMSG_FROM.toString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println((e.getLocalizedMessage()));
        } finally {
            //一些必要的清理工作
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
