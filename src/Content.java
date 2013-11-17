
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.util.concurrent.*;
import javax.imageio.ImageIO;

// This class is the master content manager, it has a list of managers.
// Also, this class may manange a few list of entities on it's own.
public class Content extends JPanel
{
    private final Frame frame;
    private CopyOnWriteArrayList<Manager> list;
    private Manager<Platform> pltfrmMng;
    private Manager<Enemy> enemyMng;
    public static Manager<Bullet> bulletMng;
    //private final Manager target;
    private boolean run;
    private Room currRoom;
    public static Graphics2D darkness;
    private boolean setOpaque = false;
    private Graphics2D bloodOverlayG;
    private int bloodOverlay = 0;
    private BufferedImage blood;
    //This is where the player will spawn in the current room
    Vector currSpawn = null;
    // Constructor
    public Content(Frame fIn)
    {
        frame = fIn;
        list = new CopyOnWriteArrayList<>();
        run = true;

        setBackground(Color.WHITE);
        currRoom = new Room("start.json", frame);
        //currRoom.getData();

        pltfrmMng = new Manager<>(frame, this);
        add(pltfrmMng);
        pltfrmMng.addAll(currRoom.getPlatforms());

        enemyMng = new Manager<>(frame, this);
        add(enemyMng);
        enemyMng.addAll(currRoom.getEnemies());



        bulletMng = new Manager<>(frame, this);
        add(bulletMng);

        /*
         * block = new Manager(frame, this); add(block);
         */

        add(new Manager<Hopper>(frame, this));
        getLast().add(new Hopper(new Vector(250, 500), 50, 50));
        

        /*target = new Manager(frame, this);
         add(target);
         getLast().add(new Target(new Vector(450, 150), 50, 50, target));
         getLast().add(new Target(new Vector(200, 250), 50, 50, target));
         getLast().add(new Target(new Vector(100, 375), 50, 50, target));
         getLast().add(new Target(new Vector(300, 500), 50, 50, target));
         */
        //Create the player
        currSpawn = currRoom.getWestSpawn();
        add(new Manager<Player>(frame, this));
        getLast().add(new Player(currSpawn));

        setBloodImage();
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
        //Pause the game
        if(Keyboard.isPressed(KeyEvent.VK_P))
        {
            run = !run;
            Keyboard.release(KeyEvent.VK_P);
            if(!run)
            {
                setOpaque = !setOpaque;
            }
            else
            {
                setOpaque = !setOpaque;
            }
        }
        //Update everything if the game is not paused
        if(run)
        {
            for(Manager m : list)
            {
                m.logic();
            }
        }

        //Move to the next room

        Vector currPos = getType("Player").get(0).getCenter();

        boolean isMove = false;

        //Go north?
        if(currPos.y < 0)
        {
            //Read the data about the room
            currRoom.loadRoom(currRoom.getNorth());
            currSpawn = currRoom.getSouthSpawn();
            isMove = true;
        }
        //Go south?
        else if(currPos.y > frame.getHeight())
        {
            //Read the data about the room
            currRoom.loadRoom(currRoom.getSouth());
            currSpawn = currRoom.getNorthSpawn();
            isMove = true;

        }
        //Go east?
        else if(currPos.x > frame.getWidth())
        {
            //Read the data about the room
            currRoom.loadRoom(currRoom.getEast());
            currSpawn = currRoom.getWestSpawn();
            isMove = true;
        }
        //Go west?
        else if(currPos.x < 0)
        {
            //Read the data about the room
            currRoom.loadRoom(currRoom.getWest());
            currSpawn = currRoom.getEastSpawn();
            isMove = true;
        }

        //If the player is actually moving to a new room, recreate 
        //the managers
        if(isMove)
        {
            pltfrmMng.drop();
            bulletMng.drop();
            enemyMng.drop();
            pltfrmMng.addAll(currRoom.getPlatforms());
            enemyMng.addAll(currRoom.getEnemies());
            getType("Player").get(0).setCenter(currSpawn);
        }
    }
    public void setBloodOverlay()
    {
        setBloodImage();
        bloodOverlay = 25;
    }
    private void setBloodImage()
    {
        Random rand = new Random();
        String path = new String("bin\\images\\splatter\\Splatter" + new String(Integer.toString((rand.nextInt() % 5 + 1))) + ".png");
        try
        {
            blood = ImageIO.read(new File(path));
        }
        catch(IOException e)
        {
            System.out.println("Error Loading Blood Splatter Image!");
            System.out.println("Bad Path: " + path);
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
        if(!setOpaque)
        { //If the game is "running" (not paused)
            if(bloodOverlay > 0)
            {
                bloodOverlayG = blood.createGraphics();
                bloodOverlayG.fillRect(0, 0, getWidth(), getHeight());
                bloodOverlayG.setComposite(AlphaComposite.DstOut);
                g.drawImage(blood, null, 0, 0);
                bloodOverlay--;
            }
        }
        else
        { //If the game is "Paused"
            //From Here to (Look for Comment Here) Is a copy, I have no idea what it does or how to use it.
            //To Here

            //This is used to Gray out the background When Pause is Selected
            g.setComposite(AlphaComposite.SrcOver.derive(.1f)); //Lets make a box that is 10% opaque!
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());//Lets make it the size of the window!
            super.paint(g); //and lets throw it on top of everything!
            g.setColor(Color.WHITE);
            g.drawString("GAME PAUSED", 370, 15); //Let the Player know the game is Paused
            g.setColor(Color.WHITE);
            g.drawString("Press 'P' to Resume Game", 330, 30); //And how they Can Return to it!
            super.paint(g);
        }
    }
}
