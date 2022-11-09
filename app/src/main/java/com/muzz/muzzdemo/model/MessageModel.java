package com.muzz.muzzdemo.model;

public class MessageModel {

    String userID;
    String messageText;

    long unix;

    boolean isReceived;

    public MessageModel(String userID, String messageText, long unix, int isReceived) {
        this.userID = userID;
        this.messageText = messageText;
        this.unix = unix;

        //Converting the int type value of the model to boolean because SQLite can't handel boolean type
        this.isReceived = isReceived >= 1;
    }

    public String getUserID() {
        return userID;
    }

    public String getMessageText() {
        return messageText;
    }

    public long getUnix() {
        return unix;
    }

    public boolean isReceived() {
        return isReceived;
    }
}
