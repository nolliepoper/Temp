import javax.swing.*;

public class Paint implements Runnable
{
    private final JFrame frame;
    private final Content manager;
    
    public Paint(JFrame jIn, Content mIn)
    {
        frame = jIn;
        manager = mIn;
    }
    @Override
    public void run()
    {
        for(;;)
        {
            frame.repaint();
            //manager.repaint();
            Util.sleep(10);
        }
    }
}
