package com.example.testpayment.relation_database.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndLibraryRelationModel {
    @Override
    public String toString() {
        return "UserAndLibraryRelationModel{" +
                "userModel=" + userModel +
                ", liberaryModel=" + liberaryModel +
                '}';
    }

    @Embedded
    private UserModel userModel;

    @Relation(
            parentColumn = "user_id"
            ,entityColumn = "user_id_library"
            ,entity = LiberaryModel.class
    )
    private LiberaryModel liberaryModel;

    public UserAndLibraryRelationModel(UserModel userModel, LiberaryModel liberaryModel) {
        this.userModel = userModel;
        this.liberaryModel = liberaryModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public LiberaryModel getLiberaryModel() {
        return liberaryModel;
    }

    public void setLiberaryModel(LiberaryModel liberaryModel) {
        this.liberaryModel = liberaryModel;
    }
}
