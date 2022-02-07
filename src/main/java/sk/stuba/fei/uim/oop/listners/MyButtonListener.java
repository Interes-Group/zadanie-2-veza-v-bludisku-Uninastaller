package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {

    private MyFakeCanvas canvas;

    public MyButtonListener(MyFakeCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        canvas.listenerHandler(Integer.parseInt(e.getActionCommand()));

    }

}
