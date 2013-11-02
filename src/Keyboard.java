
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class Keyboard implements KeyListener
{
    private static final HashMap<Integer, Boolean> map = new HashMap<>();
    
    public static boolean isPressed(int kIn)
    {
        Boolean k = map.get(kIn);
        if(k == null)
        {
            return false;
        }
        return k;
    }
    /**
    * @deprecated This can potentially lead to all sorts of problems. The keyboard map should accurately represent which keys are being held down at what time.
    */
    public static void release(int kIn)
    {
        map.put(kIn, Boolean.FALSE); // Use static variable from Boolean class so it won't make a new Boolean object inside the map.
    }
    @Override
    public void keyPressed(KeyEvent eIn)
    {
        int k = eIn.getKeyCode();
        map.put(k, Boolean.TRUE);
    }
    @Override
    public void keyTyped(KeyEvent eIn)
    {
    }
    @Override
    public void keyReleased(KeyEvent eIn)
    {
        int k = eIn.getKeyCode();
        map.put(k, Boolean.FALSE);
    }
}
