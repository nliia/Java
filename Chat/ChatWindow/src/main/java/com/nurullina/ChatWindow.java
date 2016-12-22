package com.nurullina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class ChatWindow extends JFrame implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    protected JButton button1;
    protected PrintWriter pw;

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public ChatWindow() {
        initComponents();
    }

    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        pw.println(text);
        textArea.append("\n You: " + text);
        textField.setText("");
    }

    private void initComponents() {
        this.setSize(300, 300);
        this.setTitle("Client");
        textField = new JTextField(100);
        this.add(textField, BorderLayout.NORTH);
        textArea = new JTextArea();
        this.add(textArea, BorderLayout.CENTER);
        button1 = new JButton("SEND");
        this.add(button1, BorderLayout.SOUTH);
        button1.addActionListener(this);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
