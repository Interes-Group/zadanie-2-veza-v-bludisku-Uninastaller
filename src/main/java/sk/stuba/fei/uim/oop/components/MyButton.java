package sk.stuba.fei.uim.oop.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {

    public MyButton(String label, ActionListener actionListener) {

        super(label);
        this.addActionListener(actionListener);
        setFocusable(false);

    }
}
