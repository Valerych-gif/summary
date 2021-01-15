package ru.geekbrains.httpclient.handlers;

import ru.geekbrains.httpclient.Method;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private final String HTTP_VERSION = "HTTP/1.1";
    private final String DEFAULT_HOST = "localhost";

    public String createRequest(String address, Method method, Map<String, String> headers, Map<String, String> params, String body) {

        if (address == null) throw new IllegalArgumentException();
        if (method == null) method = Method.GET;
        if (headers == null) headers = new HashMap<>();
        if (!headers.containsKey("Host")) headers.put("Host", DEFAULT_HOST);
        if (body == null) body = "";

        StringBuilder request = new StringBuilder();
        StringBuilder appendParams = new StringBuilder();

        String stringParams = "";
        if (params != null && params.size() > 0) {

            if (method.equals(Method.GET))
                appendParams.append("?");

            for (String key : params.keySet()) {
                appendParams
                        .append(key)
                        .append("=")
                        .append(params.get(key))
                        .append("&");
            }
            stringParams = appendParams.substring(0, appendParams.toString().length() - 1);
        }

        request
                .append(method)
                .append(" ")
                .append(address);
        if (method.equals(Method.GET)) {
            request.append(stringParams);
        }
        request.append(" ")
                .append(HTTP_VERSION)
                .append(System.lineSeparator());

        for (String key : headers.keySet()) {
            request
                    .append(key)
                    .append(": ")
                    .append(headers.get(key))
                    .append(System.lineSeparator());
        }

        request
                .append(System.lineSeparator());
        if (method.equals(Method.POST)) {
            request
                    .append(stringParams)
                    .append(System.lineSeparator());
        }
        request.append(body);

        return request.toString();
    }
}
