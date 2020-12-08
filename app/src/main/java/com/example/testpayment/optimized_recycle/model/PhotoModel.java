package com.example.testpayment.optimized_recycle.model;

import androidx.annotation.NonNull;

public class PhotoModel implements Cloneable,Comparable {

   private int albumId	;
   private int  id	;
    private String title;
    private String url;

    public PhotoModel(int albumId, int id, String title, String url) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PhotoModel() {
    }


    @Override
    public int compareTo(Object o) {

        PhotoModel model= (PhotoModel) o;
        if (model.id==this.id
        &&model.url.equals(this.url)
        &&model.albumId==this.albumId
        &&model.toString().equals(this.title))
        {
            return 0;
        }
        return 1;

    }
}
