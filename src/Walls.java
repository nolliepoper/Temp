
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Branden
 */
public class Walls
{
    private static final int DOOR_WIDTH = 100;
    boolean nExist, eExist, sExist, wExist;
    int nPos, ePos, sPos, wPos;
    
    public void setNorthPos(int nIn)
    {
        nPos = nIn;
    }
    public void setEastPos(int eIn)
    {
        ePos = eIn;
    }
    public void setSouthPos(int sIn)
    {
        sPos = sIn;
    }
    public void setWestPos(int wIn)
    {
        wPos = wIn;
    }
    public boolean getWExist()
    {
        return wExist;
    }
    private int avg(int n1, int n2)
    {
        return (n1 + n2) / 2;
    }
    //Builds the walls around the exits
    public ArrayList<Platform> buildExits(int windowW, int windowH)
    {
        ArrayList<Platform> walls = new ArrayList<Platform>();

        //walls.add(new Platform(
        //		new Vector(10, avg(300, windowW)),
        //		20, windowW - 300));

        //Northern door
        if(nPos > 0)
        {
            walls.add(new Platform(new Vector(avg(nPos, 0) - DOOR_WIDTH / 2, 10),
                    nPos, 20));
            walls.add(new Platform(
                    new Vector(avg(nPos + DOOR_WIDTH / 2, windowW), 10),
                    windowW - nPos - DOOR_WIDTH / 2, 20));
        }
        else
        {
            walls.add(new Platform(
                    new Vector(windowW / 2, 10),
                    windowW, 20));
        }

        //Eastern door
        if(ePos > 0)
        {
            walls.add(new Platform(new Vector(windowW - 15, avg(ePos, 0) - DOOR_WIDTH / 2),
                    20, ePos));
            walls.add(new Platform(
                    new Vector(windowW - 15, avg(ePos + DOOR_WIDTH / 2, windowH)),
                    20, windowH - ePos - DOOR_WIDTH / 2));
        }
        else
        {
            walls.add(new Platform(
                    new Vector(windowW - 15, windowH / 2),
                    20, windowH));
        }

        //Southern door		
        if(sPos > 0)
        {
            walls.add(new Platform(new Vector(avg(sPos, 0) - DOOR_WIDTH / 2, windowH - 42),
                    sPos, 20));
            walls.add(new Platform(
                    new Vector(avg(sPos + DOOR_WIDTH / 2, windowW), windowH - 42),
                    windowW - sPos - DOOR_WIDTH / 2, 20));
        }
        else
        {
            walls.add(new Platform(
                    new Vector(windowW / 2, windowH - 42),
                    windowW, 20));
        }

        //Western door
        if(wPos > 0)
        {
            walls.add(new Platform(new Vector(10, avg(wPos, 0) - DOOR_WIDTH / 2),
                    20, wPos));
            walls.add(new Platform(
                    new Vector(10, avg(wPos + DOOR_WIDTH / 2, windowH)),
                    20, windowH - wPos - DOOR_WIDTH / 2));
        }
        else
        {
            walls.add(new Platform(
                    new Vector(10, windowH / 2),
                    20, windowH));
        }

        return walls;
    }
}
