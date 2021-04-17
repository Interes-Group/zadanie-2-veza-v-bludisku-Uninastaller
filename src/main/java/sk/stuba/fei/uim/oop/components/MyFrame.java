package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.listners.MyKeyboardListener;
import sk.stuba.fei.uim.oop.listners.MyMouseLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame{

    private MyFakeCanvas canvas;
    private MyPanel panel;
    private MyMouseLogger myMouseLogger;

    public MyFakeCanvas getCanvas() {
        return canvas;
    }

    public MyPanel getPanel() {
        return panel;
    }

    public MyFrame(String label, int width, int length) {

        super(label);
        canvas = new MyFakeCanvas(this,width,length);
        this.setSize(650, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(canvas);
        this.setVisible(true);

        panel = new MyPanel(new GridLayout(2, 3),new MyKeyboardListener(canvas));
        this.add(BorderLayout.PAGE_START, panel);
        this.setVisible(true);

        myMouseLogger = new MyMouseLogger(this);
        canvas.addMouseListener(myMouseLogger);
        canvas.addMouseMotionListener(myMouseLogger);

    }

    @Override
    protected void processKeyEvent(KeyEvent e) {

        canvas.processKeyEvent(e);

    }

}

