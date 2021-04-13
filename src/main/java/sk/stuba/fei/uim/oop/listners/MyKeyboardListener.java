package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyKeyboardListener implements ActionListener {

    MyFakeCanvas canvas;

    public MyKeyboardListener(MyFakeCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Restart")) {
            canvas.restart(true);
        } else if (e.getActionCommand().equals("↑")) {
            canvas.processKeyEvent("up");
        } else if (e.getActionCommand().equals("↓")) {
            canvas.processKeyEvent("down");
        } else if (e.getActionCommand().equals("←")) {
            canvas.processKeyEvent("left");
        } else if (e.getActionCommand().equals("→")) {
            canvas.processKeyEvent("right");
        }
        canvas.listClear();
    }

}
