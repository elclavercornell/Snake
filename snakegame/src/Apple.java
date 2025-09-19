import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Apple
{
    //instance variales
    private double x, y, diam;
    private static int CONSTANT= SnakeGame.getConstant();
    
    public Apple(double xCor, double yCor)
    {
        x = (xCor*CONSTANT)+CONSTANT; //we add the constant because the wall count as one tile
        y = (yCor*CONSTANT)+CONSTANT;
        diam = CONSTANT;
    }
    
    //accesors
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
        ImageIcon image = new ImageIcon(Apple.class.getResource("apple.png"));
        g2D.drawImage(image.getImage(),(int)x, (int)y, (int)diam, (int)diam, null);
    }
}