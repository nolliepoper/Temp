import java.awt.*;
import java.util.*;
import javax.swing.*;

public abstract class Entity
{
    public final Vector point;
    public final Vector dest;
    public final Color color;
    public final Vector size;
    public final ArrayList<String> list; // For collisions, needs implementation.
    
    public Entity(Color cIn, Vector vIn, Vector sIn)
    {
        color = cIn;
        point = vIn;
        dest = new Vector(vIn.x, vIn.y);
        size = sIn;
        list = new ArrayList<>();
    }
    public abstract void logic();
    public abstract void paint(Graphics2D gIn);
    public abstract void dispose();
}
