

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.annotate.JsonSubTypes.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")  
@JsonSubTypes({  
    @Type(value = Platform.class, name = "Platform"),
	@Type(value = Enemy.class, name = "Enemy")
 })  
public abstract class Entity
{
    public Point dest;
    private Point size;
	
	private int width;	
	private int height;
	private Point start;
	
    public final ArrayList<Class> list; // For collisions, needs implementation.
    
    public Entity()
    {
        start = null;
        dest = null;
        size = null;
        list = null;
    }
	
	public Entity(Point startIn, int widthIn, int heightIn)
	{
		start = startIn;
		dest = new Point(start.x, start.y);
		width = widthIn;
		height = heightIn;
		
		size = new Point(start.x + width, start.y + height);
		
		list = null;
	}
	
	public void setStart(Point newStart)
	{
		start = newStart;
		
		size = new Point(start.x + width, start.y + height);
	}
	
	public Point getStart() {
		return start;
	}
	
	public void setWidth(int newW)
	{
		width = newW;
		size.x = start.x + width;
	}
	public void setHeight(int newW)
	{
		height = newW;
		size.y = start.y + height;
	}
	
	public Point getSize()
	{
		return size;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return height;
	}
    public abstract void logic();
    public abstract void paint(Graphics2D gIn);
    public abstract void dispose();
}
