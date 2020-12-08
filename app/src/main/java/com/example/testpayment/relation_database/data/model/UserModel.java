package com.example.testpayment.relation_database.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    private long user_id;
    @ColumnInfo(name = "name")
    private String user_name;

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }

    public UserModel(long user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
