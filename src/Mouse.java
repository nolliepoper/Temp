
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Mouse implements MouseListener, MouseMotionListener
{
    private static final HashMap<Integer, Boolean> map = new HashMap<>();
    private static final HashMap<Integer, Vector> click = new HashMap<>();
    private static final Vector point = new Vector(0, 0);
    
    public static Vector getClick(int cIn)
    {
        return click.get(cIn);
    }
    public static Vector getPoint()
    {
        return point;
    }
    public static boolean isPressesd(int cIn)
    {
        Boolean b = map.get(cIn);
        if(b == null)
        {
            return false;
        }
        return b;
    }
    public static void release(int kIn)
    {
        map.put(kIn, Boolean.FALSE);
    }
    @Override
    public void mouseClicked(MouseEvent eIn)
    {
    }
    @Override
    public void mousePressed(MouseEvent eIn)
    {
        int c = eIn.getButton();

        click.put(c, new Vector(eIn.getPoint().x, eIn.getPoint().y));
        map.put(c, Boolean.TRUE);

        String s = MouseEvent.getMouseModifiersText(MouseEvent.getMaskForButton(c)); //Not important, only helps get mouse button string.
        System.out.println(c + "=" + s); // Not important.
    }
    @Override
    public void mouseReleased(MouseEvent eIn)
    {
        int c = eIn.getButton();
        map.put(c, Boolean.FALSE);
    }
    @Override
    public void mouseEntered(MouseEvent eIn)
    {
    }
    @Override
    public void mouseExited(MouseEvent eIn)
    {
    }
    @Override
    public void mouseDragged(MouseEvent eIn)
    {
    }
    @Override
    public void mouseMoved(MouseEvent eIn)
    {
        point.x = eIn.getX();
        point.y = eIn.getY();
    }
}
