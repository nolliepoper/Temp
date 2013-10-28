/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 *
 * @author Branden
 */
public class RoomData
{
    private final String IMG_PATH = "bin/images/";
    
    public RoomData()
    {
    }
    //The room name is needed for parsing lists of data
    private String roomName;
    public void setRoomName(String name)
    {
        roomName = name;
    }
    public String getRoomName()
    {
        return roomName;
    }
    //Data for setting the scenary of the room
    Image background;
    boolean isBackTiled;
    public void setBackground(String backName)
    {

        try
        {
            background = ImageIO.read(new File(IMG_PATH + backName));
        }
        catch(IOException ex)
        {
            System.out.println("Background not loaded\n");
        }
        //System.out.println(roomName);
        //background = backName;
    }
    ;
	public void setTiled(boolean isTiled)
    {
        isBackTiled = isTiled;
    }
    ;
	
	public Image getBackground()
    {
        return background;
    }
    ;
	public boolean getTiled()
    {
        return isBackTiled;
    }
    ;
	
	//Each of the possible exits in the room
	Walls exits;
    public void setExits(Walls exIn)
    {
        exits = exIn;
    }
    public Walls getExits()
    {
        return exits;
    }
    //The list of platforms in the room
    ArrayList<Platform> platforms;
    /*public void setPlatforms()
     {
     System.out.println("Hello?");
     ObjectMapper mapper = new ObjectMapper();
     TypeFactory typeFactory = TypeFactory.defaultInstance();
     try {
     platforms = mapper.readValue(new File(roomName), typeFactory.constructCollectionType(List.class, Platform.class));
     } catch (JsonParseException ex) {
     Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
     } catch (JsonMappingException ex) {
     Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
     Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
     }
		
     platforms.addAll(exits.buildExits(800, 600));
     System.out.println(platforms.get(2).getHeight());
     }*/
    public ArrayList<Platform> getPlatforms()
    {
        platforms.addAll(exits.buildExits(800, 600));
        return platforms;
    }
    //The list of enemies in the room
    ArrayList<Enemy> enemies;
    public void setEnemies()
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        try
        {
            platforms = mapper.readValue(new File(roomName), typeFactory.constructCollectionType(List.class, Platform.class));
        }
        catch(JsonParseException ex)
        {
            Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(JsonMappingException ex)
        {
            Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ex)
        {
            Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }
}