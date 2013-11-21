import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

/**
 * @author John Michael
 */
public class PowerUp extends Entity
{
	private static final int LIGHTRADIUS = 50;
	public Type type;
	
	//Default constructor using placeholder values
	public PowerUp()
	{
		super(new Vector(0, 0), 16, 16);
		getCenter().y = getCenter().y - getHeight()/2;
		this.type = Type.DOUBLEJUMP;
		sprite = new Sprite(SpriteSheet.POWERUP, type.aniNum);
		sprite.frame = 0;
	}
	
	public PowerUp(Vector vIn, Type type) {
		super(vIn, 16, 16);
		getCenter().y = getCenter().y - getHeight()/2;
		this.type = type;
		sprite = new Sprite(SpriteSheet.POWERUP, type.aniNum);
		sprite.frame = 0;
	}
	
	public void setType(String powUp)
	{
		//Change the type and sprite of the powerup
		this.type = Type.valueOf(powUp);
		sprite = new Sprite(SpriteSheet.POWERUP, type.aniNum);
	}
	
	@Override
	public void logic() 
	{}

	@Override
	public void paint(Graphics2D gIn) {
		sprite.draw(gIn, getCenter());
		
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
		WIDERLIGHT("Wider Flashlight Beam", 0), 
		DOUBLEJUMP("Double Jump", 1),
		FASTERRELOAD("Faster Reload", 2);
		
		String name;
		int aniNum;
		
		Type(String name, int aniNum)
		{
			this.name = name;
			this.aniNum = aniNum;
		}

		@Override
		public String toString() {
			return name;
		}
		
		
	}
}
