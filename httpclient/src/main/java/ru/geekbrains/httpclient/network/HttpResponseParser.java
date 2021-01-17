package ru.geekbrains.httpclient.network;

import java.net.SocketAddress;

public class HttpResponseParser {

    private int bodyLength;

    public HttpResponseParser() {
    }

    public Response getResponse(ConnectionHandler connectionHandler) {
        Response response = new Response();

        // Стартовая строка
        String line=connectionHandler.getLine();
        String [] splitStr = line.split(" ");
        response.setProtocol(splitStr[0]);
        response.setStatus(Integer.decode(splitStr[1]));

        // Заголовки
        while (!(line = connectionHandler.getLine()).equals("")){
            splitStr = line.split(":");
            String headerName = splitStr[0].trim();
            String headerValue = splitStr[1].trim();
            if (headerName.equals("Content-Length")){
                bodyLength = Integer.decode(headerValue);
            }
            response.addHeader(headerName, headerValue);
        }

        // Тело запроса
        if (bodyLength==0){
            throw new RuntimeException("Content-Length is undefined");
        }

        String body = connectionHandler.getString(bodyLength);
        response.setBody(body);

        connectionHandler.disconnect();

        return response;
    }
}
