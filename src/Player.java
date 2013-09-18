
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
            vel.dy += -50;
            Keyboard.release(KeyEvent.VK_SPACE);
        }
        vel.logic();
        if(Collision.canMoveX(this, vel.dx))
            point.x += vel.dx;
        if(Collision.canMoveY(this, vel.dy))
            point.y += vel.dy;
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
