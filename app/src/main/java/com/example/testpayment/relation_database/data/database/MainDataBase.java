package com.example.testpayment.relation_database.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.testpayment.relation_database.data.api.MusicApi;
import com.example.testpayment.relation_database.data.model.LiberaryModel;
import com.example.testpayment.relation_database.data.model.PlayListModel;
import com.example.testpayment.relation_database.data.model.UserModel;

@Database(entities = {UserModel.class, LiberaryModel.class, PlayListModel.class},version = 6,exportSchema = false)
public abstract class MainDataBase extends RoomDatabase {
    public abstract MusicApi getAPi();
}
