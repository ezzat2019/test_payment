package com.example.testpayment.relation_database;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testpayment.R;
import com.example.testpayment.relation_database.data.model.PlayListModel;
import com.example.testpayment.relation_database.data.model.UserAndLibraryRelationModel;
import com.example.testpayment.relation_database.data.model.UserAndPlayListRelationOneToMany;
import com.example.testpayment.relation_database.data.model.UserModel;
import com.example.testpayment.relation_database.viewmodel.RelationDataBaseViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainDataBaseActivity extends AppCompatActivity {
    private RelationDataBaseViewModel viewModel;

    private ArrayList<PlayListModel> playListModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_base);

        viewModel = new ViewModelProvider(this).get(RelationDataBaseViewModel.class);

        viewModel.getUserAndLibrary().observe(this, new Observer<List<UserAndLibraryRelationModel>>() {
            @Override
            public void onChanged(List<UserAndLibraryRelationModel> userAndLibraryRelationModels) {
                if (!userAndLibraryRelationModels.isEmpty()) {
                    Log.d("ddddddddd", "onChanged: " + userAndLibraryRelationModels.toString());

                }
            }
        });

        viewModel.getUserAndPlayLists().observe(this, new Observer<List<UserAndPlayListRelationOneToMany>>() {
            @Override
            public void onChanged(List<UserAndPlayListRelationOneToMany> userAndPlayListRelationOneToManies) {
                if (!userAndPlayListRelationOneToManies.isEmpty()) {
                    Log.d("ddddddddd222222", "onChanged: " + userAndPlayListRelationOneToManies.toString());

                }
            }

        });

    }

    public void add(View view) {
        UserModel userModel = new UserModel(1, "ahmed");
//        LiberaryModel liberaryModel = new LiberaryModel( "Romantic", 2);
        viewModel.addUser(userModel);

        PlayListModel playListModel = new PlayListModel("rap", 1);
        viewModel.addPlayList(playListModel);
        PlayListModel playListModel1 = new PlayListModel("quran", 1);
        viewModel.addPlayList(playListModel1);


//        viewModel.addLibrary(liberaryModel);

    }


}