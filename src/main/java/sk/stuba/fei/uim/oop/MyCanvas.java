package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends Canvas {

    MyFrame frame;
    Player player = new Player(1,1);
    int x;
    int y;
    int actX;
    int actY;
    int maze_width;
    int maze_length;
    boolean[] freeToGo = new boolean[4];
//    Random rand = new Random();
    private List<Integer> highlightedSquares = new ArrayList<Integer>();
    Maze maze;
//    private int[][] maze;
//
//    private final int[][] baseMaze;

    void mazeCreation(int x, int y, boolean newMaze){
        actX = x;
        actY = y;
        if(newMaze) {
            maze.newMaze();
        }

        int randDirection;
        while(true) {

            maze.setPath(actX,actY);
//            maze[actX][actY] = 0;
            randDirection = maze.howManyPosibleRouts(actX,actY,9);
            if(randDirection==0){
                if(newMaze)maze.setSquare(actX,actY,2);
                for(int i = 0; i< maze_width;i++){
                    for(int j = 0; j< maze_length; j++){
                        if(maze.getSquare(i,j)==9){

                            if(maze.howManyPosibleRouts(i,j,0)==0)maze.openForNextWay(i,j);
                            maze.setSquare(i,j,0);
//                            maze.maze[i][j]=0;
                            mazeCreation(i,j,false);
                        }
                    }
                }
                break;
            }
            int[] points = maze.hladajCestu(actX,actY);
            actX = points[0];
            actY = points[1];
        }
    }

//    void openForNextWay(int x, int y){
//
//        int[] upDownLeftRight = {0,0,0,0};
//        int pocet = maze.howManyPosibleRouts(x,y,1);
//        boolean[] freeToGo2 = freeToGo.clone();
//
//        for(int i = 0; i<4; i++){
//
//            if(freeToGo2[i]){
//                if(i == 0){
//                    upDownLeftRight[i] = maze.howManyPosibleRouts(x-1,y,0);
////                    maze[x-1][y]=0;
////                    break;
//                }
//                else if(i == 1){
//                    upDownLeftRight[i] = maze.howManyPosibleRouts(x+1,y,0);
////                    maze[x+1][y]=0;
////                    break;
//                }
//                else if(i == 2){
//                    upDownLeftRight[i] = maze.howManyPosibleRouts(x,y-1,0);
////                    maze[x][y-1]=0;
////                    break;
//                }
//                else {
//                    upDownLeftRight[i] = maze.howManyPosibleRouts(x,y+1,0);
////                    maze[x][y+1]=0;
////                    break;
//                }
//            }
//        }
//        upDownLeftRight = decideWhichBlockWillBeDeleted(upDownLeftRight);
//        for(int i = 0;i<4;i++){
//            if(upDownLeftRight[i]!=0){
//                switch (i){
//                    case 0:
//                        maze.maze[x-1][y]=0;
//                        break;
//                    case 1:
//                        maze.maze[x+1][y]=0;
//                        break;
//                    case 2:
//                        maze.maze[x][y-1]=0;
//                        break;
//                    default:
//                        maze.maze[x][y+1]=0;
//                        break;
//                }break;
//            }
//        }
//
//    }
//    int[] decideWhichBlockWillBeDeleted(int[] upDownLeftRight){
//
//        for(int i = 0;i<4;i++){
//            if(upDownLeftRight[i] == 0)upDownLeftRight[i]=10;
//        }
//
//        int minNumbersOfWays = 3;
//        int x = 5;
//        for(int i = 0; i<4; i++){
//
//            if(upDownLeftRight[i]<=minNumbersOfWays){
//                minNumbersOfWays = upDownLeftRight[i];
//                upDownLeftRight[i] = ++x;
//            }
//        }
//
//        for(int i = 0;i<4;i++){
//            if(upDownLeftRight[i] == 10)upDownLeftRight[i]=0;
//        }
//
//        for(int i = 0; i<4;i++){
//            if(upDownLeftRight[i]<x){
//                upDownLeftRight[i]=0;
//            }
//            else upDownLeftRight[i] = 1;
//        }
//        return upDownLeftRight;
//    }
//    void hladajCestu(){
//
//        int randNumber;
//
//        part_of_code:
//        while (true){
//            randNumber = rand.nextInt(4);
//
//            for(int i = 0; i<4; i++){
//                if((freeToGo[i])&&(randNumber==i)){
//                    if(i == 0){
//                        maze.makeWalls(actX,actY);
//                        actX--;
//                        break part_of_code;
//                    }
//                    else if(i == 1){
//                        maze.makeWalls(actX,actY);
//                        actX++;
//                        break part_of_code;
//                    }
//                    else if(i == 2){
//                        maze.makeWalls(actX,actY);
//                        actY--;
//                        break part_of_code;
//                    }
//                    else {
//                        maze.makeWalls(actX,actY);
//                        actY++;
//                        break part_of_code;
//                    }
//                }
//            }
//        }
//
//    }

//    void makeWalls(int x, int y){
//
//        if(freeToGo[0])maze[x-1][y]=1;
//        if(freeToGo[1])maze[x+1][y]=1;
//        if(freeToGo[2])maze[x][y-1]=1;
//        if(freeToGo[3])maze[x][y+1]=1;
//
//
//    }

//    int howManyPosibleRouts(int x, int y,int indexOfLookingFor){
//
//        for(int i =0; i < 4; i++){
//            freeToGo[i] = false;
//        }
//
//        int pocet = 0;
//        if(maze[x-1][y]==indexOfLookingFor){pocet++;freeToGo[0] = true;}
//        if(maze[x+1][y]==indexOfLookingFor){pocet++;freeToGo[1] = true;}
//        if(maze[x][y-1]==indexOfLookingFor){pocet++;freeToGo[2] = true;}
//        if(maze[x][y+1]==indexOfLookingFor){pocet++;freeToGo[3] = true;}
//        return pocet;
//
//    }

    public MyCanvas(MyFrame frame, int x, int y) {

//        baseMaze = new int[x][y];
//        for(int i = 0; i<x;i++){
//            for(int j =0; j<y;j++){
//                if((i==0)||(i==(x-1)))baseMaze[i][j]=3;
//                else if((j==0)||(j==(y-1)))baseMaze[i][j]=3;
//                else baseMaze[i][j]=9;
//            }
//
//        }
//        maze = new int[x][y];
        this.maze_width = x;
        this.maze_length = y;
        this.maze = new Maze(x,y, freeToGo);
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

        for(int row = 0; row < maze_width; row++){
            for(int col = 0; col < maze_length; col++){
                Color c;
                switch (maze.getSquare(row,col)){
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

        for(int i = 0; i < highlightedSquares.size(); i+=2){
            int listX = highlightedSquares.get(i);
            int listY = highlightedSquares.get(i+1);
            g.setColor(Color.RED);
            g.drawRect(listY * 30, listX * 30,30,30);
        }


    }

    public void canMoveVerticaly(int x, int y,int index){

        if(maze.getSquare(x+index,y)==0){
            player.moveVerticaly(index);
        }
        else if(maze.getSquare(x+index,y)==2){
            player.moveVerticaly(index);
            won();
        }
    }
    public void canMoveHorizontaly(int x, int y, int index){

        if(maze.getSquare(x,y+index)==0){
            player.moveHorizontaly(index);
        }
        else if(maze.getSquare(x,y+index)==2){
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
        if((maze.getSquare(x,y-1)==0)||(maze.getSquare(x,y-1)==2)){
            highlightedSquares.add(x);
            highlightedSquares.add(y-1);
            posibleLeftRoads(x,y-1);
        }
    }
    void posibleRightRoads(int x, int y){
        if((maze.getSquare(x,y+1)==0)||(maze.getSquare(x,y+1)==2)){
            highlightedSquares.add(x);
            highlightedSquares.add(y+1);
            posibleRightRoads(x,y+1);
        }
    }
    void posibleUpRoads(int x, int y){
        if((maze.getSquare(x-1,y)==0)||(maze.getSquare(x-1,y)==2)){
            highlightedSquares.add(x-1);
            highlightedSquares.add(y);
            posibleUpRoads(x-1,y);
        }
    }
    void posibleDownRoads(int x, int y){
        if((maze.getSquare(x+1,y)==0)||(maze.getSquare(x+1,y)==2)){
            highlightedSquares.add(x+1);
            highlightedSquares.add(y);
            posibleDownRoads(x+1,y);
        }
    }

    public void posibleMove(int x, int y){
        for(int i = 0; i<(highlightedSquares.size()-1); i++){
            if(highlightedSquares.get(i)==x){
                if(highlightedSquares.get(i+1)==y){
                    player.setPosition(x,y);
                    System.out.println("realne pohol si sa");
                    if(maze.getSquare(player.getxPosition(),player.getyPosition())==2)
                    won();
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
        this.highlightedSquares.clear();
    }
}
