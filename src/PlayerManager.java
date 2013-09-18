
import java.awt.*;


public class PlayerManager extends Manager
{
    public PlayerManager(Frame fIn, Content mIn)
    {
        super(fIn, mIn);
        add(new Player(Color.BLUE, new Point(100, 100)));
    }
}
