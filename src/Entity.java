import java.awt.*;
import javax.swing.*;

public abstract class Entity
{
    protected final Point point;
    protected final Color color;
    
    public Entity(Color cIn, Point pIn)
    {
        color = cIn;
        point = pIn;
    }
    public Entity(Color cIn, int xIn, int yIn)
    {
        color = cIn;
        point = new Point(xIn, yIn);
    }
    public abstract void logic();
    public abstract void paint(Graphics2D gIn);
    public abstract void dispose();
}
