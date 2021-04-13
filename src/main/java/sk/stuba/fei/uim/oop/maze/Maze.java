package sk.stuba.fei.uim.oop.maze;

import java.util.Random;

public class Maze {

    private int[][] maze;
    private int[][] baseMaze;
    private boolean[] freeToGo;
    private Random rand = new Random();

    public Maze(int x, int y, boolean[] freeToGo) {
        this.freeToGo = freeToGo;
        baseMaze = new int[x][y];
        for(int i = 0; i<x;i++){
            for(int j =0; j<y;j++){
                if((i==0)||(i==(x-1)))baseMaze[i][j]=3;
                else if((j==0)||(j==(y-1)))baseMaze[i][j]=3;
                else baseMaze[i][j]=9;
            }

        }
        maze = new int[x][y];
    }

    public void newMaze(){
        for (int i = 0; i < maze.length; i++) {
            maze[i] = baseMaze[i].clone();
        }
    }
    public void setPath(int x, int y)
    {
        maze[x][y] = 0;
    }

    public int howManyPossibleRouts(int x, int y, int indexOfLookingFor){

        for(int i =0; i < 4; i++){
            freeToGo[i] = false;
        }

        int count = 0;
        if(maze[x-1][y]==indexOfLookingFor){count++;freeToGo[0] = true;}
        if(maze[x+1][y]==indexOfLookingFor){count++;freeToGo[1] = true;}
        if(maze[x][y-1]==indexOfLookingFor){count++;freeToGo[2] = true;}
        if(maze[x][y+1]==indexOfLookingFor){count++;freeToGo[3] = true;}
        return count;

    }

    void makeWalls(int x, int y){

        if(freeToGo[0])maze[x-1][y]=1;
        if(freeToGo[1])maze[x+1][y]=1;
        if(freeToGo[2])maze[x][y-1]=1;
        if(freeToGo[3])maze[x][y+1]=1;


    }

    public int[] lookingForWay(int actX, int actY){

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
        return new int[]{actX,actY};
    }

    public void openForNextWay(int x, int y){

        int[] upDownLeftRight = {0,0,0,0};
        howManyPossibleRouts(x,y,1);
        boolean[] freeToGo2 = freeToGo.clone();

        for(int i = 0; i<4; i++){

            if(freeToGo2[i]){
                if(i == 0){
                    upDownLeftRight[i] = howManyPossibleRouts(x-1,y,0);
                }
                else if(i == 1){
                    upDownLeftRight[i] = howManyPossibleRouts(x+1,y,0);
                }
                else if(i == 2){
                    upDownLeftRight[i] = howManyPossibleRouts(x,y-1,0);
                }
                else {
                    upDownLeftRight[i] = howManyPossibleRouts(x,y+1,0);
                }
            }
        }
        upDownLeftRight = decideWhichBlockWillBeDeleted(upDownLeftRight);
        for(int i = 0;i<4;i++){
            if(upDownLeftRight[i]!=0){
                switch (i){
                    case 0:
                        maze[x-1][y]=0;
                        break;
                    case 1:
                        maze[x+1][y]=0;
                        break;
                    case 2:
                        maze[x][y-1]=0;
                        break;
                    default:
                        maze[x][y+1]=0;
                        break;
                }break;
            }
        }

    }
    int[] decideWhichBlockWillBeDeleted(int[] upDownLeftRight){

        for(int i = 0;i<4;i++){
            if(upDownLeftRight[i] == 0)upDownLeftRight[i]=10;
        }

        int minNumbersOfWays = 3;
        int x = 5;
        for(int i = 0; i<4; i++){

            if(upDownLeftRight[i]<=minNumbersOfWays){
                minNumbersOfWays = upDownLeftRight[i];
                upDownLeftRight[i] = ++x;
            }
        }

        for(int i = 0;i<4;i++){
            if(upDownLeftRight[i] == 10)upDownLeftRight[i]=0;
        }

        for(int i = 0; i<4;i++){
            if(upDownLeftRight[i]<x){
                upDownLeftRight[i]=0;
            }
            else upDownLeftRight[i] = 1;
        }
        return upDownLeftRight;
    }

    public void setSquare(int x, int y, int index){
        maze[x][y] = index;
    }

    public int getSquare(int x, int y){
        return maze[x][y];
    }
}
