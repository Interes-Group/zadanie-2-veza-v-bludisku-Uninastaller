package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseLogger extends MouseAdapter {

    private int x;
    private int y;
    private boolean mouseMove;
    private MyFakeCanvas canvas;

    public MyMouseLogger(MyFakeCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        x = e.getX();
        y = e.getY();

        getCoordinatesByValue(x, y);

        if (mouseMove) {
            canvas.getPlayer().Hover(x, y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        x = e.getX();
        y = e.getY();

        getCoordinatesByValue(x, y);
        if (!mouseMove) {

            if ((x == canvas.getPlayer().getXPosition()) && (y == canvas.getPlayer().getYPosition())) {
                mouseMove = true;
                canvas.getPlayer().showPossibleRoads();
                canvas.repaint();
            }
        } else {

            mouseMove = false;
            canvas.getPlayer().possibleMove(x, y);
        }
    }

    void getCoordinatesByValue(int x, int y) {

        int variable = x % 30;
        x = x - variable - 90;
        x /= 30;

        variable = y % 30;
        y = y - variable - 60;
        y /= 30;

        this.x = y;
        this.y = x;

    }

    public void setMouseMove(boolean mouseMove) {
        this.mouseMove = mouseMove;
    }

}
