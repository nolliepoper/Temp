
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
    public static Color randomSolidColor()
    {
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
    }
    public static Color randomTransColor()
    {
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random());
    }
}
