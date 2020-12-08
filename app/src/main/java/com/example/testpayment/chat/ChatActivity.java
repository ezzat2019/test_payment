package com.example.testpayment.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testpayment.R;
import com.example.testpayment.chat.adapter.ChatAdapter;
import com.example.testpayment.chat.api.FCMAPI;
import com.example.testpayment.chat.model.DataModel;
import com.example.testpayment.chat.model.MessageModel;
import com.example.testpayment.chat.model.ResponseModel;
import com.example.testpayment.chat.model.SenderModel;
import com.example.testpayment.chat.model.TokkenModel;
import com.example.testpayment.chat.retrofit.ClientRetrofit;
import com.example.testpayment.ui_auth.UiLoginActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    public static final String user_id = "D7DweXvRXGQ406DaOnBtyiMs33g1";

    // ui
    private RecyclerView rec_messgae;
    private EditText ed_message;
    private Button btn_send;


    // var
    private ArrayList<MessageModel> messageModels = new ArrayList<>();
    private ChatAdapter chatAdapter;
    private FCMAPI fcmapi;
    private Boolean notify = false;
    private static int a = 0;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    // firebase
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref_chats = firebaseDatabase.getReference().child("chats");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        shared = getSharedPreferences("wez", MODE_PRIVATE);
        editor = shared.edit();

        init();
        buildAPI();
        observeChat();
        updateTokken(FirebaseInstanceId.getInstance().getToken());

        actions();
    }

    private void setLive(Boolean b) {
        editor.putBoolean("is_live", b);
        editor.commit();
    }

    private void buildAPI() {
        fcmapi = ClientRetrofit.getRetrofit("https://fcm.googleapis.com/").create(FCMAPI.class);
    }

    private void actions() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ed_message.getText().toString())) {
                    return;
                } else {
                    notify = true;
                    String message = ed_message.getText().toString();
                    MessageModel messageModel = new MessageModel(
                            message,
                            FirebaseAuth.getInstance().getCurrentUser().getUid()
                            , user_id
                    );
                    ref_chats.push().setValue(messageModel)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {




                                            Log.d("xxxxx", "run");
                                            sendNotifiction(messageModel);
                                            notify = false;




                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    });

                    ed_message.setText("");
                }
            }
        });
    }

    private void sendNotifiction(MessageModel messageModel) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("tokkens");
        Query query = reference.orderByKey().equalTo(messageModel.getReciver());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TokkenModel tokkenModel = dataSnapshot.getValue(TokkenModel.class);
                    DataModel dataModel = new DataModel(messageModel.getSender()
                            , "new mesage"
                            , messageModel.getMessgae_content()
                            , messageModel.getReciver(), R.mipmap.ic_launcher
                    );
                    SenderModel senderModel = new SenderModel(dataModel, tokkenModel.getTokken());
                    fcmapi.sendNotifiction(senderModel).enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.code() == 200) {
                                if (response.body().success != 1) {
                                    Toast.makeText(ChatActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(ChatActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void observeChat() {
        ref_chats.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MessageModel model = dataSnapshot.getValue(MessageModel.class);
                        Log.e("aaaaaaaaaa", "onDataChange: " + model.toString());


                        messageModels.add(model);
                        chatAdapter.addMessages(messageModels);


                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rec_messgae.smoothScrollToPosition(messageModels.size() - 1);
                        }
                    }, 100);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateTokken(String token) {
        TokkenModel tokkenModel = new TokkenModel(token);
        FirebaseDatabase.getInstance().getReference().child("tokkens")
                .child(firebaseAuth.getCurrentUser().getUid())
                .setValue(tokkenModel);

    }

    private void init() {
        btn_send = findViewById(R.id.btn_send);
        ed_message = findViewById(R.id.ed_enter_message);

        buildRecMessgae();
    }

    private void buildRecMessgae() {
        rec_messgae = findViewById(R.id.rec_messgae);
        rec_messgae.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        rec_messgae.setLayoutManager(linearLayoutManager);

        chatAdapter = new ChatAdapter();
        rec_messgae.setAdapter(chatAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        setLive(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setLive(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("xxxxx", "onStop:1  " + a);
        setLive(false);
        Log.d("xxxxx", "onStop:2  " + a);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setLive(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_out_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            AuthUI.getInstance().signOut(this);
            startActivity(new Intent(getApplicationContext(), UiLoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
        return true;
    }
}