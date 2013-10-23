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
		sprites = new SpriteSheet("player");
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
		double angle = Math.round(Math.toDegrees(Math.atan2(Mouse.Y() - point.y, Mouse.X() - point.x )));
		sprites.drawFrame(gIn, point, 0, 0);
		sprites.drawFrame(gIn, point, 2, 0);
		Point shoulder = new Point(point.x - 3, point.y - 9);
		sprites.drawFrame(gIn, shoulder, 1, 0, angle, 1, 1);
		gIn.fillRect(point.x, point.y, 4, 4);
    }
    @Override
    public void dispose()
    {
        
    }
}
