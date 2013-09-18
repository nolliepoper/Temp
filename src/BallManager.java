import java.awt.*;
import java.awt.event.*;

public class BallManager extends Manager
{
    public BallManager(Frame fIn, Content mIn)
    {
        super(fIn, mIn);
    }
    public Entity getRandom()
    {
        if(list.size() > 0)
            return list.get((int)(Math.random() * list.size()));
        return null;
    }
    @Override
    public void logic()
    {
        super.logic();
        
        if(Mouse.isPressesd(MouseEvent.BUTTON1))
        {
            add(new Ball(Util.randomColor(), Mouse.getClick(MouseEvent.BUTTON1)));
            Mouse.release(MouseEvent.BUTTON1);
        }
        if(Keyboard.isPressed(KeyEvent.VK_BACK_SPACE))
        {
            remove(getRandom());
            Keyboard.release(KeyEvent.VK_BACK_SPACE);
        }
    }
}
