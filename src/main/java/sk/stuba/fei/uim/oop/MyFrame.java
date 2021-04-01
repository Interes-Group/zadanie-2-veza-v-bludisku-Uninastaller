package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {

    Player player = new Player(1,1);
    private int[][] maze =
            {       {1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,0,1,0,1,0,1,0,0,0,0,0,1},
                    {1,0,1,0,0,0,1,0,1,1,1,0,1},
                    {1,0,0,0,1,1,1,0,0,0,0,0,1},
                    {1,0,1,0,0,0,0,0,1,1,1,0,1},
                    {1,0,1,0,1,1,1,0,1,0,0,0,1},
                    {1,0,1,0,1,0,0,0,1,1,1,0,1},
                    {1,0,1,0,1,1,1,0,1,0,1,0,1},
                    {1,0,0,0,0,0,0,0,0,0,1,2,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1}

            };

    public MyFrame(String label){
        super(label);
        this.setSize(640,480);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        this.setVisible(true);

//        addMouseListener(new MyMouseLogger());
//
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.LIGHT_GRAY);
//        this.add(BorderLayout.NORTH,panel);
//
//        JButton restart = new JButton("Restart");
//        panel.add(BorderLayout.PAGE_START,restart);
//        JButton left = new JButton("Left");
//        panel.add(left,BorderLayout.LINE_START);
//        JButton down = new JButton("Down");
//        panel.add(down,BorderLayout.CENTER);
//        JButton up = new JButton("Up");
//        panel.add(up,BorderLayout.NORTH);
//        JButton right = new JButton("right");
//        panel.add(right,BorderLayout.LINE_END);
//        restart.addActionListener(e -> System.out.println("click"));


    }

    @Override
    protected void processKeyEvent(KeyEvent e) {

        int y = player.getyPosition();
        int x = player.getxPosition();

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

        g.translate(50,50);

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
                    default:
                        c = Color.WHITE;
                }
                g.setColor(c);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        g.setColor(Color.ORANGE);
        g.fillOval(player.getyPosition()*30+1, player.getxPosition()*30+1,28,28);

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
    }
}
