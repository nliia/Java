package com.nurullina;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private static Socket socket;
    private static PrintWriter printWriter;
    private static ChatWindow chatWindow;
    private static BufferedReader bufferedReader;

    public ServerThread(Socket socket, ChatWindow chatWindow) throws IOException {
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
                jta.append("\n Client: " +  bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
