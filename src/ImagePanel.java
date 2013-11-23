
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel
{
	private BufferedImage image;
	// Constructor
	public ImagePanel(BufferedImage image)
	{ //Stores the image attached to the Panel (Only allows one image per panel)
		this.image = image;
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
