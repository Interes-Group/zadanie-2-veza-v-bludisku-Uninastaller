package sk.stuba.fei.uim.oop.maze.randomizedDepthFirst;

import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.randomizedDepthFirst.Direction;

public class DirectionLeft extends Direction {


    public DirectionLeft(Maze maze) {
        super(maze);
    }

    @Override
    public void move(int actX, int actY) {

        if ((actY - 2 > 0) && (maze.getSquare(actX,actY - 2) == 9)) {
                maze.setSquare(actX,actY - 2,0);
                maze.setSquare(actX,actY - 1,0);
                maze.depthFirst(actX, actY - 2);
        }

    }
}
