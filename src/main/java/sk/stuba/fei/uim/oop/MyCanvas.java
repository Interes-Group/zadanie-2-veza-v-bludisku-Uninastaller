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
    int actX;
    int actY;
    boolean[] freeToGo = new boolean[4];
    Random rand = new Random();
    private List<Integer> list = new ArrayList<Integer>();
    private int[][] maze;

    private final int[][] baseMaze;

    void mazeCreation(int x, int y, boolean newMaze){
        actX = x;
        actY = y;
        if(newMaze) {
            for (int i = 0; i < maze.length; i++) {
                maze[i] = baseMaze[i].clone();
            }
        }

        int randDirection;
        while(true) {

            maze[actX][actY] = 0;
            randDirection = howManyPosibleRouts(actX,actY,9);
            if(randDirection==0){
                if(newMaze)maze[actX][actY]=2;
                for(int i = 0; i< maze.length;i++){
                    for(int j = 0; j< maze[0].length;j++){
                        if(maze[i][j]==9){
                            maze[i][j]=0;
                            openForNextWay(i,j);
                            mazeCreation(i,j,false);
                        }
                    }
                }
                break;
            }
            hladajCestu();

        }
    }
    void openForNextWay(int x, int y){

        int pocet = howManyPosibleRouts(x,y,1);

        int randNumber;

        part_of_code:
        while (true){
            randNumber = rand.nextInt(4);

            for(int i = 0; i<4; i++){
                if((freeToGo[i])&&(randNumber==i)){
                    if(i == 0){
                        maze[x-1][y]=0;
                        break part_of_code;
                    }
                    else if(i == 1){
                        maze[x+1][y]=0;
                        break part_of_code;
                    }
                    else if(i == 2){
                        maze[x][y-1]=0;
                        break part_of_code;
                    }
                    else {
                        maze[x][y+1]=0;
                        break part_of_code;
                    }
                }
            }
        }

    }
    void hladajCestu(){

        int randNumber;

        part_of_code:
        while (true){
            randNumber = rand.nextInt(4);

            for(int i = 0; i<4; i++){
                if((freeToGo[i])&&(randNumber==i)){
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

    void makeWalls(int x, int y){

        if(freeToGo[0])maze[x-1][y]=1;
        if(freeToGo[1])maze[x+1][y]=1;
        if(freeToGo[2])maze[x][y-1]=1;
        if(freeToGo[3])maze[x][y+1]=1;


    }

    int howManyPosibleRouts(int x, int y,int indexOfLookingFor){

        for(int i =0; i < 4; i++){
            freeToGo[i] = false;
        }

        int pocet = 0;
        if(maze[x-1][y]==indexOfLookingFor){pocet++;freeToGo[0] = true;}
        if(maze[x+1][y]==indexOfLookingFor){pocet++;freeToGo[1] = true;}
        if(maze[x][y-1]==indexOfLookingFor){pocet++;freeToGo[2] = true;}
        if(maze[x][y+1]==indexOfLookingFor){pocet++;freeToGo[3] = true;}
        return pocet;

    }

    public MyCanvas(MyFrame frame, int x, int y) {

        baseMaze = new int[x][y];
        for(int i = 0; i<x;i++){
            for(int j =0; j<y;j++){
                if((i==0)||(i==(x-1)))baseMaze[i][j]=3;
                else if((j==0)||(j==(y-1)))baseMaze[i][j]=3;
                else baseMaze[i][j]=9;
            }

        }
        maze = new int[x][y];
        mazeCreation(1,1,true);
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

        listClear();
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
            won();
        }
    }
    public void canMoveHorizontaly(int x, int y, int index){

        if(maze[x][y+index]==0){
            player.moveHorizontaly(index);
        }
        else if(maze[x][y+index]==2){
            player.moveHorizontaly(index);
            won();
        }

    }
    public void won(){
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
        if((maze[x][y-1]==0)||(maze[x][y-1]==2)){
          list.add(x);
            list.add(y-1);
            posibleLeftRoads(x,y-1);
        }
    }
    void posibleRightRoads(int x, int y){
        if((maze[x][y+1]==0)||(maze[x][y+1]==2)){
            list.add(x);
            list.add(y+1);
            posibleRightRoads(x,y+1);
        }
    }
    void posibleUpRoads(int x, int y){
        if((maze[x-1][y]==0)||(maze[x-1][y]==2)){
            list.add(x-1);
            list.add(y);
            posibleUpRoads(x-1,y);
        }
    }
    void posibleDownRoads(int x, int y){
        if((maze[x+1][y]==0)||(maze[x+1][y]==2)){
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
                    if(maze[player.getxPosition()][player.getyPosition()]==2)
                    won();;
                    break;
                }
            }
        }
        listClear();
        repaint();
    }

    public void restart(boolean resetLabel) {
        if(resetLabel)frame.resetNumberOfWins();
        player.setPosition(1,1);
        mazeCreation(1,1,true);
        repaint();
    }
    public void listClear(){
        this.list.clear();
    }
}
