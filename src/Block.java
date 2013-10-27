
import java.awt.*;
import java.awt.event.MouseEvent;


public class Block extends Entity
{
    public Block(Vector vIn, Vector sIn)
    {
        super(vIn, sIn);
    }
    @Override
    public void logic()
    {
        
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(Color.red);
        gIn.fillRect(getCenter().x - getWidth()/2, getCenter().y - getHeight()/2, getWidth(), getHeight());
    }
    @Override
    public void dispose()
    {
        
    }
}
