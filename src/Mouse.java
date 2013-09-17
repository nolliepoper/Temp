import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Mouse implements MouseListener, MouseMotionListener
{
    private static final HashMap<Integer, Boolean> map = new HashMap<>();
    private static final HashMap<Integer, Point> click = new HashMap<>();
    private static final Point point = new Point();
    
    public static Point getClick(int cIn)
    {
        Point p = click.get(cIn);
        if(p == null)
            return null;
        return click.get(cIn);
    }
    public static Point getPoint()
    {
        return point;
    }
    public static boolean isPressesd(int cIn)
    {
        Boolean b = map.get(cIn);
        if(b == null)
            return false;
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
        
        click.put(c, eIn.getPoint());
        map.put(c, Boolean.TRUE);
        
        String s = MouseEvent.getMouseModifiersText(MouseEvent.getMaskForButton(c)); //Not important, only helps get mouse button string.
        System.out.println(c+"="+s); // Not important.
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
