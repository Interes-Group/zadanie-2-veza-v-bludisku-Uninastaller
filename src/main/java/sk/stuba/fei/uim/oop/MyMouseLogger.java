package sk.stuba.fei.uim.oop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseLogger  extends MouseAdapter {

    private int x;
    private int y;
    private int pomocnaPremenna;
    private boolean mouseMove;

    private MyFrame frame;

    public MyMouseLogger(MyFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {



        System.out.println(e.getPoint());
        x = e.getX();
        y = e.getY();

        pomocnaPremenna = x%30;
        x = x - pomocnaPremenna - 90;
        x /=30;

        pomocnaPremenna = y%30;
        y = y - pomocnaPremenna - 60;
        y /=30;

        pomocnaPremenna = x;
        x = y;
        y = pomocnaPremenna;

        System.out.println("x:" + x + ", y:" + y);

        if(!mouseMove){

            if((x==frame.canvas.player.getxPosition())&&(y==frame.canvas.player.getyPosition())){
                System.out.println("stlacil si na hraca");
                mouseMove = true;
                frame.canvas.showPosibleRoads();
                frame.canvas.repaint();
            }
        }
        else {

            mouseMove = false;
            frame.canvas.posibleMove(x,y);
            System.out.println(frame.canvas.player.getxPosition() + " " + frame.canvas.player.getyPosition());

        }
        frame.requestFocus();
    }

}
