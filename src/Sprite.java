
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A class that holds sprite information, and makes animating easier. This class
 * is technically not necessary, but it is much easier than using the
 * SpriteSheet's draw function.
 *
 * @author John Michael
 */
public class Sprite
{
	public SpriteSheet spriteSheet;
	public int animation;
	public double frame = 0;
	double rotation = 0;
	double xScale = 1;
	double yScale = 1;
	// Constructor
	Sprite(SpriteSheet spriteSheet, int animation)
	{
		this.spriteSheet = spriteSheet;
		this.animation = animation;
	}
	public void draw(Graphics2D gIn, Vector point)
	{
		frame = ((frame % spriteSheet.numFrames(animation)) + spriteSheet.numFrames(animation)) % spriteSheet.numFrames(animation);
		int subimage = (int)frame;
		spriteSheet.drawFrame(gIn, point, animation, subimage, rotation, xScale, yScale);
		
	}
}