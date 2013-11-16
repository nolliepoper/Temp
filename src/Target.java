
import java.awt.Color;
import java.awt.Graphics2D;


public class Target extends Enemy  
{
    private Manager manager;
    private Color color;
	
    // Constructor
	public Target() { 
		dx = 3.0;
        color = Color.BLUE;
        list.add("Platform");
	}
	
    public Target(Vector vIn, int wIn, int hIn)
    {
        super(vIn, wIn, hIn);
        dx = 3.0;
        color = Color.RED;
        list.add("Platform");
    }
    void die()
    {
        System.out.println("DIE");
        kill();
    }
    @Override
    public void logic()
    {
		if(isAlive())
		{
			//System.out.println("CALLING LOGIC IN TARGET\n");
			getDest().x = getCenter().x + (int)dx;
			getDest().y = getCenter().y;
			double pre = dx;
			Entity tmp = Collision.moveX(this);
			Collision.moveY(this);
			if(tmp != null && (tmp.getClass().getName()+"").equals("Platform"))
				dx = -pre;
		}
    }
    @Override
    public void paint(Graphics2D gIn)
    {
		if(isAlive())
		{
			
			gIn.setColor(color);
			gIn.fillOval(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2, getWidth(), getHeight());
		}
    }
    @Override
    public void dispose()
    {
    }
}
