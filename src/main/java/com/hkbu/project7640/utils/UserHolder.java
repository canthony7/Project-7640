package com.hkbu.project7640.utils;

import com.hkbu.project7640.entity.User;

/**
 * @author Chet
 * @date 11/2/2023 4:49 pm
 * 使用线程池存user对象，所有controller可以拿到
 */
public class UserHolder {
    private static ThreadLocal<User> tl = new ThreadLocal<User>();
    public static void saveUser(User user){
        tl.set(user);
    }
    public static User getUser(){
        return tl.get();
    }
    public static void removeUser(){
        tl.remove();;
    }
}
