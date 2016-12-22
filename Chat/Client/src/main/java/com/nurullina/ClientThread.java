package com.nurullina;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private PrintWriter printWriter;
    private ChatWindow chatWindow;
    private BufferedReader bufferedReader;

    public ClientThread(Socket socket, ChatWindow chatWindow) throws IOException {
        this.socket = socket;
        this.chatWindow = chatWindow;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        chatWindow.setPw(printWriter);
        try {
            JTextArea jta = chatWindow.textArea;
            while (true) {
                jta.append("\n Server: " + bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
