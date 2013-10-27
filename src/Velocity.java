
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
		dx = Math.min(Math.max(dx - 0.2, 0), dx + 0.2);
        dy += GRAVITY;
		dy /= 1.1;
    }
}
