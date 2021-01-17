package ru.geekbrains.httpclient.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

public class ConnectionHandler {

    private Socket socket;
    private DataOutputStream os;
    private BufferedReader is;

    private final SocketAddress address;

    public ConnectionHandler(SocketAddress address) {
        this.address = address;
        socket = new Socket();
    }

    public void sendRequest(String request){
        try {
            os.write(request.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            socket.connect(address);
            os = new DataOutputStream(socket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            is.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLine() {
        try {
            return is.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getString(int bodyLength) {
        char[] chars = new char[bodyLength];
        try {
            is.read(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(chars);
    }
}
