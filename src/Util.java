import java.awt.*;

// Utilities class for universal static methods.
public class Util
{
    public static void sleep(long tIn)
    {
        try
        {
            Thread.sleep(tIn);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static Color randomColor()
    {
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random());
    }
}
