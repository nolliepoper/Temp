
import java.awt.Color;
import java.awt.Graphics2D;

/* FloaterNo1
 * This is an enemy that Branden requested. He floats through surfaces and to
 * the players location. He will be fast at far distances but slow close to the
 * player. He will not collide with anything besides the player. Think Boo from
 * super mario. Also, we can implement a momentum to him if we want to make him
 * easier to avoid.
 */
public class FloaterNo1 extends Entity
{
	private Manager manager;
	private Color color;
	//This should be where the sprite sheet is attached (Branden's code from Enemy)
	private String sprites;
	private String name;
	private int health; //How many hits before the poor guy dies
	private static Player player; //To get the players location
	// Constructor
	public FloaterNo1()
	{
	}
	public FloaterNo1(Vector vIn, int wIn, int hIn, Manager mIn, Player pIn)
	{
		super(vIn, wIn, hIn);
		name = "LurkerNo2"; //Default Name, can be changed
		health = 2;
		manager = mIn;
		dx = 5;
		player = pIn;
		color = Color.ORANGE;
		list.add("Platform");
	}
	void die()
	{
		System.out.println("FloaterNO1 Hit");
		health--;
		if(health <= 0)
		{
			manager.remove(this);
			System.out.println("FloaterNo1 Dead");
		}
	}
	public void setName(String nIn)
	{
		name = nIn;
	}
	public String getName()
	{
		return name;
	}
	@Override
	public void logic()
	{
		int diffx, diffy;
		getDest().x = getCenter().x + (int)dx;
		getDest().y = getCenter().y + (int)dy; //This guy moves up and down unlike the others
		double prex = dx, prey = dy;
		Entity tmp = Collision.moveX(this);
		Collision.moveY(this);
		//Do we need this collision if we want him to float through objects?
		if(tmp != null && (tmp.getClass().getName() + "").equals("Platform"))
		{
			dx = -prex; //If a collision is found, or he has reached the end of his patrol in one direction.
			dy = -prey;
		}
		diffx = player.getCenter().x - getCenter().x;
		diffy = player.getCenter().y - getCenter().y;
		if(diffx > 0)
		{
			dx = 3; //Go Right!
		}
		else
		{
			dx = -3; //Go left!
		}
		if(Math.abs(diffx) < 20)
		{
			dx = dx / 2; //Should always be 1.5 because dx is set most recently above this line 
		}
		if(diffy > 0)
		{
			dy = 3; //Go down!
		}
		else
		{
			dy = -3; //Go up!
		}
		if(Math.abs(diffy) < 20)
		{
			dy = dy / 2; //Should always be 1.5 because dx is set most recently above this line 
		}
	}
	@Override
	public void paint(Graphics2D gIn)
	{
		gIn.setColor(color);
		gIn.fillOval(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2, getWidth(), getHeight());
	}
	@Override
	public void dispose()
	{
	}
}
