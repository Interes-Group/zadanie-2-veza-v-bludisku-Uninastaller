package sk.stuba.fei.uim.oop.maze.randomizedDepthFirst;

import sk.stuba.fei.uim.oop.maze.Maze;

public class DirectionDown extends Direction {

    private int mazeLength;

    public DirectionDown(Maze maze, int mazeLength) {
        super(maze);
        this.mazeLength = mazeLength;
    }

    @Override
    public void move(int actX, int actY) {

        if ((actX + 2 < mazeLength - 1) && (maze.getSquare(actX + 2,actY).isWall())) {
                maze.setSquare(actX + 2,actY,false);
                maze.setSquare(actX + 1,actY,false);
                maze.depthFirst(actX + 2, actY);
        }

    }
}
