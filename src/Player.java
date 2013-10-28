
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    private final Vector[] vec;
    private final Vector[] des;
    Sprite legs;
	Sprite arm;
    
    public Player(Vector vIn)
    {
        super(vIn, 30, 30);
        vec = new Vector[]
        {
            new Vector(getCenter().x, getCenter().y),
            new Vector(getCenter().x + getWidth(), getCenter().y),
            new Vector(getCenter().x, getCenter().y + getHeight()),
            new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())
        };
        des = new Vector[]
        {
            new Vector(getCenter().x, getCenter().y),
            new Vector(getCenter().x + getWidth(), getCenter().y),
            new Vector(getCenter().x, getCenter().y + getHeight()),
            new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())
        };
        vel = new Velocity();
        list.add("Block");
        list.add("Platform");
		SpriteSheet sprites = new SpriteSheet("player");
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
        if(Keyboard.isPressed(KeyEvent.VK_W))
            ;//vel.dy += -1;
        if(Keyboard.isPressed(KeyEvent.VK_A))
        {
            vel.dx += -1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_S))
            ;//vel.dy += 5;
        if(Keyboard.isPressed(KeyEvent.VK_D))
        {
            vel.dx += 1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_SPACE))
        {
            vel.dy += -50; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
        }

        vel.logic();
        getDest().x = getCenter().x + (int)vel.dx;
        getDest().y = getCenter().y + (int)vel.dy;

        Collision.moveX(this);
        Collision.moveY(this);
		
		
		arm.rotation = Math.round(Math.toDegrees(Math.atan2(Mouse.Y() - getDest().y, Mouse.X() - getDest().x )));
		vel.dx = Math.min(Math.max(-3d, vel.dx), 3d);
		
		if(vel.dx == 0)
			legs.animation = 2;
		else
		{
			legs.animation = 3;
			legs.frame += vel.dx/30 * (int)sprite.xScale;
		}
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
		Point center = new Point(getDest().x, getDest().y);
		sprite.draw(gIn, center);
		legs.draw(gIn, center);
		Point shoulder = new Point(getDest().x - 3 * (int)sprite.xScale, getDest().y - 9);
		arm.draw(gIn, shoulder);
    }
    @Override
    public void dispose()
    {
    }
}
