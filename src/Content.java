import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;

public class Content extends JPanel
{
    private final Frame frame;
    private final CopyOnWriteArrayList<Manager> list;
    
    public Content(Frame fIn)
    {
        frame = fIn;
        list = new CopyOnWriteArrayList<>();
        
        setBackground(Color.WHITE);
        
        add(new BallManager(frame, this));
    }
    public void add(Manager eIn)
    {
        list.add(eIn);
    }
    public void remove(Entity eIn)
    {
        eIn.dispose();
        list.remove(eIn);
    }
    public void logic()
    {
        for(Manager eIn: list)
            eIn.logic();
    }
    @Override
    public void paint(Graphics gIn)
    {
        super.paint(gIn);
        Graphics2D g = (Graphics2D)gIn;
        for(Manager m: list)
            m.paint(g);
    }
}
