package com.example.testpayment.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testpayment.R;
import com.example.testpayment.chat.model.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatVH> {
    private static final int IS_SENDER = 11;
    private static final int IS_RECIVER = 22;

    // var
    private List<MessageModel> messageModels = new ArrayList<>();
    private int view_type;

    @NonNull
    @Override
    public ChatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.view_type = viewType;
        View view;
        if (viewType == IS_SENDER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_item_right, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_item_left, parent, false);
        }

        return new ChatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatVH holder, int position) {
        holder.bindData(messageModels.get(position));
    }

    @Override
    public int getItemViewType(int position) {

        if (position != -1) {
            if (!messageModels.isEmpty()) {

                if (messageModels.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    return IS_SENDER;
                } else {
                    return IS_RECIVER;
                }

            }

        }

        return super.getItemViewType(position);


    }

    public void addMessages(ArrayList<MessageModel> messageModels) {

        this.messageModels = messageModels;
        notifyDataSetChanged();
    }

    public void updateMessages(MessageModel messageModel) {
        messageModels.add(messageModel);

    }

    @Override
    public int getItemCount() {

        return messageModels.size();
    }

    class ChatVH extends RecyclerView.ViewHolder {
        private View v;
        private CircleImageView img_url;
        private TextView txt_message;

        public ChatVH(@NonNull View itemView) {
            super(itemView);
            this.v = itemView;
            init();
        }

        private void init() {

            if (view_type == IS_SENDER) {
                txt_message = v.findViewById(R.id.txt_message_right);
            } else {
                txt_message = v.findViewById(R.id.txt_message_left);
                img_url = v.findViewById(R.id.img_profile_left);

            }
        }

        public void bindData(MessageModel messageModel) {

            if (view_type == IS_SENDER) {
                txt_message.setText(messageModel.getMessgae_content());
            } else {

                txt_message.setText(messageModel.getMessgae_content());

                if (messageModel.getImg_url() == "" || messageModel.getImg_url() == null) {
//                    Glide.with(v.getContext())
//                            .load(v.getContext().getDrawable(R.drawable.profile))
//                            .into(img_url);
                } else {
//                    Glide.with(v.getContext())
//                            .load(messageModel.getImg_url())
//                            .into(img_url);

                }


            }
        }
    }
}
