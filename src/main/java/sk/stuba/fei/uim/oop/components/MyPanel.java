package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.listners.MyKeyboardListener;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private JLabel label1;
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton restart;
    private int numberOfWins;

    public MyPanel(GridLayout gridLayout, MyKeyboardListener keyboardListener) {

        super(gridLayout);
        setPreferredSize(new Dimension(650, 100));
        setBackground(Color.LIGHT_GRAY);
        restart = new MyButton("Restart", "4", keyboardListener);
        up = new MyButton("↑", "1", keyboardListener);
        label1 = new JLabel("   pocet vyhier    " + numberOfWins);
        left = new MyButton("←", "0", keyboardListener);
        down = new MyButton("↓", "3", keyboardListener);
        right = new MyButton("→", "2", keyboardListener);
        add(restart);
        add(up);
        add(label1);
        add(left);
        add(down);
        add(right);

    }

    public void won(boolean reset) {
        if (reset) numberOfWins = 0;
        else numberOfWins += 1;
        setLabel();
    }

    void setLabel() {
        label1.setText("   pocet vyhier    " + numberOfWins);
    }


}
