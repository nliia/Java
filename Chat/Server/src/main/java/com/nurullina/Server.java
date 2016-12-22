package com.nurullina;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int PORT = 1234;
    private static ChatWindow chatWindow;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        chatWindow = new ChatWindow();
        while (true) {
            chatWindow.textArea.setText("Waiting for client...");
            Socket s = ss.accept();
            chatWindow.textArea.setText("Connection established");
            new ServerThread(s, chatWindow).start();
        }
    }
}
