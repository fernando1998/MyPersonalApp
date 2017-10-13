package com.cruz.mypersonalapp.Repository;

import com.cruz.mypersonalapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FERNANDO on 12/10/2017.
 */

public class UserRepository {


    public static List<User> users = new ArrayList<>();

    static{
        users.add(new User(100, "ebenites", "123", "Erick Benites"));
        users.add(new User(200, "jfarfan", "123", "Jaime Farfán"));
        users.add(new User(300, "drodriguez", "123", "David Rodriguez"));
    }

    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

    public static User getPassword(String password){
        for (User user : users){
            if(user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
        }
        return null;
    }


    public static User agregarUsuario(String username, String password){
        int id=100*(users.size()+1);
        User user = new User(id, username, password, username);
        users.add(user);
        return user;
    }


}
