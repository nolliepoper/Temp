
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    
    public Player(Point pIn)
    {
        super(pIn, 25, 25);
        vel = new Velocity();
    }
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_W))
            ;//vel.dy += -1;
        if(Keyboard.isPressed(KeyEvent.VK_A))
            vel.dx += -1;
        if(Keyboard.isPressed(KeyEvent.VK_S))
            ;//vel.dy += 1;
        if(Keyboard.isPressed(KeyEvent.VK_D))
            vel.dx += 1;
        if(Keyboard.isPressed(KeyEvent.VK_SPACE))
        {
            vel.dy += -50; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
        }
        vel.logic();
        dest.x = getStart().x + (int)vel.dx;
        if(Collision.wallX(this))
            getStart().x = dest.x;
        dest.y = getStart().y + (int)vel.dy;
        if(Collision.wallY(this))
            getStart().y = dest.y;
    }
    @Override
    public void paint(Graphics2D gIn)
    {
		Point strt = getStart();
        gIn.setColor(Color.blue);
        gIn.fillRect(strt.x, strt.y, getSize().x, getSize().y);
    }
    @Override
    public void dispose()
    {
        
    }
}
