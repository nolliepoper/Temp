
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 <p> This is an enum that contains all the sprite sheets. A sprite sheet is simply a large image that contains all the frames for an animated sprite. This is an enum because there will be a fixed number of image files in the final project, and I don't want entities creating duplicates of each sprite sheet. </p>

 <p> Each sprite sheet consists of a single .png image and another file with the same name but a .spr extension. This .spr file is a simple text file that contains information on how the .png image should be broken up into different frames. The .spr uses the following format: </p>
 <pre> &#60# of animations&#62 &#60Width&#62 &#60Height&#62

 &#60Animation 1 name&#62 &#60number of frames&#62

 &#60Left coordinate&#62 &#60Top coordinate&#62 &#60Anchor coordinate&#62 &#60Anchor coordinate&#62
	
 &#60Left coordinate&#62 &#60Top coordinate&#62 &#60Anchor coordinate&#62 &#60Anchor coordinate&#62

 ...
 &#60Animation 2 name&#62 &#60number of frames&#62

 &#60Left coordinate&#62 &#60Top coordinate&#62 &#60Anchor coordinate&#62 &#60Anchor coordinate&#62
	
 &#60Left coordinate&#62 &#60Top coordinate&#62 &#60Anchor coordinate&#62 &#60Anchor coordinate&#62

 ...
 ...</pre>
 * @author John Michael
 */
public enum SpriteSheet
{
    PLAYER("player");
    private BufferedImage[][] frames;
    private int[][] anchorsX;
    private int[][] anchorsY;
    private SpriteSheet(String imageName)
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

            for(int i = 0; i < frames.length; i++)
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
        trans.rotate(rotation, centerX, centerY);
        trans.scale(xScale, yScale);

        gIn.drawImage(frames[animationNum][frameNum], new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR),
                point.x - centerX, point.y - centerY);
    }
    public void drawFrame(Graphics2D gIn, Point point, int animationNum, int frameNum)
    {
        drawFrame(gIn, point, animationNum, frameNum, 0, 1, 1);
    }
}
