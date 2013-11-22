
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Hopper extends Enemy
{
    public static final double GRAVITY = 0.75;
    double dirX;
    private Color color;
    private int tick, wait;
    private boolean jump, collide;
    // Constructor.
    public Hopper()
    {
        super(new Vector(0, 0), 50, 50);
        color = Color.GREEN;
        int c = 3; // Absolute value of horizontal velocity.
        dirX = dx = 2 * c * ((int)(Math.random() * 2)) - c; // Random to be negative or positive.
        wait = tick = 100;
        collide = false;
        jump = collide = true;

        list.add("Player");
        list.add("Platform");
    }
    public Hopper(Vector vIn, int wIn, int hIn)
    {
        super(vIn, wIn, hIn);
        color = Color.GREEN;
        int c = 3; // Absolute value of horizontal velocity.
        dirX = dx = 2 * c * ((int)(Math.random() * 2)) - c; // Random to be negative or positive.
        wait = tick = 100;
        collide = false;
        jump = collide = true;

        list.add("Player");
        list.add("Platform");
    }
    @Override
    public void logic()
    {
        //Do nothing if it is dead
        if(!isAlive())
        {
            return;
        }
        if(jump)
        {
            if(!collide)
            {
                dx = dirX;
            }
            dy += GRAVITY;
            if(dy > 12)
            {
                dy = 12;
            }

            boolean down = false;
            if(dy > 0)
            {
                down = true;
            }

            getDest().x = getCenter().x + (int)dx;
            getDest().y = getCenter().y + (int)dy;

            Entity tmpX = Collision.moveX(this);
            Entity tmpY = Collision.moveY(this);

            if(tmpX != null)
            {
                if(tmpX.toString().equals("Player"))
                {
                    Player tmp = (Player)tmpX;
                    System.out.println("Player killed.");
                    tmp.kill();
                }
                else
                {
                    dirX *= -1;
                    collide = true;
                }
            }
            if(down && tmpY != null)
            {
                if(tmpY.toString().equals("Player"))
                {
                    Player tmp = (Player)tmpY;
                    tmp.kill();
                }
                else
                {
                    jump = false;
                }
            }
        }
        else
        {
            tick--;
            if(tick <= 0)
            {
                // Jump.
                dy -= 8;
                jump = true;
                collide = false;
                tick = wait;
            }
        }
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        //Do nothing if it is dead
        if(!isAlive())
        {
            return;
        }
        gIn.setColor(color);
        gIn.fillOval(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2, getWidth(), getHeight());
    }
    @Override
    public void dispose()
    {
    }
}
