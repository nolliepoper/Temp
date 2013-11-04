
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
    private CopyOnWriteArrayList<Manager> list;
    private Manager pltfrmMng;
    public static Manager bulletMng;
    private final Manager target;
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
		
		
        bulletMng = new Manager(frame, this);
        add(bulletMng);

        /*
         * block = new Manager(frame, this); add(block);
         */

        currRoom = new Room("start.json", frame);
        currRoom.getData();

        pltfrmMng.addAll(currRoom.getPlatforms());

        target = new Manager(frame, this);
        add(target);
        getLast().add(new Target(new Vector(450, 150), 50, 50, target));
        getLast().add(new Target(new Vector(200, 250), 50, 50, target));
        getLast().add(new Target(new Vector(100, 375), 50, 50, target));
        getLast().add(new Target(new Vector(300, 500), 50, 50, target));
        
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
            if(run == false){
                
            }
        }
        if(run)
        {
            for(Manager m : list)
            {
                m.logic();
            }
        }

        //Move to the next room
		Manager<Player> tmpMng = getType("Player");
        Vector playLoc = tmpMng.get(0).getCenter();
		boolean isMove = false;
	
        if(playLoc.y < 0)
        {
			//Read the data about the room
            currRoom.loadRoom(currRoom.getNorth());
			isMove = true;
        }
        else if(playLoc.x < 0)
        {
			//Read the data about the room
            currRoom.loadRoom(currRoom.getWest());
			isMove = true;
        }
        else if(playLoc.y > frame.getHeight())
        {
			//Read the data about the room
            currRoom.loadRoom(currRoom.getSouth());
			isMove = true;

        }
        else if(playLoc.x > frame.getWidth())
        {
			//Read the data about the room
            currRoom.loadRoom(currRoom.getEast());
			isMove = true;
        }
		
		//If the player is actually moving to a new room, recreate 
		//the managers
		if(isMove)
		{
			pltfrmMng.drop();
			bulletMng.drop();
			pltfrmMng.addAll(currRoom.getPlatforms());
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
