package ru.dolgov.restclient.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.dolgov.restclient.entity.Contact;
import ru.dolgov.restclient.entity.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class Client {
    private final String SERVICE_URL = "http://localhost:8080/contact";
    private final String URL_TO_ADD = "/add";
    private final String URL_TO_GET = "/contacts/";
    private final String URL_TO_UPDATE = "/update";
    private final String URL_TO_DELETE = "/delete";

    public void addContact(Contact contact, User user) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(SERVICE_URL + URL_TO_ADD);
        ObjectMapper mapper = new ObjectMapper();
        StringEntity input = new StringEntity(mapper.writeValueAsString(contact));
        input.setContentType("application/json");
        postRequest.setEntity(input);

        String authString = Base64.encode(user.getStringToAuth().getBytes());

        postRequest.

        HttpResponse response = client.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
            System.out.println("some wrong: " + response.getStatusLine().getStatusCode());
        }

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            System.out.println("done");
        }

        client.close();
    }
}
