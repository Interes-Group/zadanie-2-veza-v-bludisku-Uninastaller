package sk.stuba.fei.uim.oop.maze.tile;

import java.awt.*;

public class Tile {

    private boolean wall;

    public Tile(boolean wall) {
        this.wall = wall;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public Color setColor() {

        if (wall) return Color.BLACK;
        else return Color.WHITE;

    }

    public void action() {
    }

}
