package com.example.testpayment.chat.api;

import com.example.testpayment.chat.model.ResponseModel;
import com.example.testpayment.chat.model.SenderModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMAPI {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAgryuFsI:APA91bES2udCGeeyG0vI0oauc-J3qK_JdmfSIomdDOIapESa5kM9FW9hvOJ8AYgdSOym9pWXfiQYoOWr5Ekc1dSefsi3ijzaSzUHZy1dTwyQsvLUD0yg8ffcYwQvPcAF4f2JQol_Q8Kw"
            }
    )
    @POST("fcm/send")
    Call<ResponseModel> sendNotifiction(@Body SenderModel senderModel);
}
