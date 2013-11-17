import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

/**
 * @author John Michael
 */
public class PowerUp extends Entity
{
	private static final int LIGHTRADIUS = 50;
	public PowerUp(Vector vIn) {
		super(vIn, 16, 16);
	}
	
	@Override
	public void logic() {}

	@Override
	public void paint(Graphics2D gIn) {
		gIn.setColor(Color.red);
		gIn.fillRect(getCenter().x - getWidth()/2, getCenter().y - getHeight()/2, getWidth(), getHeight());
		
		
		//lighting stuff
		float[] distribution = {0.0f, 0.3f, 1f};
		Color[] colors = {Color.WHITE, new Color(0, 0, 0, 128), new Color(0, 0, 0, 0)};
		RadialGradientPaint grad = new RadialGradientPaint(getCenter(), LIGHTRADIUS, distribution, colors);
		Content.darkness.setPaint(grad);
		Content.darkness.fillOval(getCenter().x - 800, getCenter().y - 800, 1600, 1600);
	}

	@Override
	public void dispose() {}
}
