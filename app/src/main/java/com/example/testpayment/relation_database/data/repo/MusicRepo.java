package com.example.testpayment.relation_database.data.repo;

import androidx.lifecycle.LiveData;

import com.example.testpayment.relation_database.data.api.MusicApi;
import com.example.testpayment.relation_database.data.model.LiberaryModel;
import com.example.testpayment.relation_database.data.model.PlayListModel;
import com.example.testpayment.relation_database.data.model.UserAndLibraryRelationModel;
import com.example.testpayment.relation_database.data.model.UserAndPlayListRelationOneToMany;
import com.example.testpayment.relation_database.data.model.UserModel;

import java.util.List;

import javax.inject.Inject;

public class MusicRepo {
    private MusicApi musicApi;

    @Inject
    public MusicRepo(MusicApi musicApi) {
        this.musicApi = musicApi;
    }

    public void addUser(UserModel userModel) {
        musicApi.setUser(userModel);
    }
    public void addLibrary(LiberaryModel liberaryModel) {
        musicApi.setLibrary(liberaryModel);
    }
    public void addPlayList(PlayListModel playListModel) {
        musicApi.setPlayList(playListModel);
    }
    public LiveData<List<UserAndLibraryRelationModel>> getUserAndLibrary() {
        return musicApi.getUserLibrary();
    }

    public LiveData<List<UserAndPlayListRelationOneToMany>> getUserAndPlay() {
        return musicApi.getUserPlayListOfUser();
    }
}
