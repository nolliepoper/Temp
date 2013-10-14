import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;

// This class is the master content manager, it has a list of managers.
// Also, this class may manange a few list of entities on it's own.
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
        add(new Manager(frame, this));
        getLast().add(new Player(Color.BLUE, new Point(100, 100)));
    }
    public void add(Manager mIn)
    {
        list.add(mIn);
    }
    public Manager getLast()
    {
        if(list.isEmpty())
            return null;
        return list.get(list.size() - 1);
    }
    public void remove(Manager mIn)
    {
        mIn.dispose();
        list.remove(mIn);
    }
    public void logic()
    {
        for(Manager m: list)
            m.logic();
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
