
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    private final Vector[] vec;
    private final Vector[] des;
    
    public Player(Vector vIn)
    {
        super(vIn, 30, 30);
        vec = new Vector[]
        {
            new Vector(getCenter().x, getCenter().y),
            new Vector(getCenter().x + getWidth(), getCenter().y),
            new Vector(getCenter().x, getCenter().y + getHeight()),
            new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())
        };
        des = new Vector[]
        {
            new Vector(getCenter().x, getCenter().y),
            new Vector(getCenter().x + getWidth(), getCenter().y),
            new Vector(getCenter().x, getCenter().y + getHeight()),
            new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())
        };
        vel = new Velocity();
        list.add("Block");
        list.add("Platform");
    }
    /*
     public void move()
     {
     for(int i = 0; i < vec.length; i++)
     {
     vec[i].x = des[i].x;
     vec[i].y = des[i].y;
     }
     }
     */
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_W))
            ;//vel.dy += -1;
        if(Keyboard.isPressed(KeyEvent.VK_A))
        {
            vel.dx += -1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_S))
            ;//vel.dy += 5;
        if(Keyboard.isPressed(KeyEvent.VK_D))
        {
            vel.dx += 1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_SPACE))
        {
            vel.dy += -50; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
        }

        vel.logic();
        getDest().x = getCenter().x + (int)vel.dx;
        getDest().y = getCenter().y + (int)vel.dy;

        Collision.moveX(this);
        Collision.moveY(this);
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(Color.blue);
        gIn.fillRect(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2, getWidth(), getHeight());
    }
    @Override
    public void dispose()
    {
    }
}
