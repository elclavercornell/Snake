import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.applet.*;


public class SnakeGame extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH, HEIGHT, grid, h, w;
    private static int size;
    private boolean playing,won;
    private Wall wallR;
    private Wall wallL;
    private Wall wallT;
    private Wall wallB;
    private ArrayList <Square> body;
    private String direction; 
    private int ax1, ay1, ax2, ay2, ax3, ay3, ray, rax; //im making separate variables for the apples coordinated
                                            // because they will change as the snake eats the apples
    private ArrayList <Apple> apples;
    private RottenApple ra;
    private AudioClip introSong;


    private static int CONSTANT;
    /*
    So incase you were wondering, the constants are for like the grid so I can say that 
    the width and height is the amount of grid squares times the constant so I cant keep everything in the unit of 
    the the grid. This makes it easier for me to set everything to the rught positon so like I can do x position
    in grid 5 and then it would just be x = 5 * the constant corresponding with the size of the grid.
    Let me know if this is confusing or if you have questions
    */
    
    
    //Default Constructor
    public SnakeGame()
    {
        //initializing instance variables
        playing = true;
        won = false;
        direction = "right"; // snake starts going to the right
        body = new ArrayList<Square>();
        introSong = Applet.newAudioClip(this.getClass().getResource("nom.wav"));
        
        String[]buttons = {"Large", "Medium", "Small"};
        size = JOptionPane.showOptionDialog(null, "What size would you like the grid to be?", "Size of grid",
        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[2]);
        
        if(size==2) //small
        {
            CONSTANT = 35;
            //width and height (for GUI)
            w = 10;
            h = 9;
            WIDTH = (10*CONSTANT)+(CONSTANT*2);// we all two extra constants to account for the walls
            HEIGHT = (9*CONSTANT)+(CONSTANT*2);
            grid = 90;
            
            //walls
            wallL = new Wall(0,0,CONSTANT,HEIGHT); //the right wall starts at 0,0 and its one tile wide 
                                                        //(1*small constant = small constant), and its height is the height
            wallR = new Wall(WIDTH-CONSTANT, 0, CONSTANT, HEIGHT); //the left wall starts on the top of the left side
                                                                               //so the x cor is the width minus one tile
            wallT = new Wall(0,0,WIDTH,CONSTANT);
            wallB = new Wall(0, HEIGHT-CONSTANT,WIDTH, CONSTANT);
            
            //snake
            Square s1 = new Square(3,4);
            Square s2 = new Square(2,4);
            Square s3 = new Square(1,4);
            body.add(s1);
            body.add(s2);
            body.add(s3);
            
            //apple's coordinates
            ax1 = 7;
            ay1 = 2;
            ax2 = 7;
            ay2 = 4;
            ax3 = 7;
            ay3 = 6;
            
            ray = 2;
            rax = 3;
                                                                               
        }
        else if(size ==1) //medium
        {
            CONSTANT = 35;
            w = 17;
            h = 15;
            WIDTH = (17*CONSTANT)+(CONSTANT*2);
            HEIGHT = (15*CONSTANT)+(CONSTANT*2);
            grid = 255;
            wallR = new Wall(0,0,CONSTANT,HEIGHT);
            wallL = new Wall(WIDTH-CONSTANT, 0, CONSTANT, HEIGHT);
            wallT = new Wall(0,0,WIDTH,CONSTANT);
            wallB = new Wall(0, HEIGHT-CONSTANT,WIDTH, CONSTANT);
            Square s1 = new Square(4,7);
            Square s2 = new Square(3,7);
            Square s3 = new Square(2,7);
            body.add(s1);
            body.add(s2);
            body.add(s3);
            ax1 = 12;
            ax2 = 12;
            ax3 = 12;
            ay1 = 4;
            ay2 = 7;    
            ay3 = 10;
            rax = 4;
            ray = 3;
        }
        else if(size == 0) //large
        {
            CONSTANT = 25;
            w = 24;
            h = 21;
            WIDTH = (24*CONSTANT)+(CONSTANT*2);
            HEIGHT = (21*CONSTANT)+(CONSTANT*2);
            grid= 504;
            wallR = new Wall(0,0,CONSTANT,HEIGHT);
            wallL = new Wall(WIDTH-CONSTANT, 0, CONSTANT, HEIGHT);
            wallT = new Wall(0,0,WIDTH,CONSTANT);
            wallB = new Wall(0, HEIGHT-CONSTANT,WIDTH, CONSTANT);
            Square s1 = new Square(6,10);
            Square s2 = new Square(5,10);
            Square s3 = new Square(4,10);
            body.add(s1);
            body.add(s2);
            body.add(s3);
            ax1 =18;
            ax2 = 18;
            ax3 = 18;
            ay1 = 6;
            ay2= 10;
            ay3 = 14;
            ray = 5;
            rax = 5;
        }
        
        //apples
        Apple a1 = new Apple(ax1, ay1);
        Apple a2 = new Apple(ax2, ay2);
        Apple a3 = new Apple(ax3, ay3);
        apples = new ArrayList<Apple>();
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        
        ra = new RottenApple(rax, ray);
        
        //if they player didnt exit out the button screen
        if(!(size ==0 || size ==1 || size ==2))
            playing = false; //stop running file
        else
            playing = true; 
        
        //Setting up the GUI
        if(playing == true && won == false)
        {
            JFrame gui = new JFrame(); //This makes the gui box
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
            gui.setTitle("Snake Game"); //This is the title of the game, you can change it
            gui.setPreferredSize(new Dimension(WIDTH, HEIGHT+28)); //Setting the size for gui
            gui.setResizable(false); //Makes it so the gui cant be resized
            gui.getContentPane().add(this); //Adding this class to the gui
            //gui.EXIT_ON_CLOSE;

             /*If after you finish everything, you can declare your buttons or other things
              *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
              */

            gui.pack(); //Packs everything together
            gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
            gui.setVisible(true); //Makes the gui visible
            gui.addKeyListener(this);//stating that this object will listen to the keyboard
            gui.addMouseListener(this); //stating that this object will listen to the Mouse
            gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        }
        else if(won == true && playing == false)
        {
            JFrame gui = new JFrame(); //This makes the gui box
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
            gui.setTitle("YOU WON"); //This is the title of the game, you can change it
            gui.setPreferredSize(new Dimension(WIDTH, HEIGHT+28)); //Setting the size for gui
            gui.setResizable(false); //Makes it so the gui cant be resized
            gui.getContentPane().add(this); //Adding this class to the gui

             /*If after you finish everything, you can declare your buttons or other things
              *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
              */

            gui.pack(); //Packs everything together
            gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
            gui.setVisible(true); //Makes the gui visible
            gui.addKeyListener(this);//stating that this object will listen to the keyboard
            gui.addMouseListener(this); //stating that this object will listen to the Mouse
            gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        }
        else if(won == false && playing == false)
        {
            JFrame gui = new JFrame(); //This makes the gui box
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
            gui.setTitle("YOU LOST"); //This is the title of the game, you can change it
            gui.setPreferredSize(new Dimension(WIDTH, HEIGHT+28)); //Setting the size for gui
            gui.setResizable(false); //Makes it so the gui cant be resized
            gui.getContentPane().add(this); //Adding this class to the gui

             /*If after you finish everything, you can declare your buttons or other things
              *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
              */

            gui.pack(); //Packs everything together
            gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
            gui.setVisible(true); //Makes the gui visible
            gui.addKeyListener(this);//stating that this object will listen to the keyboard
            gui.addMouseListener(this); //stating that this object will listen to the Mouse
            gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        }
        
    }
    
    //acessors
    public static int getGridSize()
    {
        return size;
    }
    public static int getConstant()
    {
        return CONSTANT;
    }
    public Square getSquare(int i)
    {
        return body.get(i);
    }
    public String getDirection()
    {
        return direction;
    }
    
    //interesting methods
    
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        //getting the key pressed
        int key = e.getKeyCode();

        //moving the snake
        Square firstSquare = body.get(0);
        
        if(key==38 || key==87) //move up
        {
            if(!direction.equals("down")) //if the snake is moving down you cant make it
            {                             //go up directly beause it'll overlap itself
                direction = "up";
                firstSquare.setPrevVx(firstSquare.getVx());
                firstSquare.setPrevVy(firstSquare.getVy());
                firstSquare.setVx(0);
                firstSquare.setVy(CONSTANT);
            }    
        }
        if(key==40 || key==83) //move down
        {
            if(!direction.equals("up"))
            {
                direction = "down";
                firstSquare.setPrevVx(firstSquare.getVx());
                firstSquare.setPrevVy(firstSquare.getVy());
                firstSquare.setVx(0);
                firstSquare.setVy(CONSTANT);
            }
                
        }
        if(key==37 || key==65) //move left
        {
            if(!direction.equals("right"))
            {
                direction = "left";
                firstSquare.setPrevVx(firstSquare.getVx());
                firstSquare.setPrevVy(firstSquare.getVy());
                firstSquare.setVx(CONSTANT);
                firstSquare.setVy(0);
            }
        }
        if(key==39 || key==68) //move right
        {
            if(!direction.equals("left"))
            {
                direction = "right";
                firstSquare.setPrevVx(firstSquare.getVx());
                firstSquare.setPrevVy(firstSquare.getVy());
                firstSquare.setVx(CONSTANT);
                firstSquare.setVy(0);
            }
        }

    }   
    
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D;
        g2D = (Graphics2D)g;
        
        if(playing == true && won == false)
        {
            if(size==2)//small
            {
                ImageIcon grid = new ImageIcon(SnakeGame.class.getResource("smallGrid.png"));
                g2D.drawImage(grid.getImage(), CONSTANT, CONSTANT, WIDTH-(CONSTANT*2),HEIGHT-(CONSTANT*2),null);
            }
            if(size==1)//medium
            {
                ImageIcon grid = new ImageIcon(SnakeGame.class.getResource("mediumGrid.png"));
                g2D.drawImage(grid.getImage(), CONSTANT, CONSTANT, WIDTH-(CONSTANT*2),HEIGHT-(CONSTANT*2),null);
            }
            if(size==0)//large
            {
                ImageIcon grid = new ImageIcon(SnakeGame.class.getResource("largeGrid.png"));
                g2D.drawImage(grid.getImage(), CONSTANT, CONSTANT, WIDTH-(CONSTANT*2),HEIGHT-(CONSTANT*2),null);
            }

            wallR.drawSelf(g);
            wallL.drawSelf(g);
            wallT.drawSelf(g);
            wallB.drawSelf(g);

            apples.get(0).drawSelf(g);
            apples.get(1).drawSelf(g);
            apples.get(2).drawSelf(g);

            ra.drawSelf(g);

            ImageIcon first = new ImageIcon(SnakeGame.class.getResource("eye.png"));
            g2D.drawImage(first.getImage(), (int)body.get(0).getX(), (int)body.get(0).getY(), (int)body.get(0).getL(), (int)body.get(0).getL(), null);

            for(int i = 1; i<body.size();i++)
                body.get(i).drawSelf(g);
            
            Font f = new Font("Arial", Font.BOLD, 25);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Score: "+body.size(), 15, 20);
            
        }
        else if(won == true)
        {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            
            Font f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("YOU WON!", (WIDTH/2)-135, HEIGHT/2);
        }
        else if(won == false && playing == false)
        {
            g.setColor(Color.RED);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            Font f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("YOU LOST!", (WIDTH/2)-135, HEIGHT/2);
        }
    }
    
    //distance formula
    private double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2- y1), 2));
    }
    
    //snake colliding with the wall
    public boolean wallCollision(Wall w)
    {
        boolean output = false;
        Square firstSquare = body.get(0);
        
        if(direction.equals("right"))
        {
            if(firstSquare.getX()+CONSTANT == w.getX()) //if it's going right, it can only collide with the right wall
                output = true;
        }
        else if(direction.equals("left"))
        {
            if(firstSquare.getX()==w.getX()+CONSTANT)
                output = true;
        }
        else if(direction.equals("up"))
        {
            if(firstSquare.getY()==w.getY()+CONSTANT)
                output = true;
        }
        else
        {
            if(firstSquare.getY()+CONSTANT == w.getY())
                output = true;
        }
        
        return output;
    }
    
    //if the snake ate the apple, return true
    public boolean appleCollision(Apple a)
    {
        boolean output = false;
        Square firstSquare = body.get(0);
        
        if(firstSquare.getX() == a.getX() && firstSquare.getY() ==  a.getY())
            output = true;
        
        return output;
    }
    
    //if the snake ate the rotten apple, return true
    public boolean rottenAppleCollision(RottenApple ra)
    {
        boolean output = false;
        Square firstSquare = body.get(0);
        
        if(firstSquare.getX() == ra.getX() && firstSquare.getY() == ra.getY()) 
            output = true;
        
        return output;
    }
    
    //add a square to the snake (done after snake has eaten an apple)
    public void addSquare(double xCor, double yCor)
    {
        Square s = new Square(xCor, yCor);
        body.add(s);
    }
    
    //tells you if that coordinate is part of the snake
    public boolean onSnake(double xCor, double yCor)
    {
        boolean output = false;
        
        //if the coordinate is on the snake body
        for(int i = 0;i<body.size();i++)
        {
            double x, y;
            x = body.get(i).getX();
            y = body.get(i).getY();
            if(x==xCor && y==yCor)
                output = true;
        }
        
        //if the coordinate is the square before the snake body, since we can't spawn an apple on this coordinate
        //because then the new apple will be covered by the body
        Square lastSquare = body.get(body.size()-1);
        if(lastSquare.getX()==xCor && lastSquare.getY() == yCor)
            output = true;
        
        return output;
    }
    
    //return true if the coordinates are on any apples (rotten or good)
    public boolean onApple(double xCor, double yCor)
    {
        boolean output = false;
        
        for(Apple a: apples)
        {
            if(a.getX()==xCor && a.getY()==yCor)
                output = true;
        }
        if(ra.getX()==xCor && ra.getY()==yCor)
            output = true;
        
        return output;
    }
    
    //handling the collision with the apple
    public void handleAppleCollision(Apple a)
    {
        double x, y, newX, newY;
        
        //add a new square to the snake
        x = getSquare(body.size()-1).getPrevX(); //this will get the last square's previous coordinate
        y = getSquare(body.size()-1).getPrevY(); //in order to add a squre to the snake to the corrent place
        addSquare(x,y);
        int remain = grid - body.size();
            
        //update the location of the apple
        if(remain>3)
        {
            newX = (int)(Math.random()*(w-1)+1); //this will set X to anywhere in between 0 and the right wall
            newY = (int)(Math.random()*(h-1)+1);
            
            while(onSnake(newX, newY) || onApple(newX, newY) || newX==0 ||newY == 0 || newX == WIDTH-CONSTANT || newY == HEIGHT-CONSTANT) 
            {                 //the new coordinate for the apple cant be on the snake, or on the walls                            
                newX = (int)(Math.random()*(w-1)+1); 
                newY = (int)(Math.random()*(h-1)+1);
            }
            
            a.setX(newX);
            a.setY(newY);
        }
        else //if there isnt anymore space for the 3 apples then no new apples appear
        {
            a.setX(WIDTH*100);
            a.setY(HEIGHT*100);
        }
    }
    
    //handling the collisioin with the rotten apple
    public void handleRottenAppleCollision(RottenApple ra)
    {
        double x, y, newX, newY;
        
        //deleting the last square to the snake
        body.remove(body.size()-1);
        int remain = grid - body.size();
            
        //update the location of the apple
        if(remain>1)
        {
            newX = (int)(Math.random()*(w-1)+1); //this will set X to anywhere in between 0 and the right wall
            newY = (int)(Math.random()*(h-1)+1);
            
            while(onSnake(newX, newY) || onApple(newX, newY) || newX==0 ||newY == 0 || newX == WIDTH-CONSTANT || newY == HEIGHT-CONSTANT) 
            {                 //the new coordinate for the apple cant be on the snake, or on the walls                                      //or on the walls
                newX = (int)(Math.random()*(w-1)+1); 
                newY = (int)(Math.random()*(h-1)+1);
            }
            
            ra.setX(newX);
            ra.setY(newY);
        }
        else //if there isnt anymore space for the 3 apples then no new apples appear
        {
            ra.setX(WIDTH*100);
            ra.setY(HEIGHT*100);
        }
    }
    
    //getting the snake to move
    public void move()
    {
        Square firstSquare = body.get(0);
        double currX, currY, prevX, prevY;
        
        if(direction.equals("up"))
        {
            prevX = firstSquare.getX();
            prevY = firstSquare.getY();
            firstSquare.setY(firstSquare.getY()-firstSquare.getVy()); //adding the velocity to the coordinate
            for(int i = 1; i < body.size(); i++)
            {
                Square curr = body.get(i);
                currX = curr.getX();
                currY = curr.getY();
                
                long start = System.currentTimeMillis();
                long end = start + 6 * 10;
                while (System.currentTimeMillis() < end) {  //this will create like a time out
                    // don't do anything, just wait
                }
                
                curr.setY(prevY); //setting the next square to the same as the previous square
                curr.setX(prevX);          //adding or subtracting COSNTANT for separation between the squares
                prevY = currY;
                prevX = currX;
            }
        }
        if(direction.equals("down"))
        {
            prevX = firstSquare.getX();
            prevY = firstSquare.getY();
            firstSquare.setY(firstSquare.getY()+firstSquare.getVy()); //adding the velocity to the coordinate
            for(int i = 1; i < body.size(); i++)
            {
                Square curr = body.get(i);
                currX = curr.getX();
                currY = curr.getY();
                
                long start = System.currentTimeMillis();
                long end = start + 6 * 10;
                while (System.currentTimeMillis() < end) {  //this will create like a time out
                    // don't do anything, just wait
                }
                
                curr.setY(prevY); //setting the next square to the same as the previous square
                curr.setX(prevX);
                prevY = currY;
                prevX = currX;
            }
        }
        if(direction.equals("right"))
        {
            prevX = firstSquare.getX();
            prevY = firstSquare.getY();
            firstSquare.setX(firstSquare.getX()+firstSquare.getVx()); //adding the velocity to the coordinate
            for(int i = 1; i < body.size(); i++)
            {
                Square curr = body.get(i);
                currX = curr.getX();
                currY = curr.getY();
                
                long start = System.currentTimeMillis();
                long end = start + 6 * 10;
                while (System.currentTimeMillis() < end) {  //this will create like a time out
                    // don't do anything, just wait
                }
                
                curr.setY(prevY); //setting the next square to the same as the previous square
                curr.setX(prevX);
                prevY = currY;
                prevX = currX;
            }
        }
        if(direction.equals("left"))
        {
            prevX = firstSquare.getX();
            prevY = firstSquare.getY();
            firstSquare.setX(firstSquare.getX()-firstSquare.getVx()); //adding the velocity to the coordinate
            for(int i = 1; i < body.size(); i++)
            {
                Square curr = body.get(i);
                currX = curr.getX();
                currY = curr.getY();
                
                long start = System.currentTimeMillis();
                long end = start + 6 * 10;
                while (System.currentTimeMillis() < end) {  //this will create like a time out
                    // don't do anything, just wait
                }
                
                curr.setY(prevY); //setting the next square to the same as the previous square
                curr.setX(prevX);
                prevY = currY;
                prevX = currX;
            }
        }
    }
    
    //check if snake ran into itself
    public boolean ranIntoSnake()
    {
        boolean output = false;
        
        Square firstSquare = body.get(0);
        double xCor = firstSquare.getX();
        double yCor = firstSquare.getY();
        
        for(int i = 1; i<body.size(); i++)  //very similar to to onSnake(), just doesnt count the first one
        {
            double x, y;
            x = body.get(i).getX();
            y = body.get(i).getY();
            if(x==xCor && y==yCor)
                output = true;
        }
        
        return output;
    }
    
    public void loop()
    {
        AudioClip introSong = Applet.newAudioClip(SnakeGame.class.getResource("nom.wav"));
        
        //move
        move();
        
        //check if the snake collides with the walls
        if(wallCollision(wallR))
            playing = false;
        if(wallCollision(wallL))
            playing = false;
        if(wallCollision(wallB))
            playing = false;
        if(wallCollision(wallT))
            playing = false;
        
        //check if they won the game by eating all the apples
        if(body.size()==grid)
        {
            won = true;
            playing = false;
        }
        
        //check if the snake eats the apples
        for(Apple a : apples)
        {
            if(appleCollision(a))
            {
                introSong.play();
                handleAppleCollision(a);
            }
            
        }
        if(rottenAppleCollision(ra))
        {
            introSong.play();
            handleRottenAppleCollision(ra);
        }
        
        //check if the snake ran into itself
        if(ranIntoSnake())
            playing = false;
        
        
        //Do not write below this
        repaint();
    }
    
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) 
    {
    }
    public void keyReleased(KeyEvent e) 
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(playing){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }

    public static void main(String[] args)
    {
        SnakeGame g = new SnakeGame();
        g.start(60);
    }
}