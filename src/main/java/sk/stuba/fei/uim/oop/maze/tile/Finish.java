package sk.stuba.fei.uim.oop.maze.tile;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.awt.*;

public class Finish extends Tile {

    private MyFakeCanvas canvas;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {

        this.x = x;
        this.y = y;
        setWall(false);

    }


    public Finish(MyFakeCanvas canvas) {

        super(false);
        this.canvas = canvas;

    }

    @Override
    public Color setColor() {
        return Color.CYAN;
    }

    @Override
    public void action() {
        canvas.won();
    }

}
