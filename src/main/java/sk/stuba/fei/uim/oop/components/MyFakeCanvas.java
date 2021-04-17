package sk.stuba.fei.uim.oop.components;

import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MyFakeCanvas extends JPanel {

    private MyFrame frame;
    private Player player;
    private int x;
    private int y;
    private final int mazeWidth;
    private final int mazeLength;
    private boolean[] freeToGo = new boolean[4];
    private List<Integer> highlightedSquares = new ArrayList<Integer>();
    private List<Integer> superHighlightedSquare = new ArrayList<Integer>();
    private Maze maze;

    public void highlightedSquaresAdd(int x){
        this.highlightedSquares.add(x);
    }

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
        this.maze = new Maze(x, y, freeToGo);
        player = new Player(1, 1,this);
        maze.mazeCreation(1, 1, true);

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

        listClear();
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

        for (int i = 0; i < highlightedSquares.size(); i += 2) {
            int listX = highlightedSquares.get(i);
            int listY = highlightedSquares.get(i + 1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30, 30, 30);
        }

        if(superHighlightedSquare.size()>0){
            g.setColor(Color.GREEN);
            g.fillRect(superHighlightedSquare.get(1)*30,superHighlightedSquare.get(0)*30,30,30);
            superHighlightedSquare.clear();
        }

    }

    public void won() {
        System.out.println("you won");
        frame.getPanel().won();
        restart(false);
    }

    public void possibleMove(int x, int y){

        if(findPossibleMoves(x,y)){
            player.setPosition(this.x, this.y);
            if (maze.getSquare(player.getXPosition(), player.getYPosition()) == 2)
                won();
        }
        listClear();
        repaint();
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

    public void Hover(int x, int y){

        if(findPossibleMoves(x,y)){
            superHighlightedSquare.add(this.x);
            superHighlightedSquare.add(this.y);
        }
        repaint();

    }

    public void restart(boolean resetLabel) {
        if (resetLabel) frame.getPanel().resetNumberOfWins();
        player.setPosition(1, 1);
        maze.mazeCreation(1, 1, true);
        repaint();
    }

    public void listClear() {
        this.highlightedSquares.clear();
    }
}
