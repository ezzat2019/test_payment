package com.example.testpayment.relation_database.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "library", foreignKeys = @ForeignKey(entity = UserModel.class
        , parentColumns = "user_id", childColumns = "user_id_library", onDelete = ForeignKey.CASCADE))
public class LiberaryModel {
    @Override
    public String toString() {
        return "LiberaryModel{" +
                "library_id=" + library_id +
                ", library_name='" + library_name + '\'' +
                ", user_id_library=" + user_id_library +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "library_id")
    private long library_id;

    @ColumnInfo(name = "name")
    private String library_name;
    @ColumnInfo(name = "user_id_library")
    private long user_id_library;

    public LiberaryModel(@NonNull long library_id, String library_name, long user_id_library) {
        this.library_id = library_id;
        this.library_name = library_name;
        this.user_id_library = user_id_library;
    }

    @Ignore
    public LiberaryModel(String library_name, long user_id_library) {
        this.library_name = library_name;
        this.user_id_library = user_id_library;
    }

    public long getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(long library_id) {
        this.library_id = library_id;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public long getUser_id_library() {
        return user_id_library;
    }

    public void setUser_id_library(long user_id_library) {
        this.user_id_library = user_id_library;
    }
}
