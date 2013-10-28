
public class Velocity
{
    public static final double GRAVITY = 1;
    public double dx, dy;
    
    public Velocity()
    {
        dx = dy = 0;
    }
    public void logic()
    {
        dx = Math.max(Math.min(dx+0.2d, 0), dx-0.2d); // Friction.
        dy += GRAVITY;
		dy /= 1.1;
		//if(dy > 12)
			//dy = 12;
    }
}
