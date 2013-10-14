
import java.awt.*;
import java.util.concurrent.*;

public class Collision
{
    private static Frame frame;
    private static CopyOnWriteArrayList<Entity> list;
    
    public Collision(Frame fIn)
    {
        frame = fIn;
        list = new CopyOnWriteArrayList<>();
    }
    public static void add(Entity eIn)
    {
        list.add(eIn);
    }
    public static boolean wallLeft(Entity eIn)
    {
        return eIn.dest.x >= 0;
    }
    public static boolean wallRight(Entity eIn)
    {
        return eIn.dest.x <= frame.getWidth() - eIn.size.x - 15; // 15 to make up for right of window frame.
    }
    public static boolean wallX(Entity eIn)
    {
        return wallLeft(eIn) && wallRight(eIn);
    }
    public static boolean wallUp(Entity eIn)
    {
        return eIn.dest.y >= 0;
    }
    public static boolean wallDown(Entity eIn)
    {
        return eIn.dest.y <= frame.getHeight() - eIn.size.y - 35; // 35 to make up for bottom of window frame.
    }
    public static boolean wallY(Entity eIn)
    {
        return wallUp(eIn) && wallDown(eIn);
    }
}
