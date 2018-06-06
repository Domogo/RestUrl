package com.domagoj.RestUrl.service;

import com.domagoj.RestUrl.dao.UserDao;
import com.domagoj.RestUrl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ArrayList<User> getAllUsers(){
        return this.userDao.getAllUsers();
    }


    public void addUser(User user) {
        this.userDao.addUser(user);
    }
}
