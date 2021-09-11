package com.example.app;

public class StockNewsModel {

    private String author;
    private String tile;
    private String pubDate;
    private String url;
    private String imageUrl;
    private String description;


    public StockNewsModel(String author, String title, String pubDate,
                          String url, String imageUrl, String description) {

        this.author = author;
        this.tile = title;
        this.pubDate = pubDate;
        this.url = url;
        this.imageUrl = imageUrl;
        this.description = description;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
