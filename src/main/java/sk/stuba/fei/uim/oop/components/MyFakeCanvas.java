package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.listners.listenersHandlers.HorizontalMove;
import sk.stuba.fei.uim.oop.listners.listenersHandlers.Move;
import sk.stuba.fei.uim.oop.listners.listenersHandlers.VerticalMove;
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


    public Player getPlayer() {
        return player;
    }

    public Maze getMaze() {
        return maze;
    }

    public MyFakeCanvas(MyFrame frame, int x, int y) {

        setBackground(Color.GRAY);
        this.frame = frame;
        this.mazeLength = x;
        this.mazeWidth = y;
        this.maze = new Maze(x, y, this);
        player = new Player(1, 1, this);

        moves = new ArrayList<>();
        moves.add(new HorizontalMove(-1, player));
        moves.add(new VerticalMove(-1, player));
        moves.add(new HorizontalMove(1, player));
        moves.add(new VerticalMove(1, player));


    }

    public void listenerHandler(int index) {

        y = player.getYPosition();
        x = player.getXPosition();

        try {
            moves.get(index).move(x, y);
        } catch (IndexOutOfBoundsException e) {
            restart(true);
        }

        player.squareListClear();
        frame.getMyMouseLogger().setMouseMove(false);
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

    void paintMaze(Graphics g) {

        g.translate(90, 60);

        for (int row = 0; row < mazeLength; row++) {
            for (int col = 0; col < mazeWidth; col++) {

                Color c = maze.getSquare(row, col).setColor();
                g.setColor(c);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);

            }
        }

    }

    void paintPlayer(Graphics g) {

        g.setColor(Color.ORANGE);
        g.fillOval(player.getYPosition() * 30 + 1, player.getXPosition() * 30 + 1, 28, 28);

    }

    void paintHighlightedSquares(Graphics g) {

        int listX;
        int listY;

        for (int i = 0; i < player.highlightedSquaresSize(); i += 2) {

            listX = player.getHighlightedSquares(i);
            listY = player.getHighlightedSquares(i + 1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30, 30, 30);

        }

    }

    void paintHighlightedCircle(Graphics g) {

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
        maze.mazeCreation(1, 1, true);
        repaint();

    }

}
