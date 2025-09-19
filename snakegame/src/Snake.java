//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//import javax.swing.ImageIcon;
//
//public class Snake
//{
//    //intance variables
//    private ArrayList <Square> body;
//    private String direction; 
//    
//    //constuctors
//    public Snake()
//    {
//        direction = "right"; // snake starts going to the right
//                    
//        body = new ArrayList<Square>();
//        if(SnakeGame.getGridSize()==2) //small
//        {
//            Square s1 = new Square(3,4);
//            Square s2 = new Square(2,4);
//            Square s3 = new Square(1,4);
//            body.add(s1);
//            body.add(s2);
//            body.add(s3);
//        }
//        if(SnakeGame.getGridSize()==1) //medium
//        {
//            Square s1 = new Square(4,7);
//            Square s2 = new Square(3,7);
//            Square s3 = new Square(2,7);
//            body.add(s1);
//            body.add(s2);
//            body.add(s3);
//        }
//        if(SnakeGame.getGridSize()==0) //large
//        {
//            Square s1 = new Square(6,10);
//            Square s2 = new Square(5,10);
//            Square s3 = new Square(4,10);
//            body.add(s1);
//            body.add(s2);
//            body.add(s3);
//        }
//    }
//    
//    //accesors
//    public Square getSquare(int i)
//    {
//        return body.get(i);
//    }
//    public int size()
//    {
//        return body.size();
//    }
//    public String getDirection()
//    {
//        return direction;
//    }
//    
//    //mutators
//    public void setDirection(String direct)
//    {
//        direction = direct;
//    }
//    
//    //intresting methods
//    
//    //distance formula
//    private double distance(int x1, int y1, int x2, int y2)
//    {
//        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2- y1), 2));
//    }
//    
//    //if the snake ate the apple, return true
//    public boolean appleCollision(Apple a)
//    {
//        boolean output = false;
//        int radius, centerX, centerY;
//        Square firstSquare = body.get(0);
//        
//        radius = a.getDiam()/2;
//        centerX = (2*a.getX() + radius)/2;
//        centerY = (2*a.getY() + radius)/2;
//        
//        for(int x = firstSquare.getX(); x<= firstSquare.getX()+firstSquare.getL(); x++)
//        {
//            for(int y = firstSquare.getY(); y<= firstSquare.getY()+firstSquare.getL(); y++)
//            {
//                if(distance(x, centerX, y, centerY)<radius)
//                    output = true;
//            }
//        }
//        return output;
//    }
//    
//    //add a square to the snake (done after snake has eaten an apple)
//    public void addSquare(int xCor, int yCor)
//    {
//        Square s = new Square(xCor, yCor);
//        body.add(s);
//    }
//    
//    //tells you if that coordinate is part of the snake
//    public boolean onSnake(int xCor, int yCor)
//    {
//        for(int i = 0;i<body.size();i++)
//        {
//            int x, y;
//            x = body.get(i).getX();
//            y = body.get(i).getY();
//            if(x==xCor || y==yCor)
//                return true;
//        }
//        return false;
//    }
//    
//    //snake colliding with the wall
//    public boolean wallCollision(Wall w)
//    {
//        boolean output = false;
//        Square firstSquare = body.get(0);
//        
//        
//        for(int x = firstSquare.getX(); x<= firstSquare.getX()+firstSquare.getL(); x++)
//        {
//            for(int y = firstSquare.getY(); y<= firstSquare.getY()+firstSquare.getL(); y++)
//            {
//                if(distance(x, w.getX(), y, w.getY())<=0)
//                    output = true;
//            }
//        }
//        return output;
//    }
//    
//    //getting the snake to move
//    public void move()
//    {
//        Square firstSquare = body.get(0);
//        if(direction.equals("up"))
//        {
//            firstSquare.setY(firstSquare.getY()+firstSquare.getV()); //adding the velocity to the coordinate
//            for(int i = 1; i < body.size(); i++)
//            {
//                Square temp = body.get(i);
//                Square prev = body.get(i-1);
//                temp.setY(prev.getPrevY()); //setting the next square to the same as the previous square
//                temp.setX(prev.getPrevX());
//            }
//        }
//        if(direction.equals("down"))
//        {
//            firstSquare.setY(firstSquare.getY()-firstSquare.getV()); //adding the velocity to the coordinate
//            for(int i = 1; i < body.size(); i++)
//            {
//                Square temp = body.get(i);
//                Square prev = body.get(i-1);
//                temp.setY(prev.getPrevY()); //setting the next square to the same as the previous square
//                temp.setX(prev.getPrevX());
//            }
//        }
//        if(direction.equals("right"))
//        {
//            firstSquare.setX(firstSquare.getX()+firstSquare.getV()); //adding the velocity to the coordinate
//            for(int i = 1; i < body.size(); i++)
//            {
//                Square temp = body.get(i);
//                Square prev = body.get(i-1);
//                temp.setY(prev.getPrevY()); //setting the next square to the same as the previous square
//                temp.setX(prev.getPrevX());
//            }
//        }
//        if(direction.equals("left"))
//        {
//            firstSquare.setX(firstSquare.getX()-firstSquare.getV()); //adding the velocity to the coordinate
//            for(int i = 1; i < body.size(); i++)
//            {
//                Square temp = body.get(i);
//                Square prev = body.get(i-1);
//                temp.setY(prev.getPrevY()); //setting the next square to the same as the previous square
//                temp.setX(prev.getPrevX());
//            }
//        }
//    }
//    
//    //draw self
//    public void drawSelf(Graphics g)
//    {
//        Graphics2D g2D;
//        g2D = (Graphics2D)g;
//        
//        ImageIcon first = new ImageIcon(Snake.class.getResource("eye.png"));
//        g2D.drawImage(first.getImage(), body.get(0).getX(), body.get(0).getY(), body.get(0).getL(), body.get(0).getL(), null);
//            
//        for(int i = 1; i<body.size();i++)
//            body.get(i).drawSelf(g);
//        
//    }
//    
//    
//    
//}