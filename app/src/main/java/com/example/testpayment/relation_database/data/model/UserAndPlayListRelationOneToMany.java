package com.example.testpayment.relation_database.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.firebase.ui.auth.data.model.User;

import java.util.List;

public class UserAndPlayListRelationOneToMany {
    @Embedded
    private UserModel userModel;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "user_id_play_list"
    )
    private List<PlayListModel>playListModelList;

    @Override
    public String toString() {
        return "UserAndPlayListRelationOneToMany{" +
                "userModel=" + userModel +
                ", playListModelList=" + playListModelList +
                '}';
    }

    public UserAndPlayListRelationOneToMany(UserModel userModel, List<PlayListModel> playListModelList) {
        this.userModel = userModel;
        this.playListModelList = playListModelList;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<PlayListModel> getPlayListModelList() {
        return playListModelList;
    }

    public void setPlayListModelList(List<PlayListModel> playListModelList) {
        this.playListModelList = playListModelList;
    }
}
