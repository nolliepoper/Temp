
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Player extends Entity
{
    Sprite legs;
	Sprite arm;
	boolean hasJumped = false;
	
	public static final int WIDTH = 28;
    public static final int HEIGHT = 36;
    public static final double GRAVITY = 0.75;
	
    public Player(Vector vIn)
    {
        super(vIn, WIDTH, HEIGHT);
        list.add("Block");
        list.add("Platform");
		SpriteSheet sprites = SpriteSheet.PLAYER;
		sprite = new Sprite(sprites, 0);
		legs = new Sprite(sprites, 2);
		arm = new Sprite(sprites, 1);
    }
    /*
     public void move()
     {
     for(int i = 0; i < vec.length; i++)
     {
     vec[i].x = des[i].x;
     vec[i].y = des[i].y;
     }
     }
     */
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_A))
        {
            dx += -1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_D))
        {
            dx += 1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_SPACE) || Keyboard.isPressed(KeyEvent.VK_W))
        {
			if(!hasJumped)
			{
				hasJumped = true;
				dy = -12; // Jump.
			}
        }
		else
			hasJumped = false;
		
		
        dx = Math.max(Math.min(dx+0.2d, 0), dx-0.2d); // Friction.
		dx = Math.min(Math.max(-2d, dx), 2d);//Max speed
        dy += GRAVITY;
		if(dy > 12)
			dy = 12;
		
        getDest().x = getCenter().x + (int)dx;
        getDest().y = getCenter().y + (int)dy;

        Collision.moveX(this);
        Collision.moveY(this);
		System.out.println(dy);
		
		arm.rotation = Math.atan2(Mouse.Y() - getDest().y, Mouse.X() - getDest().x );
		
		//if(dy == 0)//I'll replace this soon
		//{
			if(dx == 0)
				legs.animation = 2;
			else
			{
				legs.animation = 3;
				legs.frame += dx/25 * (int)sprite.xScale;
			}
		//}
		//else
		//{
		//		legs.animation = 3;
		//		legs.frame = 1;
		//}
		if(getDest().x <= Mouse.X())
		{
			sprite.xScale = 1;
			arm.yScale = 1;
			legs.xScale = 1;
		}
		else
		{
			sprite.xScale = -1;
			arm.yScale = -1;
			legs.xScale = -1;
		}
    }
    @Override
    public void paint(Graphics2D gIn)
    {
		Point center = new Point(getCenter().x, getCenter().y);
		sprite.draw(gIn, center);
		legs.draw(gIn, center);
		Point shoulder = new Point(getCenter().x - 3 * (int)sprite.xScale, getCenter().y - 9);
		arm.draw(gIn, shoulder);
		
		
		BufferedImage temp = new BufferedImage(800, 800, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = temp.createGraphics();
		g2.setColor(new Color(0,0,0,223));
		g2.fillRect(0, 0, 800, 800);
		g2.setComposite(AlphaComposite.DstOut);
		g2.setColor(Color.white);
		g2.fillOval(getCenter().x - 25, getCenter().y - 25, 50, 50);
		//int shineX = (int)(50 * Math.cos(arm.rotation));
		//int shineY = (int)(50 * Math.sin(arm.rotation));
		//g2.fillOval(getCenter().x + shineX - 50, getCenter().y + shineY - 50, 100, 100);
		g2.fillArc(getCenter().x - 100, getCenter().y - 100, 200, 200, -(int)Math.toDegrees(arm.rotation), 45);
		g2.setPaintMode();
		gIn.drawImage(temp, null, 0, 0);
    }
    @Override
    public void dispose()
    {
    }
}
