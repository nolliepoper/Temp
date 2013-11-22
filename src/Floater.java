
import java.awt.*;
import javax.swing.*;

public class Floater extends Enemy
{
	private Color color;
	// Constructor.
	public Floater()
	{
		super(new Vector(300, 300), 20, 20);
		color = Color.CYAN;
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
	}
	@Override
	public void paint(Graphics2D gIn)
	{
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
