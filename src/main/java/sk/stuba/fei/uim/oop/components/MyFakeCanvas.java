package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MyFakeCanvas extends JPanel {

    private MyFrame frame;
    private Player player;
    private int x;
    private int y;
    private final int mazeWidth;
    private final int mazeLength;
    private Maze maze;


    public Player getPlayer() {
        return player;
    }

    public Maze getMaze() {
        return maze;
    }

    public MyFakeCanvas(MyFrame frame, int x, int y) {

        this.frame = frame;
        this.mazeWidth = x;
        this.mazeLength = y;
        this.maze = new Maze(x, y);
        player = new Player(1, 1,this);
        maze.mazeCreation(1, 1);

    }

    public void processKeyEvent(String move) {

        y = player.getYPosition();
        x = player.getXPosition();

        switch (move) {
            case "up":
                player.canMoveVertically(x, y, -1);
                break;
            case "down":
                player.canMoveVertically(x, y, 1);
                break;
            case "left":
                player.canMoveHorizontally(x, y, -1);
                break;
            default:
                player.canMoveHorizontally(x, y, 1);

        }
        repaint();

    }

    protected void processKeyEvent(KeyEvent e) {

        player.listClear();
        y = player.getYPosition();
        x = player.getXPosition();

        if (e.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.canMoveHorizontally(x, y, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.canMoveHorizontally(x, y, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.canMoveVertically(x, y, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.canMoveVertically(x, y, -1);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(90, 60);

        for (int row = 0; row < mazeWidth; row++) {
            for (int col = 0; col < mazeLength; col++) {
                Color c;
                switch (maze.getSquare(row, col)) {
                    case 3:
                    case 1:
                        c = Color.BLACK;
                        break;
                    case 2:
                        c = Color.CYAN;
                        break;
                    case 0:
                        c = Color.WHITE;
                        break;
                    default:
                        c = Color.MAGENTA;
                }
                g.setColor(c);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        g.setColor(Color.ORANGE);
        g.fillOval(player.getYPosition() * 30 + 1, player.getXPosition() * 30 + 1, 28, 28);

        for (int i = 0; i < player.highlightedSquaresSize(); i += 2) {
            int listX = player.getHighlightedSquares(i);
            int listY = player.getHighlightedSquares(i + 1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30, 30, 30);
        }

        if(player.superHighlightedSquareSize()>0){
            g.setColor(Color.GREEN);
            g.fillRect(player.getSuperHighlightedSquare(1)*30,player.getSuperHighlightedSquare(0)*30,30,30);
            player.superListClear();
        }

    }

    public void won() {
        System.out.println("you won");
        frame.getPanel().won();
        restart(false);
    }

    public void restart(boolean resetLabel) {
        if (resetLabel) frame.getPanel().resetNumberOfWins();
        player.setPosition(1, 1);
        maze.mazeCreation(1, 1);
        repaint();
    }

}
