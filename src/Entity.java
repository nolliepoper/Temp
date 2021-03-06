
import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes(
//{
//    @JsonSubTypes.Type(value = Platform.class, name = "Platform"),
//    @JsonSubTypes.Type(value = Enemy.class, name = "Enemy")
//})
public abstract class Entity
{
	private Vector center;
	private Vector dest;
	public double dx = 0;
	public double dy = 0;
	private int width;
	private int height;
	public final ArrayList<String> list;
	protected Sprite sprite;
	//Default constructor for the parser
	public Entity()
	{
		center = new Vector(0, 0);
		dest = null;
		width = 0;
		height = 0;
		list = null;
	}
	/**
	 * @deprecated As of 10/26, replaced by Entity(Vector vIn, int width, int
	 * height)
	 *
	 * @see #Entity(Vector vIn, int width, int height)
	 */
	public Entity(Vector vIn, Vector sIn)
	{
		center = vIn;
		dest = new Vector(vIn.x, vIn.y);
		//size = sIn;
		list = new ArrayList<>();
		width = (int)sIn.x - (int)vIn.x;
		height = (int)sIn.x - (int)vIn.x;
	}
	//Takes a vector (the center of the rectangle), width, and height
	public Entity(Vector vIn, int wIn, int hIn)
	{
		center = new Vector(0, 0);
		this.setCenter(vIn);
		dest = new Vector(vIn.x, vIn.y);
		list = new ArrayList<>();
		width = wIn;
		height = hIn;
	}
	//Setters for all independant variables
	final public void setCenter(Vector cIn)
	{
		center.x = cIn.x;
		center.y = cIn.y;
	}
	final public void setCenter(int xIn, int yIn)
	{
		center.x = xIn;
		center.y = yIn;
	}
	final public void setDest(Vector dIn)
	{
		dest = dIn;
	}
	final public void setWidth(int wIn)
	{
		width = (wIn % 2 == 0) ? wIn : wIn + 1;
	}
	final public void setHeight(int hIn)
	{
		height = (hIn % 2 == 0) ? hIn : hIn + 1;
	}
	//Getters for everything
	public Vector getCenter()
	{
		return center;
	}
	public Vector getDest()
	{
		return dest;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	@Override
	public String toString()
	{
		return getClass().getName();
	}
	//These are the important functions in the class
	public abstract void logic();
	public abstract void paint(Graphics2D gIn);
	public abstract void dispose();
}
