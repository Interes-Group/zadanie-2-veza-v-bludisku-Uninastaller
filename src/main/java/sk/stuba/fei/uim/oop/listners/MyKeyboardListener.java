package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyKeyboardListener implements ActionListener {

    private MyFakeCanvas canvas;

    public MyKeyboardListener(MyFakeCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Restart":
                canvas.restart(true);
                break;
            case "↑":
                canvas.processKeyEvent("up");
                break;
            case "↓":
                canvas.processKeyEvent("down");
                break;
            case "←":
                canvas.processKeyEvent("left");
                break;
            default:
                canvas.processKeyEvent("right");
        }
        canvas.listClear();
    }

}
