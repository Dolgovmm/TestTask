package ru.dolgov.restclient.client;

import org.apache.http.client.methods.HttpPost;

/**
 * @author M.Dolgov
 * @date 28.05.2017
 */
public class MyHttpDelete extends HttpPost {

    @Override
    public String getMethod() {
        return "DELETE";
    }

    public MyHttpDelete(String URL) {
        super(URL);
    }
}
