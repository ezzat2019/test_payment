package com.example.testpayment.relation_database.uitls;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testpayment.relation_database.data.api.MusicApi;
import com.example.testpayment.relation_database.data.database.MainDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class Providers {
    @Provides
    @Singleton
    public MainDataBase getDataBase(Application application)
    {
        return Room.databaseBuilder(application,
                MainDataBase.class,"music")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public MusicApi getMusic(MainDataBase mainDataBase)
    {
        return mainDataBase.getAPi();
    }


}
