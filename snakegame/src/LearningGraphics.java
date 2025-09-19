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
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class LearningGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH, HEIGHT, rX, rY, rW, rH, cX, cY, diam, cVx, cVy;
    private Bubble bub, bub2;
    private ArrayList<Bubble>bubbles;
    
    //Default Constructor
    public LearningGraphics()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        rX = 300;
        rY = 300;
        rW = 50;
        rH = 100;
        cX=500;
        cY=300;
        diam = 70;
        cVx = 5;
        cVy = 5;
        
        bub = new Bubble(100,100,50,Color.red);
        bub2 = new Bubble(100,250, 50, Color.green);
        
        bubbles = new ArrayList<Bubble>();
        
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Learning Graphics"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Setting the size for gui
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
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);

        //moving the rectangle
        if(key==38) //move up
            rY-=10;
        if(key==40) //move down
            rY+=10;
        if(key==37) //move left
            rX-=10;
        if(key==39) //move right
            rX+=10;

    }   
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        
        Graphics2D g2D;
        g2D = (Graphics2D)g;
        
        //Drawing a Blue Rectangle to be the background
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Drawing Hello World!! at the center of the GUI
        Font f = new Font("Arial", Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("Hello World!!", (WIDTH/2)-150, HEIGHT/2);

        //Drawing the user-controlled rectangle
        g.setColor(Color.RED);
        g.fillRect(rX, rY, rW, rH);

        //Drawing the autonomous circle
        ImageIcon image = new ImageIcon(LearningGraphics.class.getResource("bubble.png"));
        g2D.drawImage(image.getImage(), 50, 200, 50,50,null);
        
        ImageIcon image2 = new ImageIcon(LearningGraphics.class.getResource("ball.png"));
        g2D.drawImage(image2.getImage(),cX, cY, diam, diam, null);
        
        bub.drawSelf(g);
        bub2.drawSelf(g);
        
        for(int i = 0; i<bubbles.size(); i++)
            bubbles.get(i).drawSelf(g);

    }
    public void loop()
    {
        
        
        //making the autonomous circle move
        cX+=cVx;
        cY+=cVy;
        
        //handling when the circle collides with the edges
        int nextX, nextY;
        nextX = cX+cVx;
        nextY = cY+cVy;
        if(nextY+diam==HEIGHT-25) //if it hits the bottom of the screen
            cVy*=-1;
        if(nextY+diam==55) //if it hits the top of the screen
            cVy*=-1;
        if(nextX+diam == WIDTH) //if it hits the right side of the screen
            cVx*=-1;
        if(nextX+diam == 55) //if it hits the left side of the screen
            cVx*=-1;
        
        //handling the collision of the circle with the rectangle
        if(detectCollision())
        {
            cVx*=-1;
            cVy*=-1;
        }
        
        bub.handleCollision(bub2);
        bub2.handleCollision(bub);
        
        for(int i = 0; i <bubbles.size();i++)
        {
            for(int j = i+1; j < bubbles.size();j++)
            {
                bubbles.get(i).handleCollision(bubbles.get(j));
                bubbles.get(j).handleCollision(bubbles.get(i));
            }
        }
        
        bub.act(WIDTH, HEIGHT);
        bub2.act(WIDTH, HEIGHT);
        for(int i = 0; i<bubbles.size(); i++)
            bubbles.get(i).act(WIDTH, HEIGHT);

        //Do not write below this
        repaint();
    }
    
    public double distance(int x1, int x2, int y1, int y2)
    {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2- y1), 2));
    }
    
    public boolean detectCollision()
    {
        boolean output = false;
        int radius, centerX, centerY;
        radius = diam/2;
        
        int nextX, nextY;
        nextX = cX+cVx;
        nextY = cY+cVy;
        
        centerX = (2*nextX + diam)/2;
        centerY = (2*nextY + diam)/2;
        
        for(int x = rX; x<= rX+rW; x++)
        {
            for(int y = rY; y<= rY+rH; y++)
            {
                if(distance(x, centerX, y, centerY)<radius)
                    output = true;
            }
        }
        return output;
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
        Thread gameThread = new Thread()
        {
            public void run(){
                while(true){
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
        LearningGraphics g = new LearningGraphics() {};
        g.start(60);
    }
}
