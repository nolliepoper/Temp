
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
        dx /= 1.1; // Friction.
        dy += GRAVITY;
        dy /= 1.1;
    }
}
