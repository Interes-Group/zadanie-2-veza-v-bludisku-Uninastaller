package sk.stuba.fei.uim.oop.maze.randomizedDepthFirst;

import sk.stuba.fei.uim.oop.maze.Maze;

public abstract class Direction {

    protected Maze maze;

    public Direction(Maze maze) {
        this.maze = maze;
    }

    public abstract void move(int actX, int actY);


}
