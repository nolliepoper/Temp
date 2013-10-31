
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.concurrent.*;

// This class is the master content manager, it has a list of managers.
// Also, this class may manange a few list of entities on it's own.
public class Content extends JPanel
{
    private final Frame frame;
    private final CopyOnWriteArrayList<Manager> list;
    private final Manager pltfrmMng;
    //private final Manager block;
    private boolean run;
    private Room currRoom;
    
    public Content(Frame fIn)
    {
        frame = fIn;
        list = new CopyOnWriteArrayList<>();
        run = true;

        setBackground(Color.WHITE);

        pltfrmMng = new Manager(frame, this);
        add(pltfrmMng);

        /*
         * block = new Manager(frame, this); add(block);
         */

        currRoom = new Room("start.json", frame);

        pltfrmMng.addAll(currRoom.getPlatforms());

        add(new Manager(frame, this));
        getLast().add(new Player(new Vector(100, 100)));
    }
    public void add(Manager mIn)
    {
        list.add(mIn);
    }
    public Manager getLast()
    {
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(list.size() - 1);
    }
    public Manager getType(String sIn)
    {
        for(Manager m : list)
        {
            if(m.getType() != null)
            {
                if(m.getType().equals(sIn))
                {
                    return m;
                }
            }
        }
        return null;
    }
    public void remove(Manager mIn)
    {
        mIn.dispose();
        list.remove(mIn);
    }
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_P))
        {
            run = !run;
            Keyboard.release(KeyEvent.VK_P);
        }
        if(run)
        {
            /*
             * if(Mouse.isPressesd(MouseEvent.BUTTON1)) { block.add(new
             * Block(Mouse.getClick(MouseEvent.BUTTON1), new Vector(50, 50)));
             * Mouse.release(MouseEvent.BUTTON1); }
             */
            for(Manager m : list)
            {
                m.logic();
            }
        }
    }
    @Override
    public void paint(Graphics gIn)
    {
        super.paint(gIn);
        Graphics2D g = (Graphics2D)gIn;

        currRoom.paint(g);
        for(Manager m : list)
        {
            m.paint(g);
        }
    }
}
