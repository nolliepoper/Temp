
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Frame extends JFrame
{
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;
	public Semaphore gameComplete = new Semaphore(0);
	//Where everything is drawn and handled after Main Menu Concludes
	private Content manager;
	private static MainMenu mainMenu;
	//The image initiliazation information for the Game's Icon and Main Menu Image
	BufferedImage imgIcon;
	BufferedImage imgTitle;
	//To play music loop
	// Constructor
	public Frame()
	{
		super("Tempovania");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		addWindowListener(new Frame.Exit());

		//Load and set the main menu image and the game's icon
		loadIconImage("bin\\images\\MainMenu\\icon.png");
		setIconImage(imgIcon);
		loadTitleImage("bin\\images\\MainMenu\\Title.png");

		mainMenu = new MainMenu(this, imgTitle);
		add(mainMenu);

		manager = new Content(this);

		//Sets up the Audio!
		String musicDest = "bin\\sounds\\mainMenu\\DST-Surreal.wav";
		//We can implement this later

		setVisible(true);
	}
	
	public void restart()
	{
		mainMenu = new MainMenu(this, imgTitle);
		add(mainMenu);

		manager = new Content(this);

		//Sets up the Audio!
		String musicDest = "bin\\sounds\\mainMenu\\DST-Surreal.wav";
		//We can implement this later
	}
	
	public void beginGame()
	{
		remove(mainMenu);
		add(manager);

		//Create and begin the Logic and Paint Threads
		Thread logic = new Thread(new Logic(this, manager));
		Thread paint = new Thread(new Paint(this, manager));

		logic.start();
		paint.start();

		//Add collision detection to the frame and content manager
		new Collision(this, manager);
		
		//Add the Listeners for Keyboard and Mouse Input
		addKeyListener(new Keyboard());
		Mouse m = new Mouse();
		addMouseListener(m);
		addMouseMotionListener(m);
		setVisible(true);
	}
	
	public void endGame(long playtime)
	{
		manager.remove(manager);		
        //below is used to test the credits menu
        remove(manager);
        add(new GameCompletion(this, (int)playtime));
		setVisible(true);
	}
	
	private void loadIconImage(String path)
	{//Try and Load the Image from the bin images
		try
		{
			imgIcon = ImageIO.read(new File(path));
		}
		catch(IOException e)
		{
			System.out.println("Unable to load Icon Image!");
		}
	}
	private void loadTitleImage(String path)
	{//Try to Load the Image from the bin Images
		try
		{
			System.out.println(path);
			imgTitle = ImageIO.read(new File(path));
		}
		catch(IOException e)
		{
			System.out.println("Unable to load Title Image!");
		}
	}
	public Content getManager()
	{
		return manager;
	}
	// Private inner class because no other classes should determine termination.

	private class Exit extends WindowAdapter
	{
		@Override
		public void windowClosed(WindowEvent eIn)
		{
			// Do exiting logic.
			System.out.println("Exiting.");
			System.exit(0); // Exit successfully.
		}
	}
}
