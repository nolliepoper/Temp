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
        super(vIn, 50, 50);
        vec = new Vector[]
        {new Vector(getCenter().x, getCenter().y),
        new Vector(getCenter().x + getWidth(), getCenter().y),
        new Vector(getCenter().x, getCenter().y + getHeight()),
        new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())};
        des = new Vector[]
        {new Vector(getCenter().x, getCenter().y),
        new Vector(getCenter().x + getWidth(), getCenter().y),
        new Vector(getCenter().x, getCenter().y + getHeight()),
        new Vector(getCenter().x + getWidth(), getCenter().y + getHeight())};
        vel = new Velocity();
        list.add("Block");
        list.add("Platform");
		SpriteSheet sprites = new SpriteSheet("player");
		sprite = new Sprite(sprites, 0);
		legs = new Sprite(sprites, 2);
		arm = new Sprite(sprites, 1);
    }
	
    public void move()
    {
        for(int i = 0; i < vec.length; i++)
        {
            vec[i].x = des[i].x;
            vec[i].y = des[i].y;
        }
    }
	
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_A))
            vel.dx += -1;
        if(Keyboard.isPressed(KeyEvent.VK_D))
            vel.dx += 1;
        if(Keyboard.isPressed(KeyEvent.VK_SPACE) || Keyboard.isPressed(KeyEvent.VK_W))
        {
            vel.dy += -50; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
            Keyboard.release(KeyEvent.VK_W);
        }
        
        vel.logic();
        getDest().x = getCenter().x + (int)vel.dx;
        getDest().y = getCenter().y + (int)vel.dy;
        
        
        // Here is where you can handle the response to a collision with another Entity.
        if(Collision.collisionX(this) != null)
        {
            getDest().x = getCenter().x;
        }
        // Wall collisions can be handled later, when we have walls composed of blocks.
        else if(Collision.wallX(this))
            getDest().x = getCenter().x;
        else
            getCenter().x = getDest().x;
        
        if(Collision.collisionY(this) != null)
        {
            getDest().y = getCenter().y;
        }
        else if(Collision.wallY(this))
            getDest().y = getCenter().y;
        else
            getCenter().y = getDest().y;
		
		arm.rotation = Math.round(Math.toDegrees(Math.atan2(Mouse.Y() - getDest().y, Mouse.X() - getDest().x )));
		vel.dx = Math.min(Math.max(-5d, vel.dx), 5d);
		
		if(vel.dx == 0)
			legs.animation = 2;
		else
		{
			legs.animation = 3;
			legs.frame += vel.dx/20 * (int)sprite.xScale;
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
