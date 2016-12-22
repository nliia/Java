package ru.kpfu.itis;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;
import java.util.Scanner;


public class Draw extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Print address:");
        String address = sc.nextLine();
        System.out.println("Print your port:");
        int myPort = Integer.parseInt(sc.nextLine());
        System.out.println("Print another user's port:");
        int yourPort = Integer.parseInt(sc.nextLine());
        new Draw(myPort, yourPort, address);

    }

    public Draw(int port, int secondPort, String a) {
        int myPort = port;
        int otherPort = secondPort;
        String address = a;

        Paper paper = new Paper();
        SocketExtension socket;

        // setup socket connection.
        try {
            socket = new SocketExtension(myPort, otherPort, address, paper);
            // start all
            socket.startReceiving();
            socket.startSending();

        } catch (SocketException e) {
            System.err.println(e.getMessage());
            // e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(paper, BorderLayout.CENTER);
        setSize(640, 480);
        setVisible(true);
    }
}
