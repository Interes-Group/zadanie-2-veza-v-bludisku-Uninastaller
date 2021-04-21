package sk.stuba.fei.uim.oop.maze.randomizedDepthFirst;

import sk.stuba.fei.uim.oop.maze.Maze;

public class DirectionUp extends Direction {

    public DirectionUp(Maze maze) {
        super(maze);
    }

    @Override
    public void move(int actX, int actY) {

        if ((actX - 2 > 0) && (maze.getSquare(actX - 2,actY).isWall())) {
            maze.setSquare(actX - 2,actY,false);
            maze.setSquare(actX - 1,actY, false);
            maze.depthFirst(actX - 2, actY);
        }

    }
}
