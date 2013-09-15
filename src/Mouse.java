import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Mouse implements MouseListener, MouseMotionListener
{
    private HashMap<Integer, Boolean> map;
    private Point point;
    private Point click;
    
    public Mouse()
    {
        map = new HashMap<>();
        point = new Point();
        click = new Point();
    }
    public Point getClick()
    {
        return click;
    }
    public boolean isDown(int k)
    {
        if(map.get(k) == null)
            return false;
        return map.get(k);
    }
    public void release(int k)
    {
        map.put(k, false);
    }
    @Override
    public void mouseClicked(MouseEvent eIn)
    {
    }
    @Override
    public void mousePressed(MouseEvent eIn)
    {
        int c = eIn.getButton();
        click.x = eIn.getX();
        click.y = eIn.getY();
        
        map.put(c, true);
        //Util.sleep(10);
        //map.put(c, false);
        
        
        System.out.println(c);
    }
    @Override
    public void mouseReleased(MouseEvent eIn)
    {
        int c = eIn.getButton();
        map.put(c, false);
    }
    @Override
    public void mouseEntered(MouseEvent eIn)
    {
        System.out.println("HAS ENTERED!");
    }
    @Override
    public void mouseExited(MouseEvent eIn)
    {
    }
    @Override
    public void mouseDragged(MouseEvent me)
    {
    }
    @Override
    public void mouseMoved(MouseEvent me)
    {
    }
}
