package com.example.testpayment.optimized_recycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testpayment.R;
import com.example.testpayment.optimized_recycle.adapters.OptimizedAdapter;
import com.example.testpayment.optimized_recycle.model.PhotoModel;
import com.example.testpayment.optimized_recycle.repo.PhotoRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OptimizedRecycleActivity extends AppCompatActivity {

    private RecyclerView rec_photoes;

    // var
    private PhotoRepo photoRepo;
    private OptimizedAdapter adapter;
    private List<PhotoModel> photoModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimized_recycle);

        rec_photoes = findViewById(R.id.rec_photoes);
        adapter = new OptimizedAdapter();
        photoRepo = new PhotoRepo();
//        rec_photoes.setNestedScrollingEnabled(false);
//        rec_photoes.setHasFixedSize(true);
        rec_photoes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rec_photoes.setAdapter(adapter);


        photoRepo.getPhotoes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<PhotoModel> photoModels) {


                            photoModelList = photoModels;
                        adapter.setPhotoModels(photoModelList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}