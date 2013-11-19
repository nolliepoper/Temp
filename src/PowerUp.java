import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

/**
 * @author John Michael
 */
public class PowerUp extends Entity
{
	private static final int LIGHTRADIUS = 50;
	private Type type;
	
	public PowerUp(Vector vIn, Type type) {
		super(vIn, 16, 16);
		getCenter().y = getCenter().y - getHeight()/2;
		list.add("Player");
		this.type = type;
	}
	
	@Override
	public void logic() 
	{
        Entity o1 = Collision.collisionX(this);
        Entity o2 = Collision.collisionY(this);
		if(o1 != null || o2 != null)
		{
			if(o1 instanceof Player)
				((Player)o1).powerUps.add(type);
			else if(o2 instanceof Player)
				((Player)o2).powerUps.add(type);
			Content.powerUpsMng.remove(this);
		}
	}

	@Override
	public void paint(Graphics2D gIn) {
		gIn.setColor(Color.red);
		gIn.fillRect(getCenter().x - getWidth()/2, getCenter().y - getHeight()/2, getWidth(), getHeight());
		
		
		//lighting stuff
		float[] distribution = {0.0f, 0.3f, 1f};
		Color[] colors = {Color.WHITE, new Color(0, 0, 0, 128), new Color(0, 0, 0, 0)};
		RadialGradientPaint grad = new RadialGradientPaint(getCenter().toPoint(), LIGHTRADIUS, distribution, colors);
		Content.darkness.setPaint(grad);
		Content.darkness.fillOval(getCenter().x - 800, getCenter().y - 800, 1600, 1600);
	}

	@Override
	public void dispose() {}
	
	public static enum Type
	{
		DOUBLEJUMP("Double Jump")
		, BETTERLIGHT("Wider Flashlight Beam");
		
		String name;
		
		Type(String name)
		{
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
		
		
	}
}
