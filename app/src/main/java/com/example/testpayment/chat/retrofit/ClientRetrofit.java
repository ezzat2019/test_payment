package com.example.testpayment.chat.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {
    private static Retrofit mInstance=null;
    public  synchronized static  Retrofit getRetrofit(String url)
    {
        if (mInstance==null)
        {
            mInstance=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return mInstance;
    }
}
