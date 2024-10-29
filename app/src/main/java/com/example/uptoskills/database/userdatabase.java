package com.example.uptoskills.database;

import java.util.ArrayList;
import java.util.List;

public class userdatabase {
    public static List<String>username = new ArrayList<>();
    public static List<String>email = new ArrayList<>();
    public static List<String>password = new ArrayList<>();
    static List<String>title1 = new ArrayList<>();
    userdatabase(String date , String url , String description , String title){
        username.add(date);
        email.add(url);
        password.add(description);
    }
    userdatabase(){
    }
}
