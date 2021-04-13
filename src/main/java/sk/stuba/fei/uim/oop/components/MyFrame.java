package sk.stuba.fei.uim.oop.components;

import lombok.Getter;
import sk.stuba.fei.uim.oop.listners.MyKeyboardListener;
import sk.stuba.fei.uim.oop.listners.MyMouseLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame{

    @Getter
    private MyFakeCanvas canvas;
    JLabel label1;
    JButton up;
    JButton down;
    JButton left;
    JButton right;
    JButton restart;
    MyMouseLogger myMouseLogger = new MyMouseLogger(this);
    private int numberOfWins;

    public void resetNumberOfWins(){
        numberOfWins = 0;
        label1.setText("   pocet vyhier    " + numberOfWins);
    }

    public void won() {
        numberOfWins += 1;
        label1.setText("   pocet vyhier    " + numberOfWins);
    }

    public MyFrame(String label, int x, int y) {
        super(label);
        this.setSize(650, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        canvas = new MyFakeCanvas(this,x,y);
        this.add(canvas);
        setVisible(true);
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == restart) {
//            canvas.restart(true);
//        } else if (e.getSource() == up) {
//            canvas.processKeyEvent("up");
//        } else if (e.getSource() == down) {
//            canvas.processKeyEvent("down");
//        } else if (e.getSource() == left) {
//            canvas.processKeyEvent("left");
//        } else if (e.getSource() == right) {
//            canvas.processKeyEvent("right");
//        }
//        canvas.listClear();
//        this.requestFocus();
//    }
}

