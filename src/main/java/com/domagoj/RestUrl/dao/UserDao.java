package com.domagoj.RestUrl.dao;

import com.domagoj.RestUrl.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDao {

    private static ArrayList users;

    static {
        users = new ArrayList<User>(){
            {
                add(new User("username", "password"));
            }
        };
    }

    public ArrayList<User> getAllUsers(){
        return this.users;
    }

    public User findByUsername(String username){
        User g_user = null;
        for(int i =0; i < users.size(); ++i){
            User user = (User) users.get(i);
            if(user.getUsername() == username){
                g_user = user;
            }
        }

        return g_user;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
