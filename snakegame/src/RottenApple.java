
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class RottenApple
{
    //instance variables
    private double x, y, diam;
    private static int CONSTANT= SnakeGame.getConstant();
    
    public RottenApple(double xCor, double yCor)
    {
        x = (xCor*CONSTANT)+CONSTANT; //we add the constant because the wall count as one tile
        y = (yCor*CONSTANT)+CONSTANT;
        diam = CONSTANT;
    }
    
    //accessors
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getDiam()
    {
        return diam;
    }
    
    //mutators
    public void setX(double xCor)
    {
        x = (xCor*CONSTANT)+CONSTANT;
    }
    public void setY(double yCor)
    {
        y = (yCor*CONSTANT)+CONSTANT;
    }
    
    //intresting methods
    public void drawSelf(Graphics g)    
    {
        Graphics2D g2D;
        g2D = (Graphics2D)g;
        ImageIcon image = new ImageIcon(RottenApple.class.getResource("rottenApple.png"));
        g2D.drawImage(image.getImage(),(int)x, (int)y, (int)diam, (int)diam, null);
    }
}