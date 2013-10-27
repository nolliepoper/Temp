
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Branden
 * Created 10/27/13
 */
public class Room {
	private String LVL_PATH = "bin/roomFiles/";
	private String IMG_PATH = "bin/images/";
	
	private Frame  roomFrame;
	private RoomData roomInfo;
	public Room(String name, Frame frame)
	{
		roomFrame = frame;
		loadRoom(name);
	}
	
	public boolean loadRoom(String name)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			roomInfo = mapper.readValue(new File(LVL_PATH + "start.json"), RoomData.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return false;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Platform> getPlatforms()
	{
		return roomInfo.getPlatforms();
	}
	
	public void paint(Graphics2D gIn) {
		
		gIn.drawImage(roomInfo.getBackground(), 0, 0, roomFrame.getWidth(),
					roomFrame.getHeight(), roomFrame);
		
		/*for(Platform p : roomInfo.getPlatforms())
			p.paint(gIn);*/
	}
}
