
import java.awt.Color;
import java.awt.Graphics2D;
/* Lurker #2
 * This is a tank type of lurker who has 5 health and moves towards the player.
 * He is slower than most enemies but gets the job done, think of a tank from
 * L4D. 
 */
public class LurkerNo2 extends Entity{
    private Manager manager;
    private Color color;
    
    //This should be where the sprite sheet is attached
    private String sprites;
    private String name;
    
    private int health; //How many hits before the poor guy dies
    private static Player player; //To get the players location
    // Constructor
    public LurkerNo2(Vector vIn, int wIn, int hIn, Manager mIn, Player pIn)
    {
        super(vIn, wIn, hIn);
        name = "LurkerNo3"; //Default Name, can be changed
        health = 5;
        manager = mIn;
        dx = 1.5;
        color = Color.BLUE;
        player = pIn;
        list.add("Platform");
    }
    void die()
    {
        System.out.println("LurkerNO2 Hit");
        health--;
        if(health <= 0){
            manager.remove(this);
            System.out.println("LurkerNo2 Dead");
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
        if(tmp != null && (tmp.getClass().getName()+"").equals("Platform"))
            dx = -pre;
        //What I put below might not work well with the collision, it's all black magic to me!!
        if(player.getCenter().x - getCenter().x > 0) //Is the player to the right of the enemy?
            dx = 1.5; //Yes, go to the right and get them!
        else //No, they're to the left or above :(
            dx = -1.5; //Go to the left and get them, champ!
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

