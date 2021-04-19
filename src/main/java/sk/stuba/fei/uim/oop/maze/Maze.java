package sk.stuba.fei.uim.oop.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze {

    private int[][] maze;
    private int[][] baseMaze;
    private int actX;
    private int actY;
    private int mazeWidth;
    private int mazeLength;
    private List<Direction> directions;


    public Maze(int length, int width) {

        mazeWidth = width;
        mazeLength = length;
        baseMaze = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                baseMaze[i][j] = 9;
            }
        }
        maze = new int[length][width];

        directions = new ArrayList<>();
        directions.add(new DirectionUp(this));
        directions.add(new DirectionRight(this,mazeWidth));
        directions.add(new DirectionDown(this,mazeLength));
        directions.add(new DirectionLeft(this));

    }

    public void newMaze() {

        for (int i = 0; i < maze.length; i++) {
            maze[i] = baseMaze[i].clone();
        }

    }


    public Integer[] generateRandomDirections() {
        ArrayList<Integer> directions = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++)
            directions.add(i);
        Collections.shuffle(directions);

        return directions.toArray(new Integer[4]);
    }

    public int getSquare(int x, int y) {
        return maze[x][y];
    }

    public void mazeCreation(int x, int y) {

        actX = x;
        actY = y;
        newMaze();
        depthFirst(actX, actY);
        setFinish();

    }

    void depthFirst(int actX, int actY) {

        Integer[] directions = generateRandomDirections();

        for (Integer direction : directions) {

            this.directions.get(direction).move(actX,actY);

        }
    }

    void setFinish() {

        for (int i = 0; i < mazeLength; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if ((maze[i][j] == 0) && (searchForWalls(i, j) == 3)) {

                    actX = i;
                    actY = j;
                }
            }
        }
        maze[actX][actY] = 2;
    }

    int searchForWalls(int i, int j) {

        int counter = 0;

        if (maze[i + 1][j] == 9) counter++;
        if (maze[i - 1][j] == 9) counter++;
        if (maze[i][j + 1] == 9) counter++;
        if (maze[i][j - 1] == 9) counter++;

        return counter;
    }

    public void setSquare(int x, int y, int z) {
        this.maze[x][y] = z;
    }
}
