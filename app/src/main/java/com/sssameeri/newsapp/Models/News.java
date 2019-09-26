package com.sssameeri.newsapp.Models;

public class News {

    private String source ;
    private String name;
    private String date;
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

}
