package com.example.testpayment.pararlel;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testpayment.R;
import com.example.testpayment.chat.model.TokkenModel;
import com.tbruyelle.rxpermissions3.RxPermissions;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;


public class ParalelActivity extends AppCompatActivity {
    Thread funi_thread, funi2_thread;
    boolean start = true;
    private RxPermissions rxPermissions;
    private ArrayList<TokkenModel>arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paralel);
        rxPermissions = new RxPermissions(this);
        arrayList.add(new TokkenModel("ezzat1"));
        arrayList.add(new TokkenModel("ezzat2"));
        arrayList.add(new TokkenModel("ezzat3"));
        arrayList.add(new TokkenModel("ezzat4"));
        arrayList.add(new TokkenModel("ezzat5"));
        arrayList.add(new TokkenModel("ezzat6"));



//        fun1();

//        fun2();
        requedtPemissionRx();

      // rx1();

        rx2();

    }

    private void rx2() {

       Observable<String> observable1= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                for (int i=0;i<20;i++)
                { emitter.onNext(i+"");
                    Thread.sleep(500);
                    Log.d("aaaaaaaaa", "subscribe: "+i);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());


        Observable<String> observable2= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                for (int i=20;i<40;i++)
                {
                    emitter.onNext(i+"");
                    Thread.sleep(500);
                    Log.d("aaaaaaaaa", "subscribe2: "+i);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {

            @Override
            public String apply(String s, String s2) throws Throwable {

                Log.d("ccccccccccc", "apply: "+s+"     "+s2);
                return s+"     "+s2;
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe();


    }


    private void rx1() {

        Observable.fromPublisher(new Publisher<TokkenModel>() {

            @Override
            public void subscribe(Subscriber<? super TokkenModel> s) {
                for (TokkenModel model:arrayList)
                {

                    s.onNext(model);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).map(new Function<TokkenModel, String>() {
            @Override
            public String apply(TokkenModel tokkenModel) throws Throwable {
                return tokkenModel.getTokken()+" i swin";
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String tokkenModel) {
                Log.d("aaaaaa", "onNext: "+tokkenModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void startsomework() {
        for (int i=0;i<10;i++)
        {
            Log.d("aaaaaaaa", "onFor: "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void requedtPemissionRx() {
        rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(val ->
                {
                    if (val.granted) {
                        Log.d("ttttttt", "requedtPemissionRx: yes");
                    } else {
                        Log.d("ttttttt", "requedtPemissionRx: no");
                    }

                });


    }

    private void fun1() {
        funi_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    if (start) {
                        Log.d("tttttttttt", "fun1: " + (i + 1));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });


//        try {
//            funi_thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        funi_thread.start();

    }

    private void fun2() {

        funi2_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    Log.d("tttttttttt", "fun2: " + (i + 1));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        funi2_thread.start();
        try {
            funi2_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void pause(View view) {
        funi_thread.interrupt();
    }

    public void resume(View view) {
        start = true;

    }
}