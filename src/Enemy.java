
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics2D;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
		{
	@JsonSubTypes.Type(value = Target.class, name = "Target"),
	@JsonSubTypes.Type(value = Hopper.class, name = "Hopper"),
	@JsonSubTypes.Type(value = Pacer.class, name = "Pacer"),
	@JsonSubTypes.Type(value = Floater.class, name = "Floater"),
	@JsonSubTypes.Type(value = BreakableWall.class, name = "BreakableWall")
})
/**
 *
 * @author Branden
 */
public abstract class Enemy extends Entity
{
	//This should be where the sprite sheet is attached
	private String sprites;
	//How many hits before the poor guy dies
	private int health;
	private boolean alive;
	// Constructor.
	Enemy()
	{
		super(new Vector(50, 50), 50, 50);
		alive = true;
		health = 5;
	}
	public Enemy(Vector vIn, int wIn, int hIn)
	{
		super(vIn, wIn, hIn);
		alive = true;
		health = 5;
	}
	public void setHp(int hp)
	{
		health = hp;
	}
	public void damage()
	{
		health--;
		if(health <= 0)
		{
			kill();
		}
	}
	public void kill()
	{
		alive = false;
		getCenter().x = -1000; // Move off screen.
		getCenter().y = -1000;
	}
	public boolean isAlive()
	{
		return alive;
	}
	@Override
	public abstract void logic();
	@Override
	public abstract void paint(Graphics2D gIn);
	@Override
	public abstract void dispose();
}