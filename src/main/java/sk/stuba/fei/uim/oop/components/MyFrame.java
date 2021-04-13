package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.listners.MyKeyboardListener;
import sk.stuba.fei.uim.oop.listners.MyMouseLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame{

    private MyFakeCanvas canvas;
    private JLabel label1;
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton restart;
    private MyMouseLogger myMouseLogger = new MyMouseLogger(this);
    private int numberOfWins;

    public MyFakeCanvas getCanvas() {
        return canvas;
    }

    public void resetNumberOfWins(){
        numberOfWins = 0;
        label1.setText("   pocet vyhier    " + numberOfWins);
    }

    public void won() {
        numberOfWins += 1;
        label1.setText("   pocet vyhier    " + numberOfWins);
    }

    public MyFrame(String label, int width, int length) {
        super(label);
        this.setSize(650, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        canvas = new MyFakeCanvas(this,width,length);
        this.add(canvas);
        this.setVisible(true);
        MyKeyboardListener keyboardListener = new MyKeyboardListener(canvas);


        MyPanel panel = new MyPanel(new GridLayout(2, 3));
        panel.setPreferredSize(new Dimension(650, 100));
        panel.setBackground(Color.LIGHT_GRAY);
        this.add(BorderLayout.PAGE_START, panel);
        restart = new MyButton("Restart", keyboardListener);
        up = new MyButton("↑",keyboardListener);
        label1 = new JLabel("   pocet vyhier    " + numberOfWins);
        left = new MyButton("←",keyboardListener);
        down = new MyButton("↓",keyboardListener);
        right = new MyButton("→",keyboardListener);
        panel.add(restart);
        panel.add(up);
        panel.add(label1);
        panel.add(left);
        panel.add(down);
        panel.add(right);

        this.setVisible(true);
        canvas.addMouseListener(myMouseLogger);
        canvas.addMouseMotionListener(myMouseLogger);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {

        canvas.processKeyEvent(e);
        repaint();

    }

}

