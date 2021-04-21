package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyboardListener extends KeyAdapter {

    private final int VK_LEFT = 37;
    private MyFakeCanvas canvas;

    public MyKeyboardListener(MyFakeCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        canvas.listenerHandler(e.getKeyCode() - VK_LEFT);
    }
}
