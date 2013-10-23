import java.awt.*;
import java.util.*;
import javax.swing.*;

public abstract class Entity
{
    public final Point point;
    public final Point dest;
    public final Color color;
    public final Point size;
    public final ArrayList<Class> list; // For collisions, needs implementation.
	public SpriteSheet sprites;
    
    public Entity(Color cIn, Point pIn, Point sIn)
    {
        color = cIn;
        point = pIn;
        dest = new Point(pIn.x, pIn.y);
        size = sIn;
        list = new ArrayList<>();
    }
    public abstract void logic();
    public abstract void paint(Graphics2D gIn);
    public abstract void dispose();
}
