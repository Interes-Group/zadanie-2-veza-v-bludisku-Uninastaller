package sk.stuba.fei.uim.oop.maze;

public class Player {
    private int xPosition;
    private int yPosition;

    public Player(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void moveHorizontaly(int index){
        yPosition += index;
    }
    public void moveVerticaly(int index){
        xPosition += index;
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
