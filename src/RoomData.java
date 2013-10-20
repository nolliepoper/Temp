/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author Branden
 */
public class RoomData {
	
	//Data for setting the scenary of the room
	String background;
	boolean isBackTiled;
	
	public String getBackground() { return background; };
	public boolean getTiled() { return isBackTiled; };
	
	public void setBackground(String backName) { background = backName; };
	public void setTiled(boolean isTiled) { isBackTiled = isTiled; };
	
	//The list of platforms in the room
	ArrayList<Platform> platforms;
	
	public void setPlatforms()
	{
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = TypeFactory.defaultInstance();
		try {
			platforms = mapper.readValue(new File("start.json"), typeFactory.constructCollectionType(List.class, Platform.class));
		} catch (JsonParseException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JsonMappingException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}

	//The list of enemies in the room
	ArrayList<Enemy> enemies;
	
	public void setEnemies()
	{
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = TypeFactory.defaultInstance();
		try {
			platforms = mapper.readValue(new File("start.json"), typeFactory.constructCollectionType(List.class, Platform.class));
		} catch (JsonParseException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JsonMappingException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RoomData.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	
    /*public enum Gender { MALE, FEMALE };

    public static class Name {
      private String first, last;

      public String getFirst() { return first; }
      public String getLast() { return last; }

      public void setFirst(String s) { first = s; }
      public void setLast(String s) { last = s; }
    }

    private Gender gender;
    private Name name;
    private boolean isVerified;
    private byte[] userImage;

    public Name getName() { return name; }
    public boolean isVerified() { return isVerified; }
    public Gender getGender() { return gender; }
    public byte[] getUserImage() { return userImage; }

    public void setName(Name n) { name = n; }
    public void setVerified(boolean b) { isVerified = b; }
    public void setGender(Gender g) { gender = g; }
    public void setUserImage(byte[] b) { userImage = b; }*/
}
