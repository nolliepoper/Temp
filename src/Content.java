
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
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
    public static Graphics2D darkness;
    // Constructor
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
        currRoom.getData();

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
    public void dispose()
    {
        for(Manager m : list)
        {
            remove(m);
        }
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
            for(Manager m : list)
            {
                m.logic();
            }
        }

        //Move to the next room
        Vector playLoc = getType("Player").get(0).getCenter();
        //System.out.println(playLoc.y);
        if(playLoc.y < 0)
        {
            currRoom.loadRoom(currRoom.getNorth());
            //currRoom.getData();
            getType("Player").get(0).setCenter(new Vector(400, 300));
        }
        else if(playLoc.x < 0)
        {
            currRoom.loadRoom(currRoom.getWest());
            //currRoom.getData();
            getType("Player").get(0).setCenter(new Vector(400, 300));
        }
        else if(playLoc.y > frame.getHeight())
        {
            currRoom.loadRoom(currRoom.getSouth());
            //currRoom.getData();
            getType("Player").get(0).setCenter(new Vector(400, 300));
        }
        else if(playLoc.x > frame.getWidth())
        {
            currRoom.loadRoom(currRoom.getEast());
            //currRoom.getData();
            getType("Player").get(0).setCenter(new Vector(400, 300));
        }
    }
    @Override
    public void paint(Graphics gIn)
    {
        super.paint(gIn);
        Graphics2D g = (Graphics2D)gIn;
        AffineTransform trans = new AffineTransform();
        //trans.scale(0.5, 0.5);
        g.transform(trans);

        BufferedImage temp = new BufferedImage(800, 800, BufferedImage.TYPE_4BYTE_ABGR);
        darkness = temp.createGraphics();
        darkness.setColor(Color.BLACK);
        darkness.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        darkness.setComposite(AlphaComposite.DstOut);

        currRoom.paint(g);
        for(Manager m : list)
        {
            m.paint(g);
        }
        g.drawImage(temp, null, 0, 0);
    }
}
