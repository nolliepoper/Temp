
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    private final Vector[] vec;
    private final Vector[] des;
    
    public Player(Color cIn, Vector vIn)
    {
        super(cIn, vIn, new Vector(50, 50));
        vec = new Vector[]
        {new Vector(point.x, point.y),
        new Vector(point.x + size.x, point.y),
        new Vector(point.x, point.y + size.y),
        new Vector(point.x + size.x, point.y + size.y)};
        des = new Vector[]
        {new Vector(point.x, point.y),
        new Vector(point.x + size.x, point.y),
        new Vector(point.x, point.y + size.y),
        new Vector(point.x + size.x, point.y + size.y)};
        vel = new Velocity();
        list.add("Block");
    }
    public void move()
    {
        for(int i = 0; i < vec.length; i++)
        {
            vec[i].x = des[i].x;
            vec[i].y = des[i].y;
        }
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
        dest.y = point.y + (int)vel.dy;
        
        
        // Here is where you can handle the response to a collision with another Entity.
        if(Collision.collisionX(this) != null)
        {
            dest.x = point.x;
        }
        // Wall collisions can be handled later, when we have walls composed of blocks.
        else if(Collision.wallX(this))
            dest.x = point.x;
        else
            point.x = dest.x;
        
        if(Collision.collisionY(this) != null)
        {
            dest.y = point.y;
        }
        else if(Collision.wallY(this))
            dest.y = point.y;
        else
            point.y = dest.y;
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(color);
        gIn.fillRect(point.x - size.x/2, point.y - size.y/2, size.x, size.y);
    }
    @Override
    public void dispose()
    {
        
    }
}
