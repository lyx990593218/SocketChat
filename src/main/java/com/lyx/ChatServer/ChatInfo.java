package com.lyx.ChatServer;

import java.util.Date;

/**
 * @ClassName ChatInfo
 * @Descrition TODO()
 * @Author LYX
 * @Date 2018-11-03 12:28
 * @Version 1.0
 */
public class ChatInfo {
    private String fromUser;
    private String targetUser;
    private String chatContent;
    private Date timeStamp;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
