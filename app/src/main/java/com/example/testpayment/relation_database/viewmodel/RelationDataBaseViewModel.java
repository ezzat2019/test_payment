package com.example.testpayment.relation_database.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.testpayment.relation_database.data.model.LiberaryModel;
import com.example.testpayment.relation_database.data.model.PlayListModel;
import com.example.testpayment.relation_database.data.model.UserAndLibraryRelationModel;
import com.example.testpayment.relation_database.data.model.UserAndPlayListRelationOneToMany;
import com.example.testpayment.relation_database.data.model.UserModel;
import com.example.testpayment.relation_database.data.repo.MusicRepo;

import java.util.List;

public class RelationDataBaseViewModel extends ViewModel {
    private MusicRepo musicRepo;

    @ViewModelInject
    public RelationDataBaseViewModel(MusicRepo musicRepo) {
        this.musicRepo = musicRepo;
    }

    public void addUser(UserModel userModel) {
        musicRepo.addUser(userModel);
    }

    public void addLibrary(LiberaryModel liberaryModel) {
        musicRepo.addLibrary(liberaryModel);
    }

    public void addPlayList(PlayListModel playListModel) {
        musicRepo.addPlayList(playListModel);
    }

    public LiveData<List<UserAndLibraryRelationModel>> getUserAndLibrary() {
        return musicRepo.getUserAndLibrary();
    }

    public LiveData<List<UserAndPlayListRelationOneToMany>> getUserAndPlayLists() {
        return musicRepo.getUserAndPlay();
    }
}
