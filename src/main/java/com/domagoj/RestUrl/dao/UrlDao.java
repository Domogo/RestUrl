package com.domagoj.RestUrl.dao;

import com.domagoj.RestUrl.entity.Url;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UrlDao {

    private static ArrayList urls;

    static {

        urls = new ArrayList<Url>() {
            {
                add(new Url("https://docs.djangoproject.com/en/1.11/intro/tutorial01/", "shortUrl", 0));
                add(new Url("https://docs.djangoproject.com/en/1.11/intro/tutorial01/", "kratak", 0));

            }
        };
    }

    public ArrayList<Url> getAllUrls() {
        return this.urls;
    }

    /*public void addUrl(Url url) {
        this.urls.add(url);
    }*/

    public Url getUrlByShort(String shortUrl) {
        for (int i = 0; i < urls.size(); ++i) {
            Url url = (Url) urls.get(i);
            if (url.getShortUrl().equals(shortUrl)) {
                int count = ((Url) urls.get(i)).getCounter();
                ((Url) urls.get(i)).setCounter(++count);
                return url;
            }
        }
        return null;
    }

    public void addNewUrl(Url user) {
        this.urls.add(user);
    }
}