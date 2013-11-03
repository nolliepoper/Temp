
import java.awt.Color;
import java.awt.Graphics2D;


public class Target extends Entity
{
    private Manager manager;
    private Color color;
    // Constructor
    public Target(Vector vIn, int wIn, int hIn, Manager mIn)
    {
        super(vIn, wIn, hIn);
        manager = mIn;
        dx = -4.0;
        color = Color.RED;
        list.add("Platform");
    }
    void die()
    {
        System.out.println("DIE");
        manager.remove(this);
    }
    @Override
    public void logic()
    {
        getDest().x = getCenter().x + (int)dx;
        getDest().y = getCenter().y;
        double pre = dx;
        Entity tmp = Collision.moveX(this);
        Collision.moveY(this);
        if(tmp != null && (tmp.getClass().getName()+"").equals("Platform"))
            dx = -pre;
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
        //setWidth(0);
        //setHeight(0);
    }
}
