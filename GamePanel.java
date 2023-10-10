import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;



class Piece{// every piece has 4 blocks
    
    int[] x = new int[4];//array to store the x coordinates of the blocks
    int[] y = new int[4];//array to store the y coordinates of the blocks
    //int type; // type can be A/B/C/D/E/F/G, each type signifies a different kind of piece
    char type;
    
}
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 200;
    static final int SCREEN_HEIGHT = 400;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static double DELAY = 150.0;
    static final int startx=5,starty=17;//The coordinates where a new piece is spawned
    int score = 0;
    
    static char grid[][] = new char [10][20];//the tetris grid
    static Piece ActivePiece=new Piece();//ActivePiece refers to the piece that is currently floating, and has not reached the bottom yet
    static Piece PieceOnHold = newPiece(0);//this is the piece thats on hold
    static boolean GameOver = false;
    static int Tetris=0;
    static boolean running;
    Random random;
    Timer timer;
    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();  
    }
    
    public void startGame(){
        running = true;
        newPiece(random.nextInt(7));
        
        timer = new Timer((int)DELAY,this);
        timer.start();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
            for(int i=0;i<SCREEN_WIDTH;i+=UNIT_SIZE){
                g.drawLine(i,0,i, SCREEN_HEIGHT);
            }
            for(int i=0;i<SCREEN_HEIGHT;i+=UNIT_SIZE){
                g.drawLine(0,i,SCREEN_WIDTH,i);
            }    
            
            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid.length;j++){
                    switch(grid[i][j]){
                        case 'x':
                                break;
                        case 'A':
                        case 'a':
                                g.setColor(Color.cyan);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'B':
                        case 'b':
                                g.setColor(Color.yellow);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'C':
                        case 'c':
                                
                                g.setColor(new Color(102,0,153));
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'D':
                        case 'd':
                                
                                g.setColor(Color.green);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'E':
                        case 'e':
                                
                                g.setColor(Color.red);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'F':
                        case 'f':
                                
                                g.setColor(Color.orange);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                        case 'G':
                        case 'g':
                                
                                g.setColor(Color.blue);
                                g.fillRect(i*UNIT_SIZE,j*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                                break;
                    }
                }
            }
        }else{
            gameOver(g);
        }
    }
    static Piece newPiece(int type){//function to return a piece object depending of a certain type
        Piece obj = new Piece();
        int j=0;
        int i=0;
        switch(type){
            case 0:
                    obj.type='0';
                    for(i=0;i<4;i++){
                        obj.x[i]=0;
                        obj.y[i]=0;
                    }
                    break;
            case 1: // Line
                    
                    
                    obj.type='A';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    j--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j+=2;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 2: // Square   BB
                    //          BB
                    //          
                    obj.type = 'B';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i--;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 3: // T piece       C
                    //              CCC
                    //             
                    obj.type='C';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i++;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j--;
                    i++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 4: //S piece   D
                    //          DD
                    //           D
                    obj.type='D';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i++;
                    j-=2;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 5: // Z piece
                    obj.type='E';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    i--;
                    j-=2;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 6: //L piece
                    obj.type='F';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i++;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i--;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            case 7: //Backwards L piece
                    obj.type='G';
                    obj.x[0]=startx+i;
                    obj.y[0]=starty+j;
                    i--;
                    obj.x[1]=startx+i;
                    obj.y[1]=starty+j;
                    i++;
                    j++;
                    obj.x[2]=startx+i;
                    obj.y[2]=starty+j;
                    j++;
                    obj.x[3]=startx+i;
                    obj.y[3]=starty+j;
                    break;
            default:
        }
        return obj;
    }
    static Piece newPiece(char type)
    {//same function to return a piece object but with overloading
        Piece obj = new Piece();
        switch(type){
            case '0': obj = newPiece(0);
                    break;
            case 'A': obj = newPiece(1);
                    break;
            case 'B': obj = newPiece(2);
                    break;
            case 'C': obj = newPiece(3);
                    break;
            case 'D': obj = newPiece(4);
                    break;
            case 'E': obj = newPiece(5);
                    break;
            case 'F': obj = newPiece(6);
                    break;
            case 'G': obj = newPiece(7);
                    break;
            default:
        }
        
        
        return obj;
    }
    static void clearGrid(){//This doesnt actually clear the whole grid, just clears active piece
        for(int i=0;i<4;i++){                        // This method is used to temporarily suspend the active piece, while it moves\rotates etc
            grid[ActivePiece.x[i]][ActivePiece.y[i]]='x';            
        }    
    }
    static void updateGrid(){//Function to update the grid after every move
        for(int i=0;i<4;i++){
            grid[ActivePiece.x[i]][ActivePiece.y[i]]=ActivePiece.type;
        }
    }
    static boolean isBlockedRight(){//check whether the ActivePiece has free space on its right
        boolean a = false;
        char x;
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]+1][ActivePiece.y[i]];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        
        
        return a;
    }
    static boolean isBlockedLeft(){//check for free space on the left of activepiece
        boolean a = false;
        char x;
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]-1][ActivePiece.y[i]];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        
        
        return a;
    }
    static boolean isAtBottom(){//check whether the active piece has reached the bottom, or has landed on top of another piece
        boolean a=false;
        char x;
        for(int i=0;i<4;i++){
            if(ActivePiece.y[i]==0){
                return true;
            }
        }
        for(int i=0;i<4;i++){
            x=grid[ActivePiece.x[i]][ActivePiece.y[i]-1];
            if(x!='x'&&x!=ActivePiece.type){
                return true;
            }
        }
        return a;
    }
    static void moveDown(){//move active piece one step down
        if(isAtBottom()) return;
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.y[i]--;
        }
        updateGrid();
    }
    public static void moveLeft(){//move to the left
        if(isBlockedLeft()){return;}
        for(int i=0;i<4;i++){
            if(ActivePiece.x[i]==0) return;
        }
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.x[i]--;
        }
        updateGrid();
    }
    public static void moveRight(){//Function to move the active Piece one space to the right
        if(isBlockedRight()){return;}
        for(int i=0;i<4;i++){
            if(ActivePiece.x[i]==9) return;
        }
        clearGrid();
        for(int i=0;i<4;i++){
            ActivePiece.x[i]++;
        }
        updateGrid();
    }
    static void Hold(){//function to exchange the active piece with the hold piece
                        // if hold is empty, active piece is just a new random piece
        Random rand = new Random();
        clearGrid();
        if(PieceOnHold.type=='0'){
            PieceOnHold = newPiece(ActivePiece.type);
            ActivePiece = newPiece(rand.nextInt(8));
        }else{
            Piece temp = newPiece(ActivePiece.type);
            ActivePiece = newPiece(PieceOnHold.type);
            PieceOnHold = newPiece(temp.type);
        }
        
        
    }
    public static void Rotate(){//function to rotate activepiece clockwise
        int temp;
        int[] x = new int[4];
        int[] y = new int[4];
        clearGrid();
        for(int i=0;i<4;i++){
            x[i]=ActivePiece.x[i]-ActivePiece.x[0];
            y[i]=ActivePiece.y[i]-ActivePiece.y[0];
            temp=x[i];
            x[i]=y[i];
            y[i]=(-1)*temp;
            
            
            x[i]=ActivePiece.x[0]+x[i];
            y[i]=ActivePiece.y[0]+y[i];
        }
        for(int i=0;i<4;i++){
            if(x[i]<0){
                for(int j=0;j<4;j++)x[j]++;
                i=0;
            }
            
            if(x[i]>9){
                for(int j=0;j<4;j++)x[j]--;
                i=0;
            }
        }
        for(int i=0;i<4;i++){
            ActivePiece.x[i]=x[i];
            ActivePiece.y[i]=y[i];
        }
        updateGrid();
    }
    public void checkForCompletion(){//this function checks the grid to see if there are any completed rows
        int count=0;                // and it deletes the row if it is full
        for(int j=0;j<20;j++){
            boolean flag = true;
            for(int i=0;i<10;i++){
                if(grid[i][j]=='x'){
                    flag = false;
                }
            }
            if(flag){
                for(int k=j;k<20-1;k++){
                    for(int i=0;i<10;i++){
                        grid[i][k]=grid[i][k+1];
                    }
                }
                j--;
                count++;// count shows how many rows are removed simultaneously, to calculate scorebonus
            }
        }
        switch(count){
            case 0:
                    break;
            case 1: score+=1000;//only 1 row removed
                    break;
            case 2: score+=4000;//2 rows filled in the same move
                    break;
            case 3: score+=9000;// 3 rows filled in the same move
                    break;
            case 4: score+=16000;// 4 rows
                    Tetris=2;
            default:
        }
    }
    public static void Solidify(){//this function is called whenever active piece reaches bottom
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(grid[i][j]!='x'){
                    grid[i][j]=Character.toLowerCase(grid[i][j]);
                }
            }
        }// so what this function does is it converts the characters to lower case, so that when 
        //when the active piece changes, the finishedpieces are retained
    }
    public void gameOver(Graphics g){
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        
        g.drawString("Game Over",(SCREEN_WIDTH - metrics.stringWidth("Game Ocer"))/2,SCREEN_HEIGHT/2);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
    public class MyKeyAdapter extends KeyAdapter{
        
        public void keyPressed(KeyEvent e){
            
        }
    }
}
