package com.example.testpayment.chat.model;

public class DataModel {
    private String user,title,body,sendezzat;
    private int icon;

    public DataModel(String user, String title, String body, String sendezzat, int icon) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.sendezzat = sendezzat;
        this.icon = icon;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getsendezzat() {
        return sendezzat;
    }

    public void setsendezzat(String sendezzat) {
        this.sendezzat = sendezzat;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public DataModel() {
    }
}
