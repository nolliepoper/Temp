
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Branden
 */
public class BreakableWall extends Enemy {

	int side;
	
	public void setSide(int s)
	{
		side = s;
	}
	
	public void setPosition(int pos)
	{
		switch (side)
		{
			//north
			case 0:
			{
				this.setCenter(pos, 10);
				this.setWidth(100);
				this.setHeight(20);
				break;
			}
			//East
			case 1:
			{
				this.setCenter(785, pos);
				this.setWidth(20);
				this.setHeight(100);
				break;
			}
			//south
			case 2:
			{
				this.setCenter(pos, 558);
				this.setWidth(100);
				this.setHeight(20);
				break;
			}
			//west
			case 3:
			{
				this.setCenter(10, pos);
				this.setWidth(20);
				this.setHeight(100);
				break;
			}
			
		}
	}
	
	@Override
	public void logic() {
	}

	@Override
	public void paint(Graphics2D gIn) {
		gIn.setColor(Color.WHITE);
        gIn.fillRect(getCenter().x - getWidth() / 2,
        getCenter().y - getHeight() / 2, getWidth(), getHeight());
	}

	@Override
	public void dispose() {	}
	
}
