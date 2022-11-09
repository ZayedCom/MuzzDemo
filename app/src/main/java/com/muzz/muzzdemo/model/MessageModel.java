package com.muzz.muzzdemo.model;

public class MessageModel {

    String userID;
    String unix;
    String messageText;

    boolean isReceived = false;

    public MessageModel(String userID, String unix, String messageText, boolean isReceived) {
        this.userID = userID;
        this.unix = unix;
        this.messageText = messageText;
        this.isReceived = isReceived;
    }

    public String getUserID() {
        return userID;
    }

    public String getUnix() {
        return unix;
    }

    public String getMessageText() {
        return messageText;
    }

    public boolean isReceived() {
        return isReceived;
    }
}
