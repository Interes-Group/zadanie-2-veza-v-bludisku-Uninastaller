package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.maze.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyFakeCanvas extends JPanel {

    private MyFrame frame;
    private Player player;
    private int x;
    private int y;
    private final int mazeWidth;
    private final int mazeLength;
    private List<Move> moves;
    private Maze maze;
    private final String


    public Player getPlayer() {
        return player;
    }

    public Maze getMaze() {
        return maze;
    }

    public MyFakeCanvas(MyFrame frame, int x, int y) {

        this.frame = frame;
        this.mazeLength = x;
        this.mazeWidth = y;
        this.maze = new Maze(x, y);
        player = new Player(1, 1, this);
        moves = new ArrayList<>();
        moves.add(new VerticalMove());
        moves.add(new HorizontalMove());


    }

    public void listenerHandler(String move) {

        y = player.getYPosition();
        x = player.getXPosition();


        moves.get(0).move(move,x,y);

        switch (move) {
            case "38":
            case "↑":
                player.canMoveVertically(x, y, -1);
                break;
            case "40":
            case "↓":
                player.canMoveVertically(x, y, 1);
                break;
            case "37":
            case "←":
                player.canMoveHorizontally(x, y, -1);
                break;
            case "39":
            case "→":
                player.canMoveHorizontally(x, y, 1);
                break;
            default:
                restart(true);

        }
        player.squareListClear();
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        paintMaze(g);
        paintPlayer(g);
        paintHighlightedSquares(g);
        paintHighlightedCircle(g);

    }

    void paintMaze(Graphics g){

        g.translate(90, 60);

        for (int row = 0; row < mazeLength; row++) {
            for (int col = 0; col < mazeWidth; col++) {
                Color c;
                switch (maze.getSquare(row, col)) {
                    case 9:
                        c = Color.BLACK;
                        break;
                    case 2:
                        c = Color.CYAN;
                        break;
                    default:
                        c = Color.WHITE;
                }
                g.setColor(c);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

    }

    void paintPlayer(Graphics g){

        g.setColor(Color.ORANGE);
        g.fillOval(player.getYPosition() * 30 + 1, player.getXPosition() * 30 + 1, 28, 28);

    }

    void paintHighlightedSquares(Graphics g){

        for (int i = 0; i < player.highlightedSquaresSize(); i += 2) {
            int listX = player.getHighlightedSquares(i);
            int listY = player.getHighlightedSquares(i + 1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30, 30, 30);
        }

    }

    void paintHighlightedCircle(Graphics g){

        if (player.highlightedCircleSize() > 0) {
            g.setColor(Color.GREEN);
            g.fillOval(player.getHighlightedCircle(1) * 30 + 4, player.getHighlightedCircle(0) * 30 + 4, 22, 22);
            player.circleListClear();
        }

    }

    public void won() {
        frame.getPanel().won(false);
        restart(false);
    }

    public void restart(boolean resetLabel) {
        if (resetLabel) frame.getPanel().won(true);
        player.setPosition(1, 1);
        maze.mazeCreation(1, 1);
        repaint();
    }

}
