import java.awt.Graphics;
import java.awt.Color;

public class Bubble
{
    private int x, y, vx, vy, diam;
    private Color col;
    
    public Bubble(int xCoor, int yCoor, int d, Color c)
    {
        x=xCoor;
        y=yCoor;
        vx=1;
        vy=1;
        diam = d;
        col = c;
    }
    public String toString()
    {
        return "The coordinates of the bubble are ("+x+", "+y+") and the diameter is "+diam+".";
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getDiam()
    {
        return diam;
    }
    private double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2- y1), 2));
    }
    public int getCenterX()
    {
        return x+diam/2;
    }
    public int getCenterY()
    {
        return y+diam/2;
    }
    public void act(int w, int h)
    {
        //get the next x and y coordinates
        int nextX, nextY;
        nextX = x+vx;
        nextY = y+vy;
        
        //if-statements to handle the Bubble bouncing off of the 4 walls
        if(nextY+diam==h-25) //if it hits the bottom of the screen
            vy*=-1;
        if(nextY+diam==55) //if it hits the top of the screen
            vy*=-1;
        if(nextX+diam == w) //if it hits the right side of the screen
            vx*=-1;
        if(nextX+diam == 55) //if it hits the left side of the screen
            vx*=-1;

        //updating x and y
        x+=vx;
        y+=vy;
    }
    public void drawSelf(Graphics g)    
    {
        g.setColor(col);
        g.fillOval(x,y,diam,diam);
    }
    public void handleCollision(Bubble anotherBubble)
    {
        //Getting the center of this Bubble and anotherBubble
        int thisCenterX, thisCenterY, anotherCenterX, anotherCenterY;
        thisCenterX= this.getCenterX();
        thisCenterY= this.getCenterY();
        anotherCenterX = anotherBubble.getCenterX();
        anotherCenterY = anotherBubble.getCenterY();
        
        //getting the radius of this Bubble and anotherBubble
        int thisRadius, anotherRadius;
        thisRadius = this.getDiam()/2;
        anotherRadius = anotherBubble.getDiam()/2;
        
        //checking if this Bubble Collided with anotherBubble
        double distance = distance(thisCenterX, thisCenterY, anotherCenterX, anotherCenterY);
        if(distance <=thisRadius+ anotherRadius)
        {
            //calculating the velocities of this Bubble after colliding with anotherBubble
            vx = thisCenterX-anotherCenterX;
            vy = thisCenterY-anotherCenterY;
            
            //Slowing down the velocities.  otherwise they go crazy
            int maxSpeed = 1;
            
            if(vx<=-maxSpeed)
                vx = -maxSpeed;
            else if(vx>=maxSpeed)
                vx = maxSpeed;
            
            if(vy <= -maxSpeed)
                vy = -maxSpeed;
            else if(vy >= maxSpeed)
                vy = maxSpeed;
        }
        
        
    }
}
