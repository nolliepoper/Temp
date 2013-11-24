import java.awt.*;

public class Vector
{
	double x, y;
	// Constructor
	public Vector()
	{
	}
	public Vector(double xIn, double yIn)
	{
		x = xIn;
		y = yIn;
	}
	public Vector(int xIn, int yIn)
	{
		x = xIn;
		y = yIn;
	}
	//Copy constructor
	public Vector(Vector v)
	{
		x = v.x;
		y = v.y;
	}
	public void setX(int xIn)
	{
		x = xIn;
	}
	public void setY(int yIn)
	{
		y = yIn;
	}
	public double mag()
	{
		return Math.sqrt(x * x + y * y);
	}
	public Vector sub(Vector vIn)
	{
		return new Vector(x - vIn.x, y - vIn.y);
	}
	public double dot(Vector vIn)
	{
		return x * vIn.x + y * vIn.y;
	}
	public double cross(Vector vIn)
	{
		return x * vIn.y - vIn.x * y;
	}
	
	
	public double angle()
	{
		return Math.atan2(y, x);
	}
	public double angle(Vector vIn)
	{
		return Math.atan2(cross(vIn), dot(vIn));
	}
	public boolean outside(Vector[] vIn)
	{
		double sum = 0;
		for(int i = 0; i < vIn.length; i++)
		{
			sum += sub(vIn[i]).angle(sub(vIn[(i + 1) % vIn.length]));
		}
		return Math.abs(sum) < 2 * Math.PI;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		Vector other = (Vector)obj;
		if(x != other.x || y != other.y)
		{
			return false;
		}
		return true;
	}
	public Point toPoint()
	{
		return new Point((int)x, (int)y);
	}
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
