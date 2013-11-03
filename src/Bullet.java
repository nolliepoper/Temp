
import java.awt.Graphics2D;

/**
 * A class representing bullets fired by the player
 * @author John Michael
 */
public class Bullet extends Entity
{
    double speed = 10d;
    int prevX;
    int prevY;
    // Constructor
    public Bullet(Vector pos, double angle)
    {
        super(pos, 1, 1);
        dx = speed * Math.cos(angle);
        dy = speed * Math.sin(angle);
        list.add("Target");
    }
    @Override
    public void logic()
    {
        prevX = getDest().x;
        prevY = getDest().y;

        getDest().x = getCenter().x + (int)dx;
        getDest().y = getCenter().y + (int)dy;

        Entity o1 = Collision.moveX(this);
        Entity o2 = Collision.moveY(this);

        if((o1 != null || o2 != null) || (getCenter().x > Frame.WIDTH || getCenter().x < 0 || getCenter().y > Frame.HEIGHT || getCenter().y < 0))
        {
            if(o1 != null && (o1.getClass().getName()+"").equals("Target"))
            {
                Target tmp = (Target)o1;
                tmp.die();
            }
            else if(o2 != null && (o2.getClass().getName()+"").equals("Target"))
            {
                Target tmp = (Target)o2;
                tmp.die();
            }
            Content.bulletMng.remove(this);
            System.out.println("boom");
        }
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.drawLine(prevX, prevY, getCenter().x, getDest().y);
    }
    @Override
    public void dispose()
    {
    }
}
