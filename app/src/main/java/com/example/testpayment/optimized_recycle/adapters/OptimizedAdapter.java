package com.example.testpayment.optimized_recycle.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testpayment.R;
import com.example.testpayment.optimized_recycle.model.PhotoModel;
import com.example.testpayment.optimized_recycle.util.MyDiffUtill;

import java.util.ArrayList;
import java.util.List;

public class OptimizedAdapter extends RecyclerView.Adapter<OptimizedAdapter.PhotoVH> {
    private List<PhotoModel>photoModels=new ArrayList<>();
    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.optimized_icon,parent,false);
        return new PhotoVH(v);
    }

    public void setPhotoModels(List<PhotoModel> photoModels) {

        DiffUtil.DiffResult result=DiffUtil.calculateDiff(new MyDiffUtill(this.photoModels,photoModels));
        result.dispatchUpdatesTo(this);
        this.photoModels.clear();
        this.photoModels.addAll( photoModels);


    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH holder, int position) {
        holder.bind(photoModels.get(position));

    }

    @Override
    public int getItemCount() {
        return photoModels.size();
    }

    class PhotoVH extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView txt_name;
        private View vv;

        public PhotoVH(@NonNull View itemView) {
            super(itemView);
            this.vv=itemView;

            init();
        }
        public void bind(PhotoModel photoModel)
        {
            Log.d("aaaaaaaaaa", "bind: "+photoModel.getUrl());
            Glide.with(vv.getContext())
                    .load(photoModel.getUrl())
                    .into(imageView);
            txt_name.setText(photoModel.getTitle());
        }

        private void init() {
            imageView=vv.findViewById(R.id.img_photo);
            txt_name=vv.findViewById(R.id.txt_photo);

        }
    }


}
