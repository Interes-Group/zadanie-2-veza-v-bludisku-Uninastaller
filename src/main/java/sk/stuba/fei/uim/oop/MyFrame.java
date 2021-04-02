package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener{

    MyCanvas canvas;
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

    public MyFrame(String label) {
        super(label);
        this.setSize(650, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        canvas = new MyCanvas(this);
        this.add(canvas);
        setVisible(true);


        MyPanel panel = new MyPanel(new GridLayout(2, 3));
        panel.setPreferredSize(new Dimension(650, 100));
        panel.setBackground(Color.LIGHT_GRAY);
        this.add(BorderLayout.PAGE_START, panel);
        restart = new JButton("Restart");
        up = new JButton("↑");
        label1 = new JLabel("   pocet vyhier    " + numberOfWins);
        left = new JButton("←");
        down = new JButton("↓");
        right = new JButton("→");
        panel.add(restart);
        panel.add(up);
        panel.add(label1);
        panel.add(left);
        panel.add(down);
        panel.add(right);
        restart.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);

        this.setVisible(true);
        canvas.addMouseListener(myMouseLogger);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {

        canvas.processKeyEvent(e);
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == restart) {
            canvas.restart(true);
        } else if (e.getSource() == up) {
            canvas.processKeyEvent("up");
        } else if (e.getSource() == down) {
            canvas.processKeyEvent("down");
        } else if (e.getSource() == left) {
            canvas.processKeyEvent("left");
        } else if (e.getSource() == right) {
            canvas.processKeyEvent("right");
        }
        this.requestFocus();
    }
}

