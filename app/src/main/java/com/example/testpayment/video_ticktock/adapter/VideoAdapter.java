package com.example.testpayment.video_ticktock.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testpayment.R;
import com.example.testpayment.video_ticktock.data.VideoModel;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoVH> {
    public static SimpleExoPlayer simpleExoPlayer;
    private List<VideoModel> list = new ArrayList<>();

    public List<VideoModel> getList() {
        return list;
    }

    public void setList(List<VideoModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public VideoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_tictok_item, parent, false);
        return new VideoVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoVH holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VideoVH extends RecyclerView.ViewHolder {
        private final View v;
        private TextView txt_tic_subtitle, txt_tic_title;
        private StyledPlayerView playerView;


        public VideoVH(@NonNull View itemView) {
            super(itemView);
            this.v = itemView;
            init();


        }

        void bindData(VideoModel videoModel) {
            Log.d("eeeeeeeee", "bindData: ");
            txt_tic_subtitle.setText(videoModel.getSubtitle());
            txt_tic_title.setText(videoModel.getTitle());


        }


        private void init() {

            txt_tic_subtitle = v.findViewById(R.id.txt_tic_subtitle);
            txt_tic_title = v.findViewById(R.id.txt_tic_title);
            playerView = v.findViewById(R.id.tick_tock);

            Log.d("eeeeeeeee", "bindData:2 ");
            simpleExoPlayer = new SimpleExoPlayer.Builder(v.getContext())
                    .build();
            playerView.setPlayer(simpleExoPlayer);

        }


    }
}
