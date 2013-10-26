
import java.awt.*;
import java.awt.event.MouseEvent;


public class Block extends Entity
{
    public Block(Color cIn, Vector vIn, Vector sIn)
    {
        super(cIn, vIn, sIn);
    }
    @Override
    public void logic()
    {
        
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(color);
        gIn.fillRect(point.x - size.x/2, point.y - size.y/2, size.x, size.y);
    }
    @Override
    public void dispose()
    {
        
    }
}
