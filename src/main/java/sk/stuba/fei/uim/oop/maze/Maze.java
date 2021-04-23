package sk.stuba.fei.uim.oop.maze;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;
import sk.stuba.fei.uim.oop.maze.randomizedDepthFirst.*;
import sk.stuba.fei.uim.oop.maze.tile.Finish;
import sk.stuba.fei.uim.oop.maze.tile.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze {

    private Tile[][] maze;
    private MyFakeCanvas canvas;
    private int actX;
    private int actY;
    private int mazeWidth;
    private int mazeLength;
    private List<Direction> directions;
    private Finish finish;


    public Maze(int length, int width, MyFakeCanvas canvas) {

        this.canvas = canvas;
        mazeWidth = width;
        mazeLength = length;
        finish = new Finish(canvas);
        maze = new Tile[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {

                maze[i][j] = new Tile(true);

            }
        }

        directions = new ArrayList<>();
        directions.add(new DirectionUp(this));
        directions.add(new DirectionRight(this, mazeWidth));
        directions.add(new DirectionDown(this, mazeLength));
        directions.add(new DirectionLeft(this));

        mazeCreation(1, 1, false);

    }

    public void newMaze(boolean reset) {

        for (int i = 0; i < mazeLength; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                maze[i][j].setWall(true);
            }
        }
        if (reset)
            maze[finish.getX()][finish.getY()] = new Tile(true);

    }


    public Integer[] generateRandomDirections() {

        ArrayList<Integer> directions = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++)
            directions.add(i);
        Collections.shuffle(directions);

        return directions.toArray(new Integer[4]);

    }

    public Tile getSquare(int x, int y) {
        return maze[x][y];
    }

    public void mazeCreation(int x, int y, boolean reset) {

        actX = x;
        actY = y;
        newMaze(reset);
        depthFirst(actX, actY);
        setFinish();

    }

    public void depthFirst(int actX, int actY) {

        Integer[] directions = generateRandomDirections();

        for (Integer direction : directions) {

            this.directions.get(direction).move(actX, actY);

        }

    }

    void setFinish() {

        for (int i = 0; i < mazeLength; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if (!(maze[i][j].isWall()) && (searchForWalls(i, j) == 3)) {

                    actX = i;
                    actY = j;
                }
            }
        }
        maze[actX][actY] = finish;
        finish.setCoordinates(actX, actY);

    }

    int searchForWalls(int i, int j) {

        int counter = 0;

        if (maze[i + 1][j].isWall()) counter++;
        if (maze[i - 1][j].isWall()) counter++;
        if (maze[i][j + 1].isWall()) counter++;
        if (maze[i][j - 1].isWall()) counter++;

        return counter;
    }

    public void setSquare(int x, int y, boolean wall) {
        this.maze[x][y].setWall(wall);
    }
}
