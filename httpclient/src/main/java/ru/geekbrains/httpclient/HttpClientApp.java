package ru.geekbrains.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.geekbrains.httpclient.entities.User;
import ru.geekbrains.httpclient.network.ConnectionHandler;
import ru.geekbrains.httpclient.network.HttpRequestBuilder;
import ru.geekbrains.httpclient.network.Response;
import ru.geekbrains.httpclient.network.HttpResponseParser;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
/*
Метод tryGetMethod() отправляет на сервер GET-запрос с параметром name, сервер отвечает "Hello $name"
Метод tryPostMethod() отправляет POST-запрос с телом в виде JSON, соответствующем объекту класса User. Сервер имитирует создание нового пользователя.
 */
public class HttpClientApp {

    private static final String HOST = "localhost";
    private static final int PORT = 8081;

    private static ConnectionHandler connectionHandler;
    private static HttpResponseParser responseParser;

    public static void main(String[] args) {
        responseParser = new HttpResponseParser();
        tryGetMethod();
        tryPostMethod();
    }

    private static void tryGetMethod() {
        connectionHandler = new ConnectionHandler(new InetSocketAddress(HOST, PORT));
        connectionHandler.connect();
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        String request = httpRequestBuilder
                .method(Method.GET)
                .path("/hello")
                .protocol("HTTP/1.1")
                .header("Host", "localhost")
                .header("Content-type", "plain-text")
                .param("name", "John")
                .body("Body")
                .build();

        System.out.println("\n============Get Request===========");
        System.out.println(request);
        connectionHandler.sendRequest(request);
        Response response = responseParser.getResponse(connectionHandler);
        System.out.println("\n============Response===========");
        System.out.println(response);
        connectionHandler.disconnect();
    }

    private static void tryPostMethod(){
        connectionHandler = new ConnectionHandler(new InetSocketAddress(HOST, PORT));
        connectionHandler.connect();
        User user = new User();
        user.setUsername("John");
        user.setPassword("Secret");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        int bodyLength = jsonString.length();
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        String request = httpRequestBuilder
                .method(Method.POST)
                .path("/hello")
                .protocol("HTTP/1.1")
                .header("Host", "localhost")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Accept", "application/json")
                .header("Connection", "keep-alive")
                .header("Content-Length", String.valueOf(bodyLength))
                .param("username", "John")
                .param("password", "secret")
                .body(jsonString)
                .build();

        System.out.println("\n============Post Request===========");
        System.out.println(request);
        connectionHandler.sendRequest(request);
        Response response = responseParser.getResponse(connectionHandler);
        System.out.println("\n============Response===========");
        System.out.println(response);
        connectionHandler.disconnect();
    }
}
