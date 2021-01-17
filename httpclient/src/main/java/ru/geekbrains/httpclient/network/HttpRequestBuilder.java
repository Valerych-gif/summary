package ru.geekbrains.httpclient.network;

import ru.geekbrains.httpclient.Method;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private final StringBuilder httpRequest;

    private final Method DEFAULT_METHOD = Method.GET;
    private final String DEFAULT_PATH = "/";
    private final String DEFAULT_PROTOCOL_VERSION = "HTTP/1.1";
    private final String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";


    private Method method;
    private String path;
    private String protocolVersion;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private String body;

    private boolean isMethodSetted, isPathSetted, isProtocolSetted, isBodyNotEmpty;

    public HttpRequestBuilder() {
        this.httpRequest = new StringBuilder();
        this.method = DEFAULT_METHOD;
        this.path = DEFAULT_PATH;
        this.protocolVersion = DEFAULT_PROTOCOL_VERSION;
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
        this.body = "";
    }

    public HttpRequestBuilder method(Method method) {
        if (!isMethodSetted) {
            this.method = method;
            isMethodSetted = true;
        } else {
            throw new IllegalArgumentException(String.format("There is may be only one method in request. You set %s as method recently", this.method));
        }
        return this;
    }

    public HttpRequestBuilder path(String pageAddress) {
        if (!isPathSetted) {
            this.path = pageAddress;
            isPathSetted = true;
        } else {
            throw new IllegalArgumentException(String.format("There is may be only one path in request. You set %s as path recently", this.path));
        }
        return this;
    }

    public HttpRequestBuilder protocol(String protocolVersion) {
        if (!isProtocolSetted) {
            this.protocolVersion = protocolVersion;
            isProtocolSetted = true;
        } else {
            throw new IllegalArgumentException(String.format("There is may be only one protocol version in request. You set %s as protocol version recently", this.protocolVersion));
        }
        return this;
    }

    public HttpRequestBuilder header(String key, String value) {
        if (headers.containsKey(key))
            throw new IllegalArgumentException(String.format("Headers already have a header %s", key));
        headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder param(String key, String value) {
        if (params.containsKey(key))
            throw new IllegalArgumentException(String.format("Parameters already have a parameter %s", key));
        params.put(key, value);
        return this;
    }

    public HttpRequestBuilder body(String body) {
        if (!isBodyNotEmpty) {
            this.body = body;
            isBodyNotEmpty = true;
        } else {
            throw new IllegalArgumentException(String.format("There is may be only one body in request. You set %s as body recently", this.body));
        }
        return this;
    }

    public String build() {
        if (this.method.equals(Method.GET)) return buildGetRequest();
        return buildNotGetRequest();
    }

    private String buildGetRequest() {

        setUpStartLine();
        setUpHeaders();
        setUpRequestBody();

        return httpRequest.toString();
    }

    private String buildNotGetRequest() {
        setUpStartLine();

        if (!headers.containsKey("Content-Type"))
            headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
        if (!headers.get("Content-Type").equals(DEFAULT_CONTENT_TYPE))
            throw new IllegalArgumentException(
                    String.format("Sorry, but Content-type %s isn't supported. Please use %s",
                    headers.get("Content-Type"), DEFAULT_CONTENT_TYPE));

        setUpHeaders();
        setUpRequestBody();

        return httpRequest.toString();
    }

    private String getParamsString() {
        if (params.size() == 0) return "";
        StringBuilder paramsString = new StringBuilder("?");
        for (String key : params.keySet()) {
            paramsString
                    .append(key)
                    .append("=")
                    .append(params.get(key))
                    .append("&");
        }
        return paramsString.substring(0, paramsString.toString().length() - 1);
    }

    private void setUpRequestBody() {
        httpRequest
                .append(System.lineSeparator())
                .append(body);
    }

    private void setUpHeaders() {
        for (String key : headers.keySet()) {
            httpRequest
                    .append(key)
                    .append(": ")
                    .append(headers.get(key))
                    .append(System.lineSeparator());
        }
    }

    private void setUpStartLine() {
        if (protocolVersion.equals(DEFAULT_PROTOCOL_VERSION) && !headers.containsKey("Host")) {
            throw new IllegalArgumentException("You must set header Host in request by header() method");
        }

        httpRequest
                .append(method)
                .append(" ")
                .append(path);
        if (method.equals(Method.GET))
            httpRequest
                    .append(getParamsString());
        httpRequest
                .append(" ")
                .append(protocolVersion)
                .append(System.lineSeparator());
    }

}
