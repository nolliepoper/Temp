import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;

// This class manages entities in a list.
public class Manager<T extends Entity>
{
    protected final Frame frame;
    protected final Content manager;
    protected final CopyOnWriteArrayList<T> list;
    
    public Manager(Frame fIn, Content mIn)
    {
        frame = fIn;
        manager = mIn;
        list = new CopyOnWriteArrayList<>();
    }
    public String getType()
    {
        if(list.isEmpty())
            return null;
        //System.out.println(list.get(0).getClass().getName());
        return list.get(0).getClass().getName();
    }
    public void add(T eIn)
    {
        list.add(eIn);
    }
    public void remove(T eIn)
    {
        list.remove(eIn);
    }
    public T getRandom()
    {
        if(list.isEmpty())
            return null;
        return (T)list.get((int)(Math.random() * list.size()));
    }
    public void logic()
    {
        for(T e: list)
            e.logic();
    }
    public void paint(Graphics2D gIn)
    {
        for(T e: list)
            e.paint(gIn);
    }
    public void dispose()
    {
        for(T e: list)
            e.dispose();
    }
}
