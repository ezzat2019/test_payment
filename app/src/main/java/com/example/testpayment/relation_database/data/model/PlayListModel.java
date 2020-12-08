package com.example.testpayment.relation_database.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "play_list", foreignKeys = @ForeignKey(
        entity = UserModel.class
        , parentColumns = "user_id"
        , childColumns = "user_id_play_list"
        , onDelete = ForeignKey.CASCADE
))
public class PlayListModel {
    public PlayListModel(long play_list_id, String play_list_name, long user_id_play_list) {
        this.play_list_id = play_list_id;
        this.play_list_name = play_list_name;
        this.user_id_play_list = user_id_play_list;
    }

    @Override
    public String toString() {
        return "PlayListModel{" +
                "play_list_id=" + play_list_id +
                ", play_list_name='" + play_list_name + '\'' +
                ", user_id_play_list=" + user_id_play_list +
                '}';
    }


    public long getPlay_list_id() {
        return play_list_id;
    }

    public void setPlay_list_id(long play_list_id) {
        this.play_list_id = play_list_id;
    }

    public String getPlay_list_name() {
        return play_list_name;
    }

    public void setPlay_list_name(String play_list_name) {
        this.play_list_name = play_list_name;
    }

    public long getUser_id_play_list() {
        return user_id_play_list;
    }

    public void setUser_id_play_list(long user_id_play_list) {
        this.user_id_play_list = user_id_play_list;
    }

    @Ignore
    public PlayListModel(String play_list_name, long user_id_play_list) {
        this.play_list_name = play_list_name;
        this.user_id_play_list = user_id_play_list;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "play_list_id")
    private long play_list_id;
    @ColumnInfo(name = "play_list_name")
    private String play_list_name;
    @ColumnInfo(name = "user_id_play_list")
    private long user_id_play_list;
}
