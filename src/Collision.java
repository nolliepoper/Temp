
import java.awt.*;

public class Collision
{
    public static Frame frame;
    public Collision(Frame fIn)
    {
        frame = fIn;
    }
    public static boolean canMoveX(Entity eIn, double xIn)
    {
        return eIn.point.x + xIn >= 0 && eIn.point.x + xIn + eIn.size.x < frame.getWidth() - 15; // Contants are for the sides.
    }
    public static boolean canMoveY(Entity eIn, double yIn)
    {
        return eIn.point.y + yIn >= 0 && eIn.point.y + yIn + eIn.size.y < frame.getHeight() - 35;
    }
}
