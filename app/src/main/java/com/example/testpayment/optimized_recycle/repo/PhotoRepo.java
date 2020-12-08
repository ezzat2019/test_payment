package com.example.testpayment.optimized_recycle.repo;

import com.example.testpayment.optimized_recycle.api.PhotoApi;
import com.example.testpayment.optimized_recycle.model.PhotoModel;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoRepo {
    private Retrofit retrofit;
    private PhotoApi photoApi;

    public PhotoRepo() {
        retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        photoApi=retrofit.create(PhotoApi.class);
    }
    public Observable<List<PhotoModel>> getPhotoes()
    {
        return photoApi.getPhoto();
    }
}
