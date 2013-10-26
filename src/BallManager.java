import java.awt.*;
import java.awt.event.*;

public class BallManager<T extends Entity> extends Manager
{
    public BallManager(Frame fIn, Content mIn)
    {
        super(fIn, mIn);
    }
    @Override
    public void logic()
    {
        super.logic();
        
        if(Mouse.isPressesd(MouseEvent.BUTTON1))
        {
            add(new Ball(Util.randomTransColor(), Mouse.getClick(MouseEvent.BUTTON1)));
            Mouse.release(MouseEvent.BUTTON1);
        }
        if(Keyboard.isPressed(KeyEvent.VK_BACK_SPACE))
        {
            remove(getRandom());
            Keyboard.release(KeyEvent.VK_BACK_SPACE);
        }
    }
}
