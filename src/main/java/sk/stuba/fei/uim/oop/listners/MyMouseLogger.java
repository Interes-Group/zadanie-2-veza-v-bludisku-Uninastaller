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

        prepocetNaMojeSuradnice(x,y);
        System.out.println("pohyb.x:" + x + ", y:" + y);
        if(mouseMove){
           frame.getCanvas().Hover(x,y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {



        System.out.println(e.getPoint());
        x = e.getX();
        y = e.getY();

       prepocetNaMojeSuradnice(x,y);
       System.out.println("click.x:" + x + ", y:" + y);
        if(!mouseMove){

            if((x==frame.getCanvas().getPlayer().getXPosition())&&(y==frame.getCanvas().getPlayer().getYPosition())){
                System.out.println("stlacil si na hraca");
                mouseMove = true;
                frame.getCanvas().getPlayer().showPossibleRoads();
                frame.getCanvas().repaint();
            }
        }
        else {

            mouseMove = false;
            frame.getCanvas().possibleMove(x,y);
            System.out.println(frame.getCanvas().getPlayer().getXPosition() + " " + frame.getCanvas().getPlayer().getYPosition());

        }
    }

    void prepocetNaMojeSuradnice(int x, int y){
        int pomocnaPremenna = x % 30;
        x = x - pomocnaPremenna - 90;
        x /=30;

        pomocnaPremenna = y%30;
        y = y - pomocnaPremenna - 60;
        y /=30;

        pomocnaPremenna = x;
        this.x = y;
        this.y = pomocnaPremenna;

//        System.out.println("x:" + x + ", y:" + y);
    }

}
