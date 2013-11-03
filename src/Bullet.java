
import java.awt.Graphics2D;

/**
 * A class representing bullets fired by the player
 * @author John Michael
 */
public class Bullet extends Entity {
	double speed = 10d;
	int prevX;
	int prevY;
	public Bullet(Vector pos, double angle) 
	{
        super(pos, 1, 1);
		dx = speed * Math.cos(angle);
		dy = speed * Math.sin(angle);
	}

	
	@Override
	public void logic() {
		prevX = getDest().x;
		prevY = getDest().y;
		
        getDest().x = getCenter().x + (int)dx;
        getDest().y = getCenter().y + (int)dy;
		
        Entity o1 = Collision.moveX(this);
        Entity o2 = Collision.moveY(this);
		
		
		if( (o1 != null || o2 != null) ||
				(getCenter().x > Frame.WIDTH || getCenter().x < 0 || getCenter().y > Frame.HEIGHT || getCenter().y < 0))
		{
		Content.bulletMng.remove(this);
			System.out.println("boom");
		}
	}

	@Override
	public void paint(Graphics2D gIn) {
		gIn.drawLine(prevX, prevY, getCenter().x, getDest().y );
	}

	@Override
	public void dispose() {
	}
	
}
