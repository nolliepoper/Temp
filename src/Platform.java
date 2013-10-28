/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Branden
 */
public class Platform extends Entity
{
    public String art;
    
    public Platform()
    {
    }
    public Platform(Vector center, int width, int height)
    {
        super(center, width, height);
    }
    @Override
    public void logic()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void paint(Graphics2D gIn)
    {
        gIn.setColor(Color.WHITE);
        gIn.fillRect(getCenter().x - getWidth() / 2,
                getCenter().y - getHeight() / 2, getWidth(), getHeight());
    }
    @Override
    public void dispose()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
