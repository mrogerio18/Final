package com.example.afinal;

public class Foto {
    private String albumID;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Foto(String albumID, String id, String title, String url, String thumbnailUrl) {
        this.albumID = albumID;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAlbumID() { return albumID; }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
