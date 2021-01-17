package ru.geekbrains.httpclient.network;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private String protocol;
    private int status;
    private final Map<String, String> headers;
    private String body;

    public Response() {
        headers = new HashMap<>();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder responseBuilder = new StringBuilder();

        responseBuilder
                .append(protocol)
                .append(" ")
                .append(status)
                .append(System.lineSeparator());

        for (String key : headers.keySet()) {
            responseBuilder
                    .append(key)
                    .append(": ")
                    .append(headers.get(key))
                    .append(System.lineSeparator());
        }

        responseBuilder
                .append(System.lineSeparator())
                .append(body);

        return responseBuilder.toString();
    }
}
