package ru.geekbrains.httpclient.handlers;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ConnectionHandler {

    private Socket socket;
    private DataOutputStream os;
    private DataInputStream is;

    public ConnectionHandler() {


    }

    public String getResponse(SocketAddress address, String request) {

        String response = null;
        try {
            socket = new Socket();
            socket.connect(address);
            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            os.write(request.getBytes(StandardCharsets.UTF_8));

            Thread.sleep(1000);
            byte[] responseBytes = new byte[is.available()];

            is.read(responseBytes);

            response = new String(responseBytes);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
