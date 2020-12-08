package com.example.testpayment.optimized_recycle.api;

import com.example.testpayment.optimized_recycle.model.PhotoModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PhotoApi {
    @GET("photos")
    Observable<List<PhotoModel>> getPhoto();

}
