package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyCanvas extends Canvas {

    MyFrame frame;
    Player player = new Player(1,1);
    int x;
    int y;
    boolean[] freeToGo = new boolean[4];
    Random rand = new Random();
    private List<Integer> list = new ArrayList<Integer>();
    private int[][] maze = new int[15][15];

    private final int[][] baseMaze =
            {       {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,0,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,9,9,9,9,9,9,9,9,9,9,9,9,9,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}

            };

    void mazeCreation(){
        for(int i = 0; i < maze.length;i++){
            maze[i] = baseMaze[i].clone();
        }
        int actX = 1;
        int actY = 1;
        int randDirection;
        int randNumber;
        while(true) {

            maze[actX][actY] = 0;
            randDirection = howManyPosibleRouts(actX,actY);
            if(randDirection==0){
                maze[actX][actY]=2;
                break;
            }
            part_of_code:
            while (true){
                randNumber = rand.nextInt(4);

                for(int i = 0; i<4; i++){
                    if((freeToGo[i]==true)&&(randNumber==i)){
                        if(i == 0){
                            makeWalls(actX,actY);
                            actX--;
                            break part_of_code;
                        }
                        else if(i == 1){
                            makeWalls(actX,actY);
                            actX++;
                            break part_of_code;
                        }
                        else if(i == 2){
                            makeWalls(actX,actY);
                            actY--;
                            break part_of_code;
                        }
                        else {
                            makeWalls(actX,actY);
                            actY++;
                            break part_of_code;
                        }
                    }
                }
            }

        }
    }

    void makeWalls(int x, int y){

        if(freeToGo[0])maze[x-1][y]=1;
        if(freeToGo[1])maze[x+1][y]=1;
        if(freeToGo[2])maze[x][y-1]=1;
        if(freeToGo[3])maze[x][y+1]=1;


    }

    int howManyPosibleRouts(int x, int y){

        for(int i =0; i < 4; i++){
            freeToGo[i] = false;
        }

        int pocet = 0;
        if(maze[x-1][y]==9){pocet++;freeToGo[0] = true;}
        if(maze[x+1][y]==9){pocet++;freeToGo[1] = true;}
        if(maze[x][y-1]==9){pocet++;freeToGo[2] = true;}
        if(maze[x][y+1]==9){pocet++;freeToGo[3] = true;}
        return pocet;

    }

    public MyCanvas(MyFrame frame) {

        mazeCreation();
        this.frame = frame;

    }

    public void processKeyEvent(String move) {

        y = player.getyPosition();
        x = player.getxPosition();

        switch (move){
            case "up":
                canMoveVerticaly(x,y,-1);
                break;
            case "down":
                canMoveVerticaly(x,y,1);
                break;
            case "left":
                canMoveHorizontaly(x,y,-1);
                break;
            default:
                canMoveHorizontaly(x,y,1);

        }
        repaint();

    }

    protected void processKeyEvent(KeyEvent e) {

        y = player.getyPosition();
        x = player.getxPosition();

        if(e.getID() != KeyEvent.KEY_PRESSED){
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            canMoveHorizontaly(x,y,1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            canMoveHorizontaly(x,y,-1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            canMoveVerticaly(x,y,1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            canMoveVerticaly(x,y,-1);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.translate(90,60);

        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[0].length; col++){
                Color c;
                switch (maze[row][col]){
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
        g.fillOval(player.getyPosition()*30+1, player.getxPosition()*30+1,28,28);

        for(int i = 0; i <list.size(); i+=2){
            int listX = list.get(i);
            int listY = list.get(i+1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30,30,30);
        }


    }

    public void canMoveVerticaly(int x, int y,int index){

        if(maze[x+index][y]==0){
            player.moveVerticaly(index);
        }
        else if(maze[x+index][y]==2){
            player.moveVerticaly(index);
            Won();
        }
    }
    public void canMoveHorizontaly(int x, int y, int index){

        if(maze[x][y+index]==0){
            player.moveHorizontaly(index);
        }
        else if(maze[x][y+index]==2){
            player.moveHorizontaly(index);
            Won();
        }

    }
    public void Won(){
        System.out.println("you won");
        frame.won();
        restart(false);
    }
    public void showPosibleRoads(){
        this.y = player.getyPosition();
        this.x = player.getxPosition();

        posibleDownRoads(x,y);
        posibleLeftRoads(x,y);
        posibleRightRoads(x,y);
        posibleUpRoads(x,y);

    }
    void posibleLeftRoads(int x, int y){
        if(maze[x][y-1]==0){
          list.add(x);
            list.add(y-1);
            posibleLeftRoads(x,y-1);
        }
    }
    void posibleRightRoads(int x, int y){
        if(maze[x][y+1]==0){
            list.add(x);
            list.add(y+1);
            posibleRightRoads(x,y+1);
        }
    }
    void posibleUpRoads(int x, int y){
        if(maze[x-1][y]==0){
            list.add(x-1);
            list.add(y);
            posibleUpRoads(x-1,y);
        }
    }
    void posibleDownRoads(int x, int y){
        if(maze[x+1][y]==0){
            list.add(x+1);
            list.add(y);
            posibleDownRoads(x+1,y);
        }
    }

    public void posibleMove(int x, int y){
        for(int i=0;i<(list.size()-1);i++){
            if(list.get(i)==x){
                if(list.get(i+1)==y){
                    player.setPosition(x,y);
                    System.out.println("realne pohol si sa");
                    break;
                }
            }
        }
        list.clear();
        repaint();
    }

    public void restart(boolean resetLabel) {
        if(resetLabel)frame.resetNumberOfWins();
        player.setPosition(1,1);
        mazeCreation();
        repaint();
    }
}
