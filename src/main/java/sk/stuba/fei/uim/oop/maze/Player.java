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
    private List<Integer> superHighlightedSquare = new ArrayList<Integer>();

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
            highlightedSquaresAdd(x);
            highlightedSquaresAdd(y - 1);
            possibleLeftRoads(x, y - 1);
        }
    }

    void possibleRightRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x, y + 1) == 0) || (canvas.getMaze().getSquare(x, y + 1) == 2)) {
            highlightedSquaresAdd(x);
            highlightedSquaresAdd(y + 1);
            possibleRightRoads(x, y + 1);
        }
    }

    void possibleUpRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x - 1, y) == 0) || (canvas.getMaze().getSquare(x - 1, y) == 2)) {
            highlightedSquaresAdd(x - 1);
            highlightedSquaresAdd(y);
            possibleUpRoads(x - 1, y);
        }
    }

    void possibleDownRoads(int x, int y) {
        if ((canvas.getMaze().getSquare(x + 1, y) == 0) || (canvas.getMaze().getSquare(x + 1, y) == 2)) {
            highlightedSquaresAdd(x + 1);
            highlightedSquaresAdd(y);
            possibleDownRoads(x + 1, y);
        }
    }

    public boolean findPossibleMoves(int x, int y) {

        boolean found = false;

        for (int i = 0; i < (highlightedSquares.size()-1); i+=2) {
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

    public void possibleMove(int x, int y){

        if(findPossibleMoves(x,y)){
            canvas.getPlayer().setPosition(this.x, this.y);
            if (canvas.getMaze().getSquare(canvas.getPlayer().getXPosition(),canvas.getPlayer().getYPosition()) == 2)
                canvas.won();
        }
        listClear();
        canvas.repaint();
    }

    public void Hover(int x, int y){

        if(findPossibleMoves(x,y)){
            superHighlightedSquare.add(this.x);
            superHighlightedSquare.add(this.y);
        }
        canvas.repaint();

    }

    public void highlightedSquaresAdd(int x){
        this.highlightedSquares.add(x);
    }

    public int highlightedSquaresSize(){
        return highlightedSquares.size();
    }

    public int getHighlightedSquares(int x){
        return highlightedSquares.get(x);
    }


    public int superHighlightedSquareSize(){
        return superHighlightedSquare.size();
    }

    public int getSuperHighlightedSquare(int x){
        return superHighlightedSquare.get(x);
    }

    public void listClear() {
        this.highlightedSquares.clear();
    }

    public void superListClear() {
        this.superHighlightedSquare.clear();
    }

}
