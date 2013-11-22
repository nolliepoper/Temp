
import java.awt.*;
import javax.swing.*;

public class Pacer extends Enemy
{
    public static final double GRAVITY = 0.75;
    private Color color;
    Entity prev;
    double dirX;
    int minX, maxX;
    // Constructor.
    public Pacer(Vector vIn, int wIn, int hIn)
    {
        super(vIn, wIn, hIn);
        color = Color.GRAY;
        int c = 2; // Absolute value of horizontal velocity.
        dirX = dx = 2 * c * ((int)(Math.random() * 2)) - c; // Random to be negative or positive
        prev = null;
        minX = Integer.MIN_VALUE;
        maxX = Integer.MAX_VALUE;
        list.add("Player");
        list.add("Hopper");
        list.add("Platform");
    }
    @Override
    public void logic()
    {
        if(!isAlive())
        {
            return;
        }
        if(prev == null)
        {
            dy += GRAVITY;
            getDest().y = getCenter().y + (int)dy;
            prev = Collision.moveY(this);
            if(prev == null)
            {
                return;
            }
            minX = prev.getCenter().x - prev.getWidth()/2;
            maxX = prev.getCenter().x + prev.getWidth()/2;
        }
        
        int destX = getCenter().x + (int)dx;
        if(destX + getWidth()/2 < minX || destX - getWidth()/2 > maxX)
        {
            dirX *= -1;
            dx = dirX;
            destX = getCenter().x + (int)dx;
        }
        
        getDest().x = destX;        
        
        Entity tmpX = Collision.moveX(this);
        
        if(tmpX != null)
        {
            if(tmpX.toString().equals("Player"))
            {
                Player tmp = (Player)tmpX;
                tmp.kill();
            }
            else
            {
                dirX *= -1;
            }
            dx = dirX;
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
        gIn.fillRect(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2, getWidth(), getHeight());
    }
    @Override
    public void dispose()
    {
    }
}
