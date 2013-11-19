
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import javax.swing.*;

public class Player extends Entity
{
    Sprite legs;
	Sprite arm;
	boolean hasJumped = false;
	boolean autoFire = true;
	int reload = -1;
	double mouseDistance;
	private Vector shoulder = new Vector();
	
	public static HashSet<PowerUp.Type> powerUps = new HashSet<>();
    boolean singleJump;
    boolean doubleJump;
    public static final int WIDTH = 28;
    public static final int HEIGHT = 36;
    public static final double GRAVITY = 0.75;
    public static final int LIGHTRADIUS = 100;
	private static final Vector shoulderPos = SpriteSheet.PLAYER.getAnchor(1, 0).sub(SpriteSheet.PLAYER.getAnchor(0, 0));
    // Constructor
	
    public Player(Vector vIn)
    {
        super(vIn, WIDTH, HEIGHT);
        list.add("Platform");
        SpriteSheet sprites = SpriteSheet.PLAYER;
        sprite = new Sprite(sprites, 0);
        legs = new Sprite(sprites, 2);
        arm = new Sprite(sprites, 1);
        doubleJump = singleJump = false;
		Content.powerUpsMng.add(new PowerUp(new Vector(400,500), PowerUp.Type.DOUBLEJUMP));
		Content.powerUpsMng.add(new PowerUp(new Vector(100,500), PowerUp.Type.WIDERLIGHT));
		Content.powerUpsMng.add(new PowerUp(new Vector(300,500), PowerUp.Type.FASTERRELOAD));
    }
	
	 
    @Override
    public void logic()
    {
		move();
		shoot();
		physics();
		
        double xMoved = getCenter().x;
        Collision.moveX(this);
        //Collision.moveY(this);//we already have a movey somewhere else
		xMoved = getCenter().x - xMoved;
		
		annimate(xMoved);
    }
	
	
    @Override
    public void paint(Graphics2D gIn)
    {
		sprite.draw(gIn, getCenter());
		legs.draw(gIn, getCenter());
		arm.draw(gIn, shoulder);
		
		//lighting stuff
		float[] distribution = {0.0f, 0.4f, 1f};
		Color[] colors = {Color.WHITE, new Color(0, 0, 0, 128), new Color(0, 0, 0, 0)};
		RadialGradientPaint grad = new RadialGradientPaint(getCenter().toPoint(), LIGHTRADIUS, distribution, colors);
		Content.darkness.setPaint(grad);
		Content.darkness.fillOval(getCenter().x - LIGHTRADIUS, getCenter().y - LIGHTRADIUS, 2*LIGHTRADIUS, 2*LIGHTRADIUS);
		
		float LightDist = Math.min((float)mouseDistance + 100f, 400);
		int angle = Math.min(18000/(int)LightDist - 30, 90);
		if(powerUps.contains(PowerUp.Type.WIDERLIGHT))
			angle+=30;
        grad = new RadialGradientPaint(getCenter().toPoint(), LightDist, distribution, colors);
        Content.darkness.setPaint(grad);
        Content.darkness.fillArc(shoulder.x - (int)LightDist, shoulder.y - (int)LightDist, 2*(int)LightDist, 2*(int)LightDist,
                                 -(int)Math.toDegrees(arm.rotation) - angle / 2, angle);
    }
	
	public void move()
	{
	
		if(Keyboard.isPressed(KeyEvent.VK_A))
        {
            dx += -1;
        }
        if(Keyboard.isPressed(KeyEvent.VK_D))
        {
            dx += 1;
        }
        if(Keyboard.isOnce(KeyEvent.VK_SPACE) || Keyboard.isOnce(KeyEvent.VK_W))
        {
            Keyboard.useOnce(KeyEvent.VK_SPACE);
            Keyboard.useOnce(KeyEvent.VK_W);
            if(singleJump)
            {
                dy = -12; // Single jump.
                singleJump = false;
                doubleJump = true;
            }
            else if(doubleJump && powerUps.contains(PowerUp.Type.DOUBLEJUMP))
            {
                dy = -12; // Double jump.
                doubleJump = false;
            }
        }
	}
	
	public void shoot()
	{
		if(Mouse.isPressesd(1))
		{
			if(reload <= (autoFire? 0 : -1))
			{
				reload = powerUps.contains(PowerUp.Type.FASTERRELOAD)? 10: 30;
				Content.bulletMng.add(new Bullet(new Vector(shoulder.x, shoulder.y), arm.rotation));
			}
		}
		else
		{
			if(reload == 0)
				reload = -1;
		}
		if(reload > 0)
			reload -= 1;
	}
	
	public void physics()
	{
		if(dy >= 0 && dy <= GRAVITY)//I only want friction on the ground. Because of how collision works, dy is never quite 0 on the ground
			dx = Math.max(Math.min(dx + 0.2d, 0), dx - 0.2d); // Friction.
        dx = Math.min(Math.max(-2d, dx), 2d);//Max speed
        dy += GRAVITY;
        if(dy > 12)
        {
            dy = 12;
        }
        boolean down = false;
        if(dy > 0)
        {
            down = true;
        }
        getDest().x = getCenter().x + (int)dx;
        getDest().y = getCenter().y + (int)dy;
		
        Entity o = Collision.moveY(this);
        
        if(down && dy == 0 && o instanceof Platform)
        {
            singleJump = true;
        }
		
		list.add("PowerUp");//This is kind of a work around for Zach's weird way of collision checking
        Entity o1 = Collision.collisionX(this);
        Entity o2 = Collision.collisionY(this);
		if(o1 instanceof PowerUp)
		{
			powerUps.add(((PowerUp)o1).type);
			Content.powerUpsMng.remove(((PowerUp)o1));
		}
		else if(o2 instanceof PowerUp)
		{
			powerUps.add(((PowerUp)o2).type);
			Content.powerUpsMng.remove(((PowerUp)o2));
		}
		list.remove("PowerUp");//without this, the player will collide with power ups in the same way he collides with walls
	}
	
	public void annimate(double xMoved)
	{
		shoulder.x = getCenter().x + shoulderPos.x;
		shoulder.y = getCenter().y + shoulderPos.y;
		arm.rotation = Math.atan2(Mouse.Y() - getDest().y, Mouse.X() - getDest().x );
		mouseDistance = Math.hypot(Mouse.Y() - getDest().y, Mouse.X() - getDest().x );
		
		if(xMoved == 0)
			legs.animation = 2;
		else
		{
			legs.animation = 3;
			legs.frame += xMoved/25 * (int)sprite.xScale;
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
		/*if(Math.abs(dy) > GRAVITY)
		{
			legs.animation = 3;
			legs.frame = 1;
		}*/
			
	}
	
    @Override
    public void dispose()
    {
		
    }
}
