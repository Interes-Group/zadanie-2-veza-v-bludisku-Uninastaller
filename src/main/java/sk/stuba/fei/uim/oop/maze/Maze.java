package sk.stuba.fei.uim.oop.maze;

import java.util.ArrayList;
import java.util.Collections;

public class Maze {

    private int[][] maze;
    private int[][] baseMaze;
    private int actX;
    private int actY;
    private int mazeWidth;
    private int mazeLength;

    public Maze(int width, int length) {

        mazeWidth = width;
        mazeLength = length;
        baseMaze = new int[width][length];
        for(int i = 0; i<width;i++){
            for(int j =0; j<length;j++){
                baseMaze[i][j]=9;
            }
        }
        maze = new int[width][length];

    }

    public void newMaze(){

        for (int i = 0; i < maze.length; i++) {
            maze[i] = baseMaze[i].clone();
        }

    }



    /////////////////////////////////

    public Integer[] generateRandomDirections() {
        ArrayList<Integer> directions = new ArrayList<Integer>();
        for (int i = 1; i < 5; i++)
            directions.add(i);
        Collections.shuffle(directions);

        return directions.toArray(new Integer[4]);
    }



//    public int howManyPossibleRouts(int x, int y, int indexOfLookingFor){
//
//        for(int i =0; i < 4; i++){
//            freeToGo[i] = false;
//        }
//
//        int count = 0;
//        if(maze[x-1][y]==indexOfLookingFor){count++;freeToGo[0] = true;}
//        if(maze[x+1][y]==indexOfLookingFor){count++;freeToGo[1] = true;}
//        if(maze[x][y-1]==indexOfLookingFor){count++;freeToGo[2] = true;}
//        if(maze[x][y+1]==indexOfLookingFor){count++;freeToGo[3] = true;}
//        return count;
//
//    }
//
//    void makeWalls(int x, int y){
//
//        if(freeToGo[0])maze[x-1][y]=1;
//        if(freeToGo[1])maze[x+1][y]=1;
//        if(freeToGo[2])maze[x][y-1]=1;
//        if(freeToGo[3])maze[x][y+1]=1;
//
//
//    }
//
//    public int[] lookingForWay(int actX, int actY){
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
//                        makeWalls(actX,actY);
//                        actX--;
//                        break part_of_code;
//                    }
//                    else if(i == 1){
//                        makeWalls(actX,actY);
//                        actX++;
//                        break part_of_code;
//                    }
//                    else if(i == 2){
//                        makeWalls(actX,actY);
//                        actY--;
//                        break part_of_code;
//                    }
//                    else {
//                        makeWalls(actX,actY);
//                        actY++;
//                        break part_of_code;
//                    }
//                }
//            }
//        }
//        return new int[]{actX,actY};
//    }
//
//    public void openForNextWay(int x, int y){
//
//        int[] upDownLeftRight = {0,0,0,0};
//        howManyPossibleRouts(x,y,1);
//        boolean[] freeToGo2 = freeToGo.clone();
//
//        for(int i = 0; i<4; i++){
//
//            if(freeToGo2[i]){
//                if(i == 0){
//                    upDownLeftRight[i] = howManyPossibleRouts(x-1,y,0);
//                }
//                else if(i == 1){
//                    upDownLeftRight[i] = howManyPossibleRouts(x+1,y,0);
//                }
//                else if(i == 2){
//                    upDownLeftRight[i] = howManyPossibleRouts(x,y-1,0);
//                }
//                else {
//                    upDownLeftRight[i] = howManyPossibleRouts(x,y+1,0);
//                }
//            }
//        }
//        upDownLeftRight = decideWhichBlockWillBeDeleted(upDownLeftRight);
//
//        for(int i = 0;i<4;i++){
//            if(upDownLeftRight[i]!=0){
//                switch (i){
//                    case 0:
//                        maze[x-1][y]=0;
//                        break;
//                    case 1:
//                        maze[x+1][y]=0;
//                        break;
//                    case 2:
//                        maze[x][y-1]=0;
//                        break;
//                    default:
//                        maze[x][y+1]=0;
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
//
//    }

    public int getSquare(int x, int y){
        return maze[x][y];
    }

    public void mazeCreation(int x, int y) {

        actX = x;
        actY = y;
        newMaze();
        depthFirst(actX,actY);

        setFinish();




//
//        actX = x;
//        actY = y;
//        if (newMaze) {
//            newMaze();
//        }
//
//        int randDirection;
//        while (true) {
//            vipis();
//            setSquare(actX, actY);
//            randDirection = howManyPossibleRouts(actX, actY, 9);
//            if (randDirection == 0) {
//                if (newMaze) setSquare(actX, actY, 2);
//                for (int i = 0; i < mazeWidth; i++) {
//                    for (int j = 0; j < mazeLength; j++) {
//                        if (getSquare(i, j) == 9) {
//                            if (howManyPossibleRouts(i, j, 0) == 0) openForNextWay(i, j);
//                            setSquare(i, j);
//                            mazeCreation(i, j, false);
//                        }
//                    }
//                }
//                break;
//            }
//            int[] points = lookingForWay(actX, actY);
//            actX = points[0];
//            actY = points[1];
//        }

    }

    void depthFirst(int actX, int actY){

        vipis();

        Integer[] directions = generateRandomDirections();

//        for (int i = 0; i < directions.length; i++) {
//
//            switch (directions[i]) {
//                case 1: // Up
//                    //　Whether 2 cells up is out or not
//                    if (actX - 2 <= 0)
//                        continue;
//                    if (maze[actX - 2][actY] == 9) {
//                        maze[actX - 2][actY] =  maze[actX - 1][actY] = 0;
//                        depthFirst(actX - 2, actY);
//                    }
//                    break;
//                case 2: // Right
//                    // Whether 2 cells to the right is out or not
//                    if (actY + 2 >= mazeWidth - 1)
//                        continue;
//                    if (maze[actX][actY + 2] == 9) {
//                        maze[actX][actY + 2] =  maze[actX][actY + 1] = 0;
//                        depthFirst(actX, actY + 2);
//                    }
//                    break;
//                case 3: // Down
//                    // Whether 2 cells down is out or not
//                    if (actX + 2 >= mazeLength - 1)
//                        continue;
//                    if (maze[actX + 2][actY] == 9) {
//                        maze[actX + 2][actY] = maze[actX + 1][actY] = 0;
//                        depthFirst(actX + 2, actY);
//                    }
//                    break;
//                case 4: // Left
//                    // Whether 2 cells to the left is out or not
//                    if (actY - 2 <= 0)
//                        continue;
//                    if (maze[actX][actY - 2] == 9) {
//                        maze[actX][actY - 2] = maze[actX][actY - 1] = 0;
//                        depthFirst(actX, actY - 2);
//                    }
//                    break;
//            }
//        }

        for (Integer direction : directions) {

            if ((direction == 1) && (!(actX - 2 <= 0)) && (maze[actX - 2][actY] == 9)) {
                maze[actX - 2][actY] = maze[actX - 1][actY] = 0;
                depthFirst(actX - 2, actY);
            }

            if ((direction == 2) && (!(actY + 2 >= mazeWidth - 1)) && (maze[actX][actY + 2] == 9)) {
                maze[actX][actY + 2] = maze[actX][actY + 1] = 0;
                depthFirst(actX, actY + 2);
            }
            if ((direction == 3) && (!(actX + 2 >= mazeLength - 1)) && (maze[actX + 2][actY] == 9)) {
                maze[actX + 2][actY] = maze[actX + 1][actY] = 0;
                depthFirst(actX + 2, actY);
            }
            if ((direction == 4) && (!(actY - 2 <= 0)) && (maze[actX][actY - 2] == 9)) {
                maze[actX][actY - 2] = maze[actX][actY - 1] = 0;
                depthFirst(actX, actY - 2);
            }

        }
    }


    void vipis(){
        System.out.println("\n\n\n\n");
        for(int i = 0; i< mazeLength; i++){
            for (int j = 0; j<mazeWidth;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

    }

    void setFinish(){

        int pocet;

        for(int i = 0; i < mazeWidth; i++){
            for(int j = 0; j < mazeLength; j++){
                if(maze[i][j]==0){
                    pocet = 0;
                    if(maze[i+1][j]==9)pocet++;
                    if(maze[i-1][j]==9)pocet++;
                    if(maze[i][j+1]==9)pocet++;
                    if(maze[i][j-1]==9)pocet++;
                    if(pocet==3) {
                        actX = i;
                        actY = j;
                    }
                }
            }
        }
        maze[actX][actY]=2;

    }

}
