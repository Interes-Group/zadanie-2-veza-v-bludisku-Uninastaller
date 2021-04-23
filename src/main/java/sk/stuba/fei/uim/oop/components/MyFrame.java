package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.listners.MyButtonListener;
import sk.stuba.fei.uim.oop.listners.MyKeyboardListener;
import sk.stuba.fei.uim.oop.listners.MyMouseLogger;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private MyFakeCanvas canvas;
    private MyPanel panel;
    private MyMouseLogger myMouseLogger;

    public MyPanel getPanel() {
        return panel;
    }

    public MyFrame(String label, int length, int width) {

        super(label);
        canvas = new MyFakeCanvas(this, length, width);
        this.setSize(650, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(canvas);

        panel = new MyPanel(new GridLayout(2, 3), new MyButtonListener(canvas));
        this.add(BorderLayout.PAGE_START, panel);
        this.setVisible(true);

        myMouseLogger = new MyMouseLogger(canvas);
        canvas.addMouseListener(myMouseLogger);
        canvas.addMouseMotionListener(myMouseLogger);
        addKeyListener(new MyKeyboardListener(canvas));

    }

    public MyMouseLogger getMyMouseLogger() {
        return myMouseLogger;
    }
}

