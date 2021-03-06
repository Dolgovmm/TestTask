package ru.dolgov.restclient.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import ru.dolgov.restclient.entity.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M.Dolgov
 * @date 28.05.2017
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
        postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " +
                Base64.encode(User.getStringToAuth().getBytes()));

        postRequest.setEntity(createStringEntity(contact));

        HttpResponse response = client.execute(postRequest);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            result = "добавление контакта выполнено";
        } else {
            result = "ошибка добавления контакта с кодом ответа: " + response.getStatusLine().getStatusCode();
        }
        client.close();
        return result;
    }

    public List<Contact> getContactByName(String name) throws IOException {
        List<Contact> list = null;

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(SERVICE_URL + URL_TO_GET + name);
        getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " +
                Base64.encode(User.getStringToAuth().getBytes()));

        HttpResponse response = client.execute(getRequest);

        HttpEntity httpEntity = response.getEntity();

        if (httpEntity != null) {
            String json = EntityUtils.toString(response.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json,
                    new TypeReference<ArrayList<Contact>>() {});
        }
        client.close();
        return list;
    }

    public String updateContact(Contact contact) throws IOException {
        String result = "";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPut putRequest = new HttpPut(SERVICE_URL + URL_TO_UPDATE);
        putRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " +
                Base64.encode(User.getStringToAuth().getBytes()));

        putRequest.setEntity(createStringEntity(contact));

        HttpResponse response = client.execute(putRequest);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = "изменение контакта выполнено";
        } else {
            result = "ошибка изменения контакта с кодом ответа: " + response.getStatusLine().getStatusCode();
        }

        client.close();
        return result;
    }

    public String deleteContact(Contact contact) throws IOException {
        String result = "";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        MyHttpDelete deleteRequest = new MyHttpDelete(SERVICE_URL + URL_TO_DELETE);
        deleteRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " +
                Base64.encode(User.getStringToAuth().getBytes()));

        deleteRequest.setEntity(createStringEntity(contact));

        HttpResponse response = client.execute(deleteRequest);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = "удаление контакта выполнено";
        } else {
            result = "ошибка удаления коонтакта с кодом ответа: " + response.getStatusLine().getStatusCode();
        }

        client.close();
        return result;
    }

    private StringEntity createStringEntity(Contact contact) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        StringEntity input = new StringEntity(mapper.writeValueAsString(contact), "UTF-8");
        input.setContentType("application/json");
        return input;
    }
}
