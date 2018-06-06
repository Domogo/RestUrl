package com.domagoj.RestUrl.service;

import com.domagoj.RestUrl.dao.UrlDao;
import com.domagoj.RestUrl.entity.Url;
import com.domagoj.RestUrl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UrlService {

    @Autowired
    private UrlDao urlDao;

    public ArrayList<Url> getAllUrls(){
        return this.urlDao.getAllUrls();
    }

    public Url getUrlByShort(String shortUrl) {
        return this.urlDao.getUrlByShort(shortUrl);
    }

    public void addNewUrl(Url url) {
        this.urlDao.addNewUrl(url);
    }
}
