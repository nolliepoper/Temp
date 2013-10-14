
import java.awt.*;


public class Block extends Entity
{
    public Block(Color cIn, Point pIn, Point sIn)
    {
        super(cIn, pIn, sIn);
    }
    @Override
    public void logic()
    {
        
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(color);
        gIn.fillRect(point.x, point.y, size.x, size.y);
    }
    @Override
    public void dispose()
    {
        
    }
}
