package com.example.testpayment.optimized_recycle.util;

import androidx.recyclerview.widget.DiffUtil;

import com.example.testpayment.optimized_recycle.model.PhotoModel;

import java.util.List;

public class MyDiffUtill  extends DiffUtil.Callback {
    private List<PhotoModel> photoModelsOld;
    private List<PhotoModel> photoModelsNew;

    public MyDiffUtill(List<PhotoModel> photoModelsOld, List<PhotoModel> photoModelsNew) {
        this.photoModelsOld = photoModelsOld;
        this.photoModelsNew = photoModelsNew;
    }

    @Override
    public int getOldListSize() {
        return photoModelsOld.size();
    }

    @Override
    public int getNewListSize() {
        return photoModelsNew.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return photoModelsOld.get(oldItemPosition).getId()==photoModelsNew.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int res=photoModelsOld.get(oldItemPosition).compareTo(photoModelsNew.get(newItemPosition));

        return res==0;
    }
}
