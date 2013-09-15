import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;

public abstract class Manager
{
    protected final Frame frame;
    protected final Content manager;
    
    protected final CopyOnWriteArrayList<Entity> list;
    
    public Manager(Frame fIn, Content mIn)
    {
        frame = fIn;
        manager = mIn;
        list = new CopyOnWriteArrayList<>();
    }
    public void add(Entity eIn)
    {
        list.add(eIn);
    }
    public void remove(Entity eIn)
    {
        list.remove(eIn);
    }
    public void logic()
    {
        for(Entity e: list)
            e.logic();
    }
    public void paint(Graphics2D gIn)
    {
        for(Entity e: list)
            e.paint(gIn);
    }
    public void dispose()
    {
        for(Entity e: list)
            e.dispose();
    }
}
