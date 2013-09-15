import java.io.*;
import java.util.*;
import java.awt.event.*;

public class KeyBoard implements KeyListener
{
    private final HashMap<Integer, Boolean> map;
    private final Content manager;
    
    public KeyBoard(Content mIn)
    {
        manager = mIn;
        map = new HashMap<>();
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
    public void keyPressed(KeyEvent e)
    {
        int c = e.getKeyCode();
        map.put(c, true);
        //Util.sleep(10);
        //map.put(c, false);
        
        String s = KeyEvent.getKeyText(c); // Not important.
        System.out.println(c+"="+s); // Not important.
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        int c = e.getKeyCode();
        map.put(c, false);
    }
}
