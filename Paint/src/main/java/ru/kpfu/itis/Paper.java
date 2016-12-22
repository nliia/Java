package ru.kpfu.itis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;

class Paper extends JPanel {
    private static final long serialVersionUID = 1L;
    private final HashSet<Point> hs = new HashSet<Point>();
    protected JButton clearButton;
    protected JButton redButton;
    protected JButton blackButton;
    protected JButton magentaButton;
    protected JButton greenButton;
    protected JButton blueButton;
    private Color color;

    //creates paper object
    public Paper() {
        color = Color.black;
        setBackground(Color.white);
        addMouseListener(new L1());
        addMouseMotionListener(new L2());
        clearButton = new JButton("Clear");

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        redButton = new JButton("Red");
        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = Color.red;
            }
        });

        blackButton = new JButton("Black");
        blackButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                color = Color.black;
            }
        });

        magentaButton = new JButton("Pink");
        magentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = Color.magenta;
            }
        });

        blueButton = new JButton("Blue");
        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = Color.blue;
            }
        });

        greenButton = new JButton("Green");
        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = Color.green;
            }
        });
        this.add(blackButton);
        this.add(magentaButton);
        this.add(greenButton);
        this.add(blueButton);
        this.add(clearButton);
        this.add(redButton);
    }

    @Override
    synchronized public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        Iterator<Point> i = hs.iterator();
        while (i.hasNext()) {
            Point p = i.next();
            g.fillOval(p.x, p.y, 6, 6);
        }
    }

    //adds point to the paper
    synchronized public void addPoint(Point p) {
        hs.add(p);
        repaint();
    }

    //paints dot at position when the mouse pressed
    class L1 extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            addPoint(me.getPoint());
        }
    }

    class L2 extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent me) {
            addPoint(me.getPoint());
        }
    }

    private void clear() {
        hs.clear();
        repaint();
    }
}