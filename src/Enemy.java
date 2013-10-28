/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics2D;

/**
 *
 * @author Branden
 */
public class Enemy extends Entity
{
    //This should be where the sprite sheet is attached
    private String sprites;
    private String name;
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void dispose()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
