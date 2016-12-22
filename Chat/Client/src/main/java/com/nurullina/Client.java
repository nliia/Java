package com.nurullina;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 1234;
    private static ChatWindow chatWindow;

    public static void main(String[] args) throws IOException {
        Socket s = new Socket(ADDRESS, PORT);
        chatWindow = new ChatWindow();
        new ClientThread(s, chatWindow).start();
    }
}
