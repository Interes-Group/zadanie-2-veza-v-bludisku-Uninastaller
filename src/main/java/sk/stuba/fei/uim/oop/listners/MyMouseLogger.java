package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseLogger extends MouseAdapter {

    private int x;
    private int y;
    private boolean mouseMove;

    private MyFrame frame;

    public MyMouseLogger(MyFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        System.out.println(e.getPoint());
        x = e.getX();
        y = e.getY();

        getCoordinatesByValue(x, y);
        System.out.println("pohyb.x:" + x + ", y:" + y);
        if (mouseMove) {
            frame.getCanvas().getPlayer().Hover(x, y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {


        System.out.println(e.getPoint());
        x = e.getX();
        y = e.getY();

        getCoordinatesByValue(x, y);
        if (!mouseMove) {

            if ((x == frame.getCanvas().getPlayer().getXPosition()) && (y == frame.getCanvas().getPlayer().getYPosition())) {
                mouseMove = true;
                frame.getCanvas().getPlayer().showPossibleRoads();
                frame.getCanvas().repaint();
            }
        } else {

            mouseMove = false;
            frame.getCanvas().getPlayer().possibleMove(x, y);
        }
    }

    void getCoordinatesByValue(int x, int y) {
        int variable = x % 30;
        x = x - variable - 90;
        x /= 30;

        variable = y % 30;
        y = y - variable - 60;
        y /= 30;

        variable = x;
        this.x = y;
        this.y = variable;

//        System.out.println("x:" + x + ", y:" + y);
    }

}
