package com.example.testpayment.relation_database.data.api;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testpayment.relation_database.data.model.LiberaryModel;
import com.example.testpayment.relation_database.data.model.PlayListModel;
import com.example.testpayment.relation_database.data.model.UserAndLibraryRelationModel;
import com.example.testpayment.relation_database.data.model.UserAndPlayListRelationOneToMany;
import com.example.testpayment.relation_database.data.model.UserModel;

import java.util.List;

@Dao
public interface MusicApi {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setUser(UserModel user);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLibrary(LiberaryModel library);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setPlayList(PlayListModel playList);
    @Transaction
    @Query("select * from user")
    LiveData<List<UserAndLibraryRelationModel>> getUserLibrary();

    @Transaction
    @Query("select * from user")
    LiveData<List<UserAndPlayListRelationOneToMany>> getUserPlayListOfUser();
}
