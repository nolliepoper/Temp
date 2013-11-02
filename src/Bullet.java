
import java.awt.Graphics2D;

/**
 * A class representing bullets fired by the player
 * @author John Michael
 */
public class Bullet extends Entity {
	int prevX;
	int prevY;
	double angle;
	double speed = 5d;

	@Override
	public void logic() {
		prevX = getCenter().x;
		prevY = getCenter().y;
		
		getCenter().x += (int)(speed * Math.cos(angle));
		getCenter().y += (int)(speed * Math.sin(angle));
		if(getCenter().x > Content.WIDTH || getCenter().x < 0
				|| getCenter().y > Content.HEIGHT || getCenter().y < 0)
			dispose();
	}

	@Override
	public void paint(Graphics2D gIn) {
		gIn.drawLine(prevX, prevY, getCenter().x, getCenter().y);
	}

	@Override
	public void dispose() {
	}
	
}
