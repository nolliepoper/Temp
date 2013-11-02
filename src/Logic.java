
import javax.swing.*;

public class Logic implements Runnable
{
    private final JFrame frame;
    private final Content manager;
    // Constructor
    public Logic(JFrame jIn, Content mIn)
    {
        frame = jIn;
        manager = mIn;
    }
    @Override
    public void run()
    {
        for(;;)
        {
            manager.logic();
            Util.sleep(17);
        }
    }
}
