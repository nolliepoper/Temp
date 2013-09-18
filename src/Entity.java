import java.awt.*;
import javax.swing.*;

public abstract class Entity
{
    protected final Point point;
    protected final Color color;
    public final Point size;
    
    public Entity(Color cIn, Point pIn, Point sIn)
    {
        color = cIn;
        point = pIn;
        size = sIn;
    }
    public abstract void logic();
    public abstract void paint(Graphics2D gIn);
    public abstract void dispose();
}
