package com.example.testpayment.chat.model;

public class MessageModel {
    private String messgae_content;
    private String sender;
    private String Reciver;
    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "messgae_content='" + messgae_content + '\'' +
                ", sender='" + sender + '\'' +
                ", Reciver='" + Reciver + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    public MessageModel() {
    }

    public String getMessgae_content() {
        return messgae_content;
    }

    public void setMessgae_content(String messgae_content) {
        this.messgae_content = messgae_content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return Reciver;
    }

    public void setReciver(String reciver) {
        Reciver = reciver;
    }

    public MessageModel(String messgae_content, String sender, String reciver) {
        this.messgae_content = messgae_content;
        this.sender = sender;
        Reciver = reciver;
    }
}
