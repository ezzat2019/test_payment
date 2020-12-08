package com.example.testpayment.chat.model;

public class SenderModel {
    private DataModel data;
    private String to;

    public SenderModel(DataModel data, String to) {
        this.data = data;
        this.to = to;
    }

    public DataModel getDataModel() {
        return data;
    }

    public void setDataModel(DataModel dataModel) {
        this.data = dataModel;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public SenderModel() {
    }
}
