
import java.awt.Color;
import java.awt.Graphics2D;

/* Lurker #1
 * This guy just patrols between two areas at moderate speed and has two health.
 * This is the basic enemy and really has no brains at all. He's just good at 
 * dying. 
 */

public class LurkerNo1 extends Entity{
    private Manager manager;
    private Color color;
    
    //This should be where the sprite sheet is attached
    private String sprites;
    private String name;
    
    private int health; //How many hits before the poor guy dies
    private int patrolx1; //The left bound of his patrol
    private int patrolx2; //The right bound of his patrol
    // Constructor
    public LurkerNo1(Vector vIn, int wIn, int hIn, Manager mIn, int px1, int px2)
    {
        super(vIn, wIn, hIn);
        name = "LurkerNo1"; //Default Name, can be changed
        health = 2;
        manager = mIn;
        dx = 3;
        color = Color.WHITE;
        patrolx1 = px1;
        patrolx2 = px2;
        list.add("Platform");
    }
    void die()
    {
        System.out.println("LurkerNO1 Hit");
        health--;
        if(health <= 0){
            manager.remove(this);
            System.out.println("LurkerNo1 Dead");
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
        if(tmp != null && (tmp.getClass().getName()+"").equals("Platform") || getCenter().x == patrolx1 || getCenter().x == patrolx2)
            dx = -pre; //If a collision is found, or he has reached the end of his patrol in one direction.
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
