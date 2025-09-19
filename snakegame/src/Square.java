import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Square
{
    //instance variables
    private double x,y,l;
    private double vx, vy, prevVx, prevVy;
    
    
    public Square(double xCor, double yCor)
    {
        x = (xCor*SnakeGame.getConstant())+SnakeGame.getConstant(); 
        y = (yCor*SnakeGame.getConstant())+SnakeGame.getConstant();
        l = SnakeGame.getConstant();
        vx = SnakeGame.getConstant();
        vy = 0;
        prevVx = SnakeGame.getConstant();
        prevVy = 0;
        
    }
    
    //accesors
    public double getL()
    {
        return l;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getVx()
    {
        return vx;
    }
    public double getVy()
    {
        return vy;
    }
    public double getPrevVx()
    {
        return prevVx;
    }
    public double getPrevVy()
    {
        return prevVy;
    }
    
    //mutators
    public void setX(double xCor)
    {
        x = xCor;
    }
    public void setY(double yCor)
    {
        y = yCor;
    }
    public void setVx(double xVel)
    {
        vx = xVel;
    }
    public void setVy(double yVel)
    {
        vy = yVel;
    }
    public void setPrevVx(double vx)
    {
        prevVx = vx;
    }
    public void setPrevVy(double vy)
    {
        prevVy = vy;
    }
    
    //interesting
    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, (int)l, (int)l);
    }
    
    public double getPrevX()
    {
        return x-prevVx;
    }
    public double getPrevY()
    {
        return y-prevVy;
    }
    
}