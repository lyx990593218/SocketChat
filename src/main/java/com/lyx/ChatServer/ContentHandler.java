package com.lyx.ChatServer;

import java.util.*;

/**
 * @ClassName ContentHandler
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 12:25
 * @Version 1.0
 */
public class ContentHandler {

    private String receiveMsg = "";

    public ContentHandler(String receiveMsg) {
        this.receiveMsg = receiveMsg;
        receiveAndSetChatInfo();
    }

    public List<String> queryAndSendAllMsgToUser(){
        List<String> SERVER_SENDMSG_FROM = new ArrayList<>();
        if (receiveMsg.contains("%@CONFIRM_USER@%")){
            String[] receiveMsgs = receiveMsg.split("%@CONFIRM_USER@%");
            String fromUser = receiveMsgs[0];
            for (ChatInfo chatInfo : ChatInfosPersistent.getChatInfos()){
                if (fromUser.equals(chatInfo.getTargetUser())){
                    SERVER_SENDMSG_FROM.add(chatInfo.getChatContent() + "SERVER_SENDMSG_FROM@" + chatInfo.getFromUser());
                }
            }
        }
        return SERVER_SENDMSG_FROM;
    }

    public List<String> queryAndSendMsgFromToUser(){
        List<String> SERVER_SENDMSG_FROM = new ArrayList<>();
        if (receiveMsg.contains("%@CONFIRM_USER@%")){
            String[] receiveMsgs = receiveMsg.split("%@CONFIRM_USER@%");
            String fromUser = receiveMsgs[0];
            String targetUser = receiveMsgs[1];

            System.out.println("CONFIRM_USER");

            SERVER_SENDMSG_FROM = getMsg(fromUser, targetUser);
        }else if (receiveMsg.contains("%@SENDMSG@%")){

            System.out.println("SENDMSG");

            String[] receiveMsgs = receiveMsg.split("%@SENDMSG@%");
            String fromUser = receiveMsgs[0];
            String targetUser = receiveMsgs[1];
            String chatContent = receiveMsgs[2];

            //SERVER_SENDMSG_FROM = getMsg(fromUser, targetUser);
            System.out.println("SENDMSG:" + SERVER_SENDMSG_FROM.toString());

        }
        return SERVER_SENDMSG_FROM;
    }

    public void receiveAndSetChatInfo(){
        //System.out.println("ContentHandler:" + receiveMsg);
        if (receiveMsg.contains("%@SENDMSG@%")){
            String[] receiveMsgs = receiveMsg.split("%@SENDMSG@%");
            String fromUser = receiveMsgs[0];
            String targetUser = receiveMsgs[1];
            String chatContent = receiveMsgs[2];

            ChatInfo chatInfo = new ChatInfo();
            chatInfo.setFromUser(fromUser);
            chatInfo.setTargetUser(targetUser);
            chatInfo.setChatContent(chatContent);
            chatInfo.setTimeStamp(new Date());

            ChatInfosPersistent.setChatInfo(chatInfo);
        }
    }

    public List<String> getMsg(String fromUser, String targetUser){
        List<String> SERVER_SENDMSG_FROM = new ArrayList<>();
        Iterator<ChatInfo> it = ChatInfosPersistent.getChatInfos().iterator();
        while(it.hasNext()){
            ChatInfo chatInfo = it.next();
            if (fromUser.equals(chatInfo.getTargetUser()) && targetUser.equals(chatInfo.getFromUser())){
                SERVER_SENDMSG_FROM.add(chatInfo.getChatContent() + "@SERVER_SENDMSG_FROM@" + chatInfo.getFromUser());

                it.remove();//remove the current item
            }
        }
        return SERVER_SENDMSG_FROM;
    }
}
