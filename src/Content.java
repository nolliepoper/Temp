import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.concurrent.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

// This class is the master content manager, it has a list of managers.
// Also, this class may manange a few list of entities on it's own.
public class Content extends JPanel
{
	private String LVL_PATH = "bin/roomFiles/";
	private String IMG_PATH = "bin/images/";
    private final Frame frame;
	RoomData rd;
    private final CopyOnWriteArrayList<Manager> list;
    private boolean run;
    
    public Content(Frame fIn)
    {
        frame = fIn;
        list = new CopyOnWriteArrayList<>();
        run = true;
        ObjectMapper mapper = new ObjectMapper();
		try {
			RoomData rd = mapper.readValue(new File(LVL_PATH + "start.json"), RoomData.class);
			System.out.println(rd.getBackground());
			System.out.println(rd.getTiled());
			System.out.println(rd.getPlatforms().get(0).getHeight());
			System.out.println(rd.getEnemies().get(2).getName());
			//System.out.println(rd.getPlatform(2).getHeight());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        setBackground(Color.WHITE);

        add(new Manager(frame, this));
        getLast().add(new Player(new Point(100, 100)));
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
        if(Keyboard.isPressed(KeyEvent.VK_P))
        {
            run = !run;
            Keyboard.release(KeyEvent.VK_P);
        }
        if(run)
        {
            if(Mouse.isPressesd(MouseEvent.BUTTON1))
            {
                Mouse.release(MouseEvent.BUTTON1);
            }
            for(Manager m: list)
                m.logic();
        }
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
