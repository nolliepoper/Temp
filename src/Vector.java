import java.math.*;

public class Vector
{
    int x, y;
    
    public Vector(int xIn, int yIn)
    {
        x = xIn;
        y = yIn;
    }
    public Vector sub(Vector vIn)
    {
        return new Vector(x - vIn.x, y - vIn.y);
    }
    public int dot(Vector vIn)
    {
        return x*vIn.x + y*vIn.y;
    }
    public int cross(Vector vIn)
    {
        return x*vIn.y - vIn.x*y;
    }
    public double angle(Vector vIn)
    {
        return Math.atan2(cross(vIn), dot(vIn));
    }
    public boolean outside(Vector[] vIn)
    {
        double sum = 0;
        for(int i = 0; i < vIn.length; i++)
            sum += sub(vIn[i]).angle(sub(vIn[(i + 1)%vIn.length]));
        return Math.abs(sum) < 2*Math.PI;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
            return false;
        Vector other = (Vector)obj;
        if(x != other.x || y != other.y)
            return false;
        return true;
    }
}
