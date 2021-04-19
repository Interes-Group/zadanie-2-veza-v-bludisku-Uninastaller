package sk.stuba.fei.uim.oop.maze;

public class DirectionUp extends Direction{

    public DirectionUp(Maze maze) {
        super(maze);
    }

    @Override
    public void move(int actX, int actY) {

        if ((actX - 2 > 0) && (maze.getSquare(actX - 2,actY) == 9)) {
            maze.setSquare(actX - 2,actY,0);
            maze.setSquare(actX - 1,actY, 0);
            maze.depthFirst(actX - 2, actY);
        }

    }
}
