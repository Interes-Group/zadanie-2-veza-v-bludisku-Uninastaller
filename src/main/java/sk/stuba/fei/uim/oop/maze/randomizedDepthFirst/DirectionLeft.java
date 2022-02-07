package sk.stuba.fei.uim.oop.maze.randomizedDepthFirst;

import sk.stuba.fei.uim.oop.maze.Maze;

public class DirectionLeft extends Direction {


    public DirectionLeft(Maze maze) {
        super(maze);
    }

    @Override
    public void move(int actX, int actY) {

        if ((actY - 2 > 0) && (maze.getSquare(actX,actY - 2).isWall())) {
                maze.setSquare(actX,actY - 2,false);
                maze.setSquare(actX,actY - 1,false);
                maze.depthFirst(actX, actY - 2);
        }

    }
}
