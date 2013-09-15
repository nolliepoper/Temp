import java.awt.*;

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
        
        if(frame.getMouse().isDown(1))
        {
            add(new Ball(Util.randomColor(), new Point(frame.getMouse().getClick())));
            frame.getMouse().release(1);
        }
        if(frame.getKeyboard().isDown(32))
        {
            remove(getRandom());
            frame.getKeyboard().release(32);
        }
    }
}
