
import java.awt.Graphics2D;

/**
 * A class representing bullets fired by the player
 *
 * @author John Michael
 */
public class Bullet extends Entity
{
	double speed = 12d;
	// Constructor
	public Bullet(Vector pos, double angle)
	{
		super(pos, 12, 12);
		sprite = new Sprite(SpriteSheet.BULLET, 0);
		sprite.rotation = angle;
		dx = speed * Math.cos(angle);
		dy = speed * Math.sin(angle);
		list.add("Target");
		list.add("Hopper");
		list.add("Pacer");
		list.add("BreakableWall");
		list.add("Platform");
	}
	@Override
	public void logic()
	{
		getDest().x = getCenter().x + (int)dx;
		getDest().y = getCenter().y + (int)dy;

		sprite.frame += 1;

		Entity o1 = Collision.moveX(this);
		Entity o2 = Collision.moveY(this);

		if((o1 != null || o2 != null) || (getCenter().x > Frame.WIDTH || getCenter().x < 0 || getCenter().y > Frame.HEIGHT || getCenter().y < 0))
		{
			if(o1 instanceof Enemy)
			{
				((Enemy)o1).damage();
			}
			else if(o2 instanceof Enemy)
			{
				((Enemy)o2).damage();
			}
			Content.bulletMng.remove(this);
		}
	}
	@Override
	public void paint(Graphics2D gIn)
	{
		sprite.draw(gIn, getCenter());
	}
	@Override
	public void dispose()
	{
	}
}
