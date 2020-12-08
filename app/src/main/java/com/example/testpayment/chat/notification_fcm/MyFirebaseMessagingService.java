package com.example.testpayment.chat.notification_fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.testpayment.R;
import com.example.testpayment.chat.ChatActivity;
import com.example.testpayment.chat.model.TokkenModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private SharedPreferences sharedPreferences;
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sharedPreferences = getSharedPreferences("wez", MODE_PRIVATE);

        String sended = remoteMessage.getData().get("sendezzat");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "sended: " + sended);


        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


        }

        // Check if message contains a notification payload.
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(sended)) {
            if (!sharedPreferences.getBoolean("is_live", true)) {
                sendNotification(remoteMessage);

            }

            Log.d(TAG, "remoteMessage.getNotification() != null" + sharedPreferences.getBoolean("is_live", false));
        }

    }

    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.

    // [END receive_message]
    private void updateTokken(String token) {
        TokkenModel tokkenModel = new TokkenModel(token);
        FirebaseDatabase.getInstance().getReference().child("tokkens")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(tokkenModel);

    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);


        updateTokken(token);
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }


    private void sendNotification(RemoteMessage messageBody) {
        String user = messageBody.getData().get("user");
        String icon = messageBody.getData().get("icon");
        String title = messageBody.getData().get("title");
        String body = messageBody.getData().get("body");


        Intent intent = new Intent(this, ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = "ch1";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Random random = new Random(1000);
        notificationManager.notify(random.nextInt() /* ID of notification */, notificationBuilder.build());
    }
}
