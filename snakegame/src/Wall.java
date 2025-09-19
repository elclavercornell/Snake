import java.awt.Color;
import java.awt.Graphics;

public class Wall
{
    private int x,y,w,h;
    
    public Wall(int xCor, int yCor, int width, int height)
    {
        x = xCor;
        y = yCor;
        w = width;
        h = height;
    }
    
    //accesors
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    
    //intresting methods
    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);
    }
}