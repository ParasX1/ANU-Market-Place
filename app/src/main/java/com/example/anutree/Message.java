package com.example.anutree;

public class Message {

    private String sender;
    private String receiver;
    private String contentOfMsg;

    public Message(String sender, String receiver, String contentOfMsg) {
        this.sender = sender;
        this.receiver = receiver;
        this.contentOfMsg = contentOfMsg;
    }

    public Message() { }

    public String getContentOfMsg() {
        return contentOfMsg;
    }

    public void setContentOfMsg(String contentOfMsg) {
        this.contentOfMsg = contentOfMsg;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
