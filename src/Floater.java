
import java.awt.*;
import javax.swing.*;

public class Floater extends Enemy
{
	// Constructor.
	public Floater()
	{
		super(new Vector(300, 300), 20, 20);
		sprite = new Sprite(SpriteSheet.FLY, 0);
		list.add("Player");
	}
	private Vector getLoc()
	{
		return Content.playerManager.get(0).getCenter();
	}
	@Override
	public void logic()
	{
		if(!isAlive())
		{
			return;
		}

		Vector dis = getLoc().sub(getCenter());
		double mag = dis.mag();
		dx = (2.0 * dis.x) / mag;
		dy = (2.0 * dis.y) / mag;

		getDest().x = getCenter().x + (int)dx;
		getDest().y = getCenter().y + (int)dy;

		Entity tmpX = Collision.moveX(this);
		Entity tmpY = Collision.moveY(this);

		if(tmpX instanceof Player)
		{
			Player tmp = (Player)tmpX;
			tmp.kill();
		}
		else if(tmpY instanceof Player)
		{
			Player tmp = (Player)tmpY;
			tmp.kill();
		}
		
		if(dx != 0)
			sprite.xScale = dx/Math.abs(dx);
		sprite.frame += 1;
	}
	@Override
	public void paint(Graphics2D gIn)
	{
		if(!isAlive())
		{
			return;
		}
		sprite.draw(gIn, getCenter());
	}
	@Override
	public void dispose()
	{
	}
}
