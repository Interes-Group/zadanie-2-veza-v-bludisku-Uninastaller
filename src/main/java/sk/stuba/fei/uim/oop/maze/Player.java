package sk.stuba.fei.uim.oop.maze;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

public class Player {

    private int xPosition;
    private int yPosition;
    private MyFakeCanvas canvas;

    public Player(int xPosition,int yPosition, MyFakeCanvas canvas) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.canvas = canvas;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void moveHorizontally(int index){
        yPosition += index;
    }
    public void moveVertically(int index){
        xPosition += index;
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void canMoveVertically(int x, int y, int index) {

        if (canvas.getMaze().getSquare(x + index, y) == 0) {
            moveVertically(index);
        } else if (canvas.getMaze().getSquare(x + index, y) == 2) {
            moveVertically(index);
            canvas.won();
        }
    }

    public void canMoveHorizontally(int x, int y, int index) {

        if (canvas.getMaze().getSquare(x, y + index) == 0) {
            moveHorizontally(index);
        } else if (canvas.getMaze().getSquare(x, y + index) == 2) {
            moveHorizontally(index);
            canvas.won();
        }

    }

    public void showPossibleRoads() {

        possibleDownRoads(xPosition, yPosition);
        possibleLeftRoads(xPosition, yPosition);
        possibleRightRoads(xPosition, yPosition);
        possibleUpRoads(xPosition, yPosition);

    }

    void possibleLeftRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x, y - 1) == 0) || (canvas.getMaze().getSquare(x, y - 1) == 2)) {
            canvas.highlightedSquaresAdd(x);
            canvas.highlightedSquaresAdd(y - 1);
            possibleLeftRoads(x, y - 1);
        }
    }

    void possibleRightRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x, y + 1) == 0) || (canvas.getMaze().getSquare(x, y + 1) == 2)) {
            canvas.highlightedSquaresAdd(x);
            canvas.highlightedSquaresAdd(y + 1);
            possibleRightRoads(x, y + 1);
        }
    }

    void possibleUpRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x - 1, y) == 0) || (canvas.getMaze().getSquare(x - 1, y) == 2)) {
            canvas.highlightedSquaresAdd(x - 1);
            canvas.highlightedSquaresAdd(y);
            possibleUpRoads(x - 1, y);
        }
    }

    void possibleDownRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x + 1, y) == 0) || (canvas.getMaze().getSquare(x + 1, y) == 2)) {
            canvas.highlightedSquaresAdd(x + 1);
            canvas.highlightedSquaresAdd(y);
            possibleDownRoads(x + 1, y);
        }
    }

}
