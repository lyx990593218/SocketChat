package com.lyx.ChatServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChatInfosPersistent
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 12:53
 * @Version 1.0
 */
public class ChatInfosPersistent {

    private static List<ChatInfo> chatInfos = new ArrayList<ChatInfo>();

    public static List<ChatInfo> getChatInfos() {
        return chatInfos;
    }

    public static void setChatInfo(ChatInfo chatInfo) {
        ChatInfosPersistent.chatInfos.add(chatInfo);
    }
}
