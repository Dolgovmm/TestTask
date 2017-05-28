package ru.dolgov.restclient.client;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class User {
    private static String login = "";
    private static String password = "";

    public static void setLogin(String newLogin) {
        login = newLogin;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    public static String getLogin() {
        return login;
    }

    public static String getStringToAuth() {
        return login + ":" + password;
    }
}
