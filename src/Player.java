
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    
    public Player(Color cIn, Point pIn)
    {
        super(cIn, pIn, new Point(50, 50));
        vel = new Velocity();
    }
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_W))
            vel.dy += -1;
        if(Keyboard.isPressed(KeyEvent.VK_A))
            vel.dx += -1;
        if(Keyboard.isPressed(KeyEvent.VK_S))
            vel.dy += 1;
        if(Keyboard.isPressed(KeyEvent.VK_D))
            vel.dx += 1;
        if(Keyboard.isPressed(KeyEvent.VK_SPACE))
        {
            vel.dy += -50; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
        }
        vel.logic();
        dest.x = point.x + (int)vel.dx;
        if(Collision.wallX(this))
            point.x = dest.x;
        dest.y = point.y + (int)vel.dy;
        if(Collision.wallY(this))
            point.y = dest.y;
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(color);
        gIn.fillRect(point.x, point.y, size.x, size.y);
    }
    @Override
    public void dispose()
    {
        
    }
}
