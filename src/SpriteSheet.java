
import java.awt.*;
import java.awt.geom.*;
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
	private int[][] anchorsX;
	private int[][] anchorsY;
	
	SpriteSheet(String imageName)
	{
		try
		{
			BufferedImage spriteSheet = ImageIO.read(new File("resources\\" + imageName + ".png"));
			Scanner spriteInfo = new Scanner(new File("resources\\" + imageName + ".spr"));
			frames = new BufferedImage[spriteInfo.nextInt()][];
			anchorsX = new int[frames.length][];
			anchorsY = new int[frames.length][];
			int width = spriteInfo.nextInt();
			int height = spriteInfo.nextInt();
			
			for(int i = 0; i< frames.length; i++)
			{
				spriteInfo.next();//Throw out the strings in the input file, their just there to make it more readable.
				frames[i] = new BufferedImage[spriteInfo.nextInt()];
				anchorsX[i] = new int[frames[i].length];
				anchorsY[i] = new int[frames[i].length];
				for(int j = 0; j < frames[i].length; j++)
				{		
					frames[i][j] = spriteSheet.getSubimage(spriteInfo.nextInt(), spriteInfo.nextInt(), width, height);
					anchorsX[i][j] = spriteInfo.nextInt();
					anchorsY[i][j] = spriteInfo.nextInt();
				}
			}
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public int numFrames(int animationNum)
	{
		return frames[animationNum].length;
	}
	
	public int numAnimations(int animationNum)
	{
		return frames.length;
	}
	
	public void drawFrame(Graphics2D gIn, Point point, int animationNum, int frameNum, double rotation, double xScale, double yScale)
	{
		AffineTransform trans = new AffineTransform();
		int centerX = (int)Math.round(anchorsX[animationNum][frameNum] * xScale);
		int centerY = (int)Math.round(anchorsY[animationNum][frameNum] * yScale);
		trans.rotate(Math.toRadians(rotation), centerX, centerY);
		trans.scale(xScale, yScale);
		
		gIn.drawImage(frames[animationNum][frameNum], new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR), 
				point.x - centerX, point.y - centerY);
	}
	
	public void drawFrame(Graphics2D gIn, Point point, int animationNum, int frameNum)
	{
		drawFrame(gIn, point, animationNum, frameNum, 0, 1, 1);
	}
}
