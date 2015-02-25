package org.nightschool.model;

/**
 * Created by Administrator on 2015/2/21.
 */
public class User{
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
