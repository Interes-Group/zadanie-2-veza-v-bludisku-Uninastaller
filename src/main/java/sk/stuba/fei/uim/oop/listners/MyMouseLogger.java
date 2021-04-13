package sk.stuba.fei.uim.oop.listners;

import sk.stuba.fei.uim.oop.components.MyFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseLogger extends MouseAdapter {

    private int x;
    private int y;
    private int pomocnaPremenna;
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

//        if(mouseMove){
//            if((x==frame.getCanvas().getHighlightedSquares()){
//
//            }
//        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {



        System.out.println(e.getPoint());
        x = e.getX();
        y = e.getY();

       prepocetNaMojeSuradnice(x,y);

        if(!mouseMove){

            if((x==frame.getCanvas().getPlayer().getxPosition())&&(y==frame.getCanvas().getPlayer().getyPosition())){
                System.out.println("stlacil si na hraca");
                mouseMove = true;
                frame.getCanvas().showPosibleRoads();
                frame.getCanvas().repaint();
            }
        }
        else {

            mouseMove = false;
            frame.getCanvas().posibleMove(x,y);
            System.out.println(frame.getCanvas().getPlayer().getxPosition() + " " + frame.getCanvas().getPlayer().getyPosition());

        }
        frame.requestFocus();
    }

    void prepocetNaMojeSuradnice(int x, int y){
        pomocnaPremenna = x%30;
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
