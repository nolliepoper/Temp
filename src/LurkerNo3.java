
import java.awt.Color;
import java.awt.Graphics2D;
/* Lurker #3
 * This is a fast Lurker that moves towards the player's x location. He has two
 * health and is meant to surprise the player more than anyhting. 
 */

public class LurkerNo3 extends Entity
{
	private Manager manager;
	private Color color;
	//This should be where the sprite sheet is attached
	private String sprites;
	private String name;
	private int health; //How many hits before the poor guy dies
	private static Player player; //To get the players location
	// Constructor
	public LurkerNo3(Vector vIn, int wIn, int hIn, Manager mIn, Player pIn)
	{
		super(vIn, wIn, hIn);
		name = "LurkerNo2"; //Default Name, can be changed
		health = 2;
		manager = mIn;
		dx = 5;
		color = Color.GREEN;
		player = pIn;
		list.add("Platform");
	}
	void die()
	{
		System.out.println("LurkerNO3 Hit");
		health--;
		if(health <= 0)
		{
			manager.remove(this);
			System.out.println("LurkerNo3 Dead");
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
		getDest().x = getCenter().x + (int)dx;
		getDest().y = getCenter().y;
		double pre = dx;
		Entity tmp = Collision.moveX(this);
		Collision.moveY(this);
		if(tmp != null && (tmp.getClass().getName() + "").equals("Platform"))
		{
			dx = -pre;
		}
		//What I put below might not work well with the collision, it's all black magic to me!!
		if(player.getCenter().x - getCenter().x > 0) //Is the player to the right of the enemy?
		{
			dx = 5; //Yes, go to the right and get them!
		}
		else //No, they're to the left or directly above :(
		{
			dx = -5; //Go to the left and get them, champ!
		}
	}
	@Override
	public void paint(Graphics2D gIn)
	{
		gIn.setColor(color);
		gIn.fillOval((int)getCenter().x - getWidth() / 2, (int)getCenter().y - getHeight() / 2, getWidth(), getHeight());
	}
	@Override
	public void dispose()
	{
	}
}
