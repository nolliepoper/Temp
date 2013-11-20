
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * This represents the map the player will see in the pause menu.
 * Rooms only appear on this map one a player has entered it.
 * The map shows the walls of each room, so the player can know where they haven't been yet.
 * @author John Michael
 */
public class Map
{
	private Point currentRoom = new Point(0, 0);
	private static final int roomWidth = 24;
	private static final int roomHeight = 24;
	private BufferedImage image = new BufferedImage(roomWidth, roomHeight, BufferedImage.TYPE_4BYTE_ABGR);
	public static Map defaultMap;

	public Map(Room curRoom) {
		Update(0,0, curRoom);
	}
	
	public void Update(int shiftX, int shiftY, Room curRoom)
	{
		currentRoom.translate(shiftX, shiftY);
		resize();
		Graphics2D g2d = (Graphics2D)image.getGraphics();
		g2d.setColor(Color.BLUE);
		g2d.fillRect(currentRoom.x * roomWidth, currentRoom.y * roomHeight, roomWidth, roomHeight);
		if(curRoom != null)//Draw walls
		{
			g2d.setColor(Color.WHITE);
			if(curRoom.getNorth() == null)
				g2d.drawLine(currentRoom.x * roomWidth, currentRoom.y * roomHeight, 
						(currentRoom.x + 1) * roomWidth - 1, currentRoom.y * roomHeight);
			if(curRoom.getWest() == null)
				g2d.drawLine(currentRoom.x * roomWidth, currentRoom.y * roomHeight, 
						currentRoom.x * roomWidth, (currentRoom.y + 1) * roomHeight - 1);
			if(curRoom.getSouth() == null)
				g2d.drawLine(currentRoom.x * roomWidth, (currentRoom.y + 1) * roomHeight - 1, 
						(currentRoom.x + 1) * roomWidth - 1, (currentRoom.y + 1) * roomHeight - 1);
			if(curRoom.getEast() == null)
				g2d.drawLine((currentRoom.x + 1) * roomWidth - 1, currentRoom.y * roomHeight, 
						(currentRoom.x + 1) * roomWidth - 1, (currentRoom.y + 1) * roomHeight - 1);
		}
	}
	 
	public void draw(Graphics2D g, int x, int y, int width, int height)
	{
		//double ratio = image.getWidth()/image.getHeight();
		int xOffset = width/2 - image.getWidth()/2;
		int yOffset = height/2 - image.getHeight()/2;
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
		g.drawImage(image, null, x + xOffset, y + yOffset);
		
		int alpha = (int)Math.abs(System.currentTimeMillis()% 1000 - 500);
		g.setColor(new Color(255, 255, 255, (alpha * 255 ) / 1000));
		g.fillRect(x + xOffset + currentRoom.x * roomWidth, y + yOffset + currentRoom.y * roomHeight, roomWidth, roomHeight);
	}
	
	public void drawMinimap(Graphics2D g, int x, int y, int width, int height, int showRoomsX, int showRoomsY)
	{
		//int left = Math(roomWidth*(currentRoom.x - showRoomsX/2), 0);
		//BufferedImage minimap = new BufferedImage((2*showRoomsX + 1) * roomWidth, (2*showRoomsY + 1) * roomHeight, BufferedImage.TYPE_3BYTE_BGR);
		//BufferedImage tempImage = image.getSubimage(, , 
				//showRoomsX * roomWidth, showRoomsY * roomHeight);
	}
	
	private void resize()
	{
		int newWidth = image.getWidth(), newHeight = image.getHeight(), x = 0, y = 0;
		boolean needResize = false;
		if(currentRoom.x < 0)
		{
			newWidth += Math.abs(currentRoom.x * roomWidth);
			x = -currentRoom.x;
			currentRoom.x = 0;
			needResize = true;
		}
		else if((currentRoom.x + 1) * roomWidth > image.getWidth())
		{
			newWidth = (currentRoom.x + 1) * roomWidth;
			needResize = true;
		}
		if(currentRoom.y < 0)
		{
			newHeight += Math.abs(currentRoom.y * roomHeight);
			y = -currentRoom.y;
			currentRoom.y = 0;
			needResize = true;
		}
		else if((currentRoom.y + 1) * roomHeight > image.getHeight())
		{
			newHeight = (currentRoom.y + 1) * roomHeight;
			needResize = true;
		}
		
		if(needResize)
		{
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
			newImage.getGraphics().drawImage(image, x * roomWidth, y * roomHeight, null);
			image = newImage;
		}
	}
}
