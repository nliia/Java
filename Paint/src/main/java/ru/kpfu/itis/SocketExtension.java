package ru.kpfu.itis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.*;

class SocketExtension {

    volatile boolean sending = false;
    volatile boolean receiving = false;
    int myPort, yourPort;
    String address;

    final int BUFF_SIZE = 256;
    DatagramSocket socket;
    Paper paper;

    public SocketExtension(int myPort, int otherPort, String address,
                           Paper paper) throws SocketException {
        this.myPort = myPort;
        this.yourPort = otherPort;
        this.address = address;
        socket = new DatagramSocket(myPort);
        this.paper = paper;

        paper.addMouseListener(new L1());
        paper.addMouseMotionListener(new L2());
    }

    //Start to receive points from the other client.

    public void startReceiving() {
        if (!receiving) {
            receiving = true;
            new Thread(new Runnable() {
                String tmpStr;
                byte[] buf;
                DatagramPacket datagramPacket;

                public void run() {
                    try {
                        buf = new byte[BUFF_SIZE];
                        datagramPacket = new DatagramPacket(buf, buf.length);

                        while (receiving) {
                            socket.receive(datagramPacket);
                            tmpStr = new String(buf);
                            paper.addPoint(stringToPoint(tmpStr));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    //  stop receiving Points from the other client.

    public void stopReceiving() {
        receiving = false;
    }


//     Used to register an action listener that sends Points to the other client
//     on certain events

    class L1 extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            if (sending)
                sendPoint(me.getPoint());
        }
    }

    class L2 extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent me) {
            if (sending)
                sendPoint(me.getPoint());
        }
    }

    public void startSending() {
        sending = true;
    }

    public void stopSending() {
        sending = false;
    }

    //sends point to another client
    synchronized public void sendPoint(Point point) {
        InetAddress addr;
        DatagramPacket p;
        String tmpStr;
        byte buf[];

        tmpStr = pointToString(point);
        buf = tmpStr.getBytes();

        try {
            addr = InetAddress.getByName(address);
            p = new DatagramPacket(buf, buf.length, addr, yourPort);
            socket.send(p);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String pointToString(Point p) {
        String msg = Integer.toString(p.x) + " " + Integer.toString(p.y) + " ";
        return msg;
    }

    public Point stringToPoint(String s) {

        String[] xy = s.trim().split(" ");

        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        return new Point(x, y);
    }
}
