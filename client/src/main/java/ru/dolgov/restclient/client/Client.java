package ru.dolgov.restclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.dolgov.restclient.entity.Contact;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Михалыч on 28.05.2017.
 */
public class Client {
    private final String SERVICE_URL = "http://localhost:8080/contact";
    private final String URL_TO_ADD = "/add";
    private final String URL_TO_GET = "/contacts/";
    private final String URL_TO_UPDATE = "/update";
    private final String URL_TO_DELETE = "/delete";

    public String addContact(Contact contact) throws IOException {

        String result = "";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(SERVICE_URL + URL_TO_ADD);
        String authString = Base64.encode(User.getStringToAuth().getBytes());
        //postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("authorization", "Basic " + authString);

        ObjectMapper mapper = new ObjectMapper();
        StringEntity input = new StringEntity(mapper.writeValueAsString(contact), "UTF-8");
        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = client.execute(postRequest);
        client.close();

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
            result = "error add contact to service with response code: " + response.getStatusLine().getStatusCode();
        }

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            result = "add contact to service successfully";
        }
        return result;
    }
}
