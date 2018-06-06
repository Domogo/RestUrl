package com.domagoj.RestUrl.entity;

public class Url {
    private String longUrl;
    private String shortUrl;
    private int counter;

    public Url(String longUrl, String shortUrl, int counter) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.counter = counter;
    }

    public Url(){}

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
