package com.example.testpayment.video_ticktock.data;

public class VideoModel {
    private String title, subtitle, sources;

    public VideoModel(String title, String subtitle, String sources) {
        this.title = title;
        this.subtitle = subtitle;
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }
}
