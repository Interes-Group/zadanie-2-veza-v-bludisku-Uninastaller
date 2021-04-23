package sk.stuba.fei.uim.oop.maze;

import sk.stuba.fei.uim.oop.components.MyFakeCanvas;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int xPosition;
    private int yPosition;
    private int x;
    private int y;
    private MyFakeCanvas canvas;
    private List<Integer> highlightedSquares = new ArrayList<Integer>();
    private List<Integer> highlightedCircle = new ArrayList<Integer>();

    public Player(int xPosition, int yPosition, MyFakeCanvas canvas) {

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

    public void moveHorizontally(int index) {

        yPosition += index;

    }

    public void moveVertically(int index) {

        xPosition += index;

    }

    public void setPosition(int xPosition, int yPosition) {

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        canvas.getMaze().getSquare(this.xPosition, this.yPosition).action();

    }

    public void canMoveVertically(int x, int y, int index) {

        if (!(canvas.getMaze().getSquare(x + index, y).isWall())) {
            moveVertically(index);
            canvas.getMaze().getSquare(x + index, y).action();
        }

    }

    public void canMoveHorizontally(int x, int y, int index) {

        if (!(canvas.getMaze().getSquare(x, y + index).isWall())) {
            moveHorizontally(index);
            canvas.getMaze().getSquare(x, y + index).action();
        }

    }

    public void showPossibleRoads() {

        possibleDownRoads(xPosition, yPosition);
        possibleLeftRoads(xPosition, yPosition);
        possibleRightRoads(xPosition, yPosition);
        possibleUpRoads(xPosition, yPosition);

    }

    void possibleLeftRoads(int x, int y) {

        if (!(canvas.getMaze().getSquare(x, y - 1).isWall())) {
            highlightedSquaresAdd(x);
            highlightedSquaresAdd(y - 1);
            possibleLeftRoads(x, y - 1);
        }

    }

    void possibleRightRoads(int x, int y) {

        if (!(canvas.getMaze().getSquare(x, y + 1).isWall())) {
            highlightedSquaresAdd(x);
            highlightedSquaresAdd(y + 1);
            possibleRightRoads(x, y + 1);
        }

    }

    void possibleUpRoads(int x, int y) {

        if (!(canvas.getMaze().getSquare(x - 1, y)).isWall()) {
            highlightedSquaresAdd(x - 1);
            highlightedSquaresAdd(y);
            possibleUpRoads(x - 1, y);
        }

    }

    void possibleDownRoads(int x, int y) {

        if (!(canvas.getMaze().getSquare(x + 1, y).isWall())) {
            highlightedSquaresAdd(x + 1);
            highlightedSquaresAdd(y);
            possibleDownRoads(x + 1, y);
        }

    }

    public boolean findPossibleMoves(int x, int y) {

        boolean found = false;

        for (int i = 0; i < (highlightedSquares.size() - 1); i += 2) {
            if (highlightedSquares.get(i) == x) {
                if (highlightedSquares.get(i + 1) == y) {
                    this.x = x;
                    this.y = y;
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public void possibleMove(int x, int y) {

        if (findPossibleMoves(x, y)) {
            setPosition(this.x, this.y);
        }
        squareListClear();
        canvas.repaint();
    }

    public void Hover(int x, int y) {

        if (findPossibleMoves(x, y)) {
            highlightedCircle.add(this.x);
            highlightedCircle.add(this.y);
        }
        canvas.repaint();

    }

    public void highlightedSquaresAdd(int x) {
        this.highlightedSquares.add(x);
    }

    public int highlightedSquaresSize() {
        return highlightedSquares.size();
    }

    public int getHighlightedSquares(int x) {
        return highlightedSquares.get(x);
    }

    public int highlightedCircleSize() {
        return highlightedCircle.size();
    }

    public int getHighlightedCircle(int x) {
        return highlightedCircle.get(x);
    }

    public void squareListClear() {
        this.highlightedSquares.clear();
    }

    public void circleListClear() {
        this.highlightedCircle.clear();
    }

}
