
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Branden
 * Created 10/27/13
 */
public class Room
{
    private String LVL_PATH = "bin/roomFiles/";
    private String IMG_PATH = "bin/images/";
    private Frame roomFrame;
    private RoomData roomInfo;
    private Vector spawn;
    // Constructor
    public Room(String name, Frame frame)
    {
        roomFrame = frame;
        loadRoom(name);
        //spawn = roomInfo.getDefaultSpawn();
    }
    public boolean loadRoom(String name)
    {
        //String prevRoom = roomInfo.getRoomName();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            roomInfo = mapper.readValue(new File(LVL_PATH + name), RoomData.class);
        }
        catch(JsonGenerationException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(JsonMappingException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        //(Arrays.toString(roomInfo.getExits().adjRooms));

        //if(roomInfo.getNorth() == prevRoom)
        //spawn = roomInfo.getNorthSpawn();

        return true;
    }
    public ArrayList<Platform> getPlatforms()
    {
        return roomInfo.getPlatforms();
    }
	
	public ArrayList<Enemy> getEnemies()
	{
		return roomInfo.getEnemies();
	}
	
    public String getNorth()
    {
        return roomInfo.getExits().getAdjRoom(0);
    }
    public String getEast()
    {
        return roomInfo.getExits().getAdjRoom(1);
    }
    public String getSouth()
    {
        return roomInfo.getExits().getAdjRoom(2);
    }
    public String getWest()
    {
        return roomInfo.getExits().getAdjRoom(3);
    }
	
	public Vector getNorthSpawn()
	{
		return roomInfo.getSpawns()[0];
	}
	
	public Vector getEastSpawn()
	{
		return roomInfo.getSpawns()[1];
	}
			
	public Vector getSouthSpawn()
	{
		return roomInfo.getSpawns()[2];
	}
	
	public Vector getWestSpawn()
	{
		return roomInfo.getSpawns()[3];
	}
	
	public void showSpawns()
	{
		Vector[] pts = roomInfo.getSpawns();
		for(int i = 0; i < 4; i++)
		{
			System.out.println("Spawn " + i + "(" + pts[i].x + ", "+ pts[i].y + ")");
		}
	}
    public void getData()
    {
        System.out.println(roomInfo.getBackground());
        System.out.println(roomInfo.isBackTiled);
        System.out.println(roomInfo.getExits().getAdjRoom(0));
    }
    public void paint(Graphics2D gIn)
    {

        gIn.drawImage(roomInfo.getBackground(), 0, 0, roomFrame.getWidth(), roomFrame.getHeight(), roomFrame);

        /*for(Platform p : roomInfo.getPlatforms())
         p.paint(gIn);*/
    }
}
