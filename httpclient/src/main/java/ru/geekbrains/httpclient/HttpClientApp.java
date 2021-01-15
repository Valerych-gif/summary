package ru.geekbrains.httpclient;

import ru.geekbrains.httpclient.handlers.ConnectionHandler;
import ru.geekbrains.httpclient.handlers.RequestHandler;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class HttpClientApp {

    private static final String HOST = "localhost";
    private static final int PORT = 8081;

    private static RequestHandler requestHandler;
    private static ConnectionHandler connectionHandler;

    private static SocketAddress host;
    private static String pageAddress;
    private static Method method;
    private static Map<String, String> headers;
    private static Map<String, String> params;
    private static String body;

    public static void main(String[] args) {
        requestHandler = new RequestHandler();

        connectionHandler = new ConnectionHandler();

        host = new InetSocketAddress(HOST, PORT);
        method = Method.GET;
        body ="";

        tryGetMethod();
        tryPostMethod();

        connectionHandler.close();
    }

    private static void tryGetMethod() {
        headers = new HashMap<>();
        params = new HashMap<>();
        headers.put("Host", "localhost");
        pageAddress = "/hello";
        params.put("name", "John");
        method = Method.GET;
        body = "Body";
        String request = requestHandler.createRequest(pageAddress, method, headers, params, body);
        System.out.println("\n============Get Request===========");
        System.out.println(request);
        String response = connectionHandler.getResponse(host, request);
        System.out.println("\n============Response===========");
        System.out.println(response);
    }

    private static void tryPostMethod() {
        headers = new HashMap<>();
        params = new HashMap<>();
        headers.put("Host", "localhost");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        pageAddress = "/hello";
        params.put("username", "John");
        params.put("password", "Password");
        method = Method.POST;
        body = "";
        String request = requestHandler.createRequest(pageAddress, method, headers, params, body);
        System.out.println("\n============Post Request===========");
        System.out.println(request);
        String response = connectionHandler.getResponse(host, request);
        System.out.println("\n============Response===========");
        System.out.println(response);
    }
}
