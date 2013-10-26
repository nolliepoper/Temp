import java.awt.*;
import java.util.concurrent.*;

public class Collision
{
    private static Frame frame;
    private static Content manager;
    
    public Collision(Frame fIn, Content mIn)
    {
        frame = fIn;
        manager = mIn;
    }
    private static boolean collideX(Entity eIn, Entity oIn)
    {
        if(Math.abs(eIn.point.y - oIn.point.y) < eIn.size.y/2 + oIn.size.y/2)
            if(Math.abs(eIn.dest.x - oIn.point.x) < eIn.size.x/2 + oIn.size.x/2)
                return true;
        return false;
    }
    private static boolean collideY(Entity eIn, Entity oIn)
    {
        if(Math.abs(eIn.point.x - oIn.point.x) < eIn.size.x/2 + oIn.size.x/2)
            if(Math.abs(eIn.dest.y - oIn.point.y) < eIn.size.y/2 + oIn.size.y/2)
                return true;
        return false;
    }
    public static Entity collisionX(Entity eIn)
    {
        for(String s: eIn.list)
        {
            Manager m = manager.getType(s);
            if(m != null)
                for(Object e: m.list)
                    if(collideX(eIn, (Entity)e))
                        return (Entity)e;
        }
        return null;
    }
    public static Entity collisionY(Entity eIn)
    {
        for(String s: eIn.list)
        {
            Manager m = manager.getType(s);
            if(m != null)
                for(Object e: m.list)
                    if(collideY(eIn, (Entity)e))
                        return (Entity)e;
        }
        return null;
    }
    public static boolean wallLeft(Entity eIn)
    {
        return eIn.dest.x - eIn.size.x/2 < 0;
    }
    public static boolean wallRight(Entity eIn)
    {
        return eIn.dest.x + eIn.size.x/2 > frame.getWidth() - 15; // 15 to make up for right of window frame.
    }
    public static boolean wallX(Entity eIn)
    {
        return wallLeft(eIn) || wallRight(eIn);
    }
    public static boolean wallUp(Entity eIn)
    {
        return eIn.dest.y - eIn.size.y/2 < 0;
    }
    public static boolean wallDown(Entity eIn)
    {
        return eIn.dest.y + eIn.size.y/2 > frame.getHeight() - 35; // 35 to make up for bottom of window frame.
    }
    public static boolean wallY(Entity eIn)
    {
        return wallUp(eIn) || wallDown(eIn);
    }
}
