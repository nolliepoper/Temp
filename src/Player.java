import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends Entity
{
    private final Velocity vel;
    Sprite legs;
	Sprite arm;
	
    public Player(Point pIn)
    {
        super(pIn, new Point(50, 50));
        vel = new Velocity();
		SpriteSheet sprites = new SpriteSheet("player");
		sprite = new Sprite(sprites, 0);
		legs = new Sprite(sprites, 2);
		arm = new Sprite(sprites, 1);
    }
    @Override
    public void logic()
    {
        if(Keyboard.isPressed(KeyEvent.VK_W))
            ;//vel.dy += -1;
        if(Keyboard.isPressed(KeyEvent.VK_A))
            vel.dx += -1;
        if(Keyboard.isPressed(KeyEvent.VK_S))
            ;//vel.dy += 1;
        if(Keyboard.isPressed(KeyEvent.VK_D))
            vel.dx += 1;
        if(Keyboard.isPressed(KeyEvent.VK_SPACE))
        {
            vel.dy += -30; // Jump.
            Keyboard.release(KeyEvent.VK_SPACE);
        }
        vel.logic();
        dest.x = point.x + (int)vel.dx;
        if(Collision.wallX(this))
            point.x = dest.x;
        dest.y = point.y + (int)vel.dy;
        if(Collision.wallY(this))
            point.y = dest.y;
		
		arm.rotation = Math.round(Math.toDegrees(Math.atan2(Mouse.Y() - point.y, Mouse.X() - point.x )));
		vel.dx = Math.min(Math.max(-5d, vel.dx), 5d);
		
		if(vel.dx == 0)
			legs.animation = 2;
		else
		{
			legs.animation = 3;
			legs.frame += vel.dx/20 * (int)sprite.xScale;
		}
		if(point.x <= Mouse.X())
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
		sprite.draw(gIn, point);
		legs.draw(gIn, point);
		Point shoulder = new Point(point.x - 3 * (int)sprite.xScale, point.y - 9);
		arm.draw(gIn, shoulder);
    }
    @Override
    public void dispose()
    {
        
    }
}
