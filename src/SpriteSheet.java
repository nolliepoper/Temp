
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author John Michael
 */
public class SpriteSheet 
{
	private BufferedImage[][] frames;
	SpriteSheet(String imageName)
	{
		try
		{
			BufferedImage spriteSheet = ImageIO.read(new File("resources\\" + imageName + ".png"));
			Scanner spriteInfo = new Scanner(new File("resources\\" + imageName + ".spr"));
			frames = new BufferedImage[spriteInfo.nextInt()][];
			int width = spriteInfo.nextInt();
			int height = spriteInfo.nextInt();
			
			for(int i = 0; i< frames.length; i++)
			{
				frames[i] = new BufferedImage[spriteInfo.nextInt()];
				for(int j = 0; j < frames[i].length; j++)
					frames[i][j] = spriteSheet.getSubimage(spriteInfo.nextInt(), spriteInfo.nextInt(), width, height);
			}
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public void drawFrame(Graphics2D gIn, Point point, int animationNum, int frameNum, double rotation, double xScale, double yScale)
	{
		AffineTransform trans = new AffineTransform();
		trans.scale(xScale, yScale);
		trans.rotate(Math.toRadians(rotation));
		gIn.drawImage(frames[animationNum][frameNum], new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR), point.x, point.y);
	}
	
	public void drawFrame(Graphics2D gIn, Point point, int animationNum, int frameNum)
	{
		drawFrame(gIn, point, animationNum, frameNum, 0, 1, 1);
	}
}
