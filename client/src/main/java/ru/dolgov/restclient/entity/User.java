package ru.dolgov.restclient.entity;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getStringToAuth() {
        return login + ":" + password;
    }
}
