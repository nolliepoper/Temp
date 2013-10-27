import java.awt.*;
import javax.swing.*;

public class Ball extends Entity
{
    private int a; // How many pixels to move.
    
    public Ball(Color cIn, Point pIn)
    {
        super(pIn, new Point(50, 50));
        //a = (int)(Math.random() * 50);
        a = 1;
        
    }
    @Override
    public void logic()
    {
        point.x += a*(int)(Math.random() * 3) - a;
        point.y += a*(int)(Math.random() * 3) - a;
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(Color.RED);
        gIn.fillOval(point.x, point.y, size.x, size.y);
    }
    @Override
    public void dispose()
    {
        
    }
}
