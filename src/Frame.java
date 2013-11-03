
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Frame extends JFrame
{
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;
	
    //Where everything is drawn and handled after Main Menu Concludes
    private final Content manager;
    //Used to add/remove the main menu from the frame before and after user
    JPanel wholePanel = new JPanel(new BorderLayout());
    //Six Panels below are for the main menu or for centering the main menu
    JPanel northPanel = new JPanel(new GridLayout(1, 1));
    JPanel southPanel = new JPanel(new GridLayout(1, 1));
    JPanel westPanel = new JPanel(new GridLayout(1, 1));
    JPanel eastPanel = new JPanel(new GridLayout(1, 1));
    JPanel centerPanel = new JPanel(new GridLayout(3, 1));
    JPanel centerPanelO = new JPanel(new GridLayout(5, 1));
    //The image initiliazation information for the Game's Icon and Main Menu Image
    String iconPath = new String("bin\\images\\MainMenu\\icon.png");
    BufferedImage imgIcon;
    String titlePath = new String("bin\\images\\MainMenu\\Title.png");
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
        loadIconImage();
        setIconImage(imgIcon);
        loadTitleImage();
        ImagePanel titlePanel = new ImagePanel(imgTitle);
        titlePanel.setVisible(true);

        manager = new Content(this);

        wholePanel.setVisible(true);
        
        //Sets up the Audio!
        String musicDest = "bin\\sounds\\mainMenu\\DST-Surreal.wav";
        //We can implement this later

        //The size of the center panel, where the main menu options will be
        Dimension expectedDimension = new Dimension(300, 200);

        //The dimension for the panels above and below, and then left and right of main menu
        Dimension ud = new Dimension(800, 150);
        Dimension lr = new Dimension(200, 100);

        //Init's the panels with the proper sizes
        setUpFillerPanel(eastPanel, lr);
        setUpFillerPanel(westPanel, lr);
        setUpFillerPanel(northPanel, ud);
        setUpFillerPanel(southPanel, ud);
        setUpFillerPanel(centerPanel, expectedDimension);
        setUpCenterPanel(centerPanel);
        setUpFillerPanel(centerPanelO, expectedDimension);
        setUpCenterPanelO(centerPanelO);

        //Special Title Panel is addeded to host the main menu image
        northPanel.add(titlePanel, SwingConstants.CENTER);

        //Add the Main Menu panel initially, without the options
        wholePanel.add(eastPanel, BorderLayout.WEST);
        wholePanel.add(westPanel, BorderLayout.EAST);
        wholePanel.add(northPanel, BorderLayout.NORTH);
        wholePanel.add(southPanel, BorderLayout.SOUTH);
        wholePanel.add(centerPanel, BorderLayout.CENTER);
        add(wholePanel);

        setVisible(true);
    }
    public void beginGame()
    {
        //Get rid of the main menu, and add the game manager.
        wholePanel.removeAll();
        remove(wholePanel);
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
    private void setUpFillerPanel(JPanel jIn, Dimension d)
    { //Saves space with Frame constructor, looks cleaner
        jIn.setPreferredSize(d);
        jIn.setMaximumSize(d);
        jIn.setMinimumSize(d);
        jIn.setBackground(Color.gray);
        jIn.setVisible(true);
    }
    private void setUpCenterPanel(JPanel jIn)
    {
        //The buttons that will become New Game, Options, and Exit
        CustomJPanel newGame = new CustomJPanel(new GridLayout(1, 1), "New Game", this);
        CustomJPanel options = new CustomJPanel(new GridLayout(1, 1), "Options", this);
        CustomJPanel exit = new CustomJPanel(new GridLayout(1, 1), "Exit", this);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        //The corresponding Labels to the JFrame's
        JLabel newGameL = new JLabel("New Game", SwingConstants.CENTER);
        JLabel optionsL = new JLabel("Options", SwingConstants.CENTER);
        JLabel exitL = new JLabel("Exit", SwingConstants.CENTER);

        //Set the font of the labels and add them to their CustomeJPanel's
        newGameL.setFont(font);
        newGame.add(newGameL);
        optionsL.setFont(font);
        options.add(optionsL);
        exitL.setFont(font);
        exit.add(exitL);

        //Add the custom JPanels to the Frame
        jIn.add(newGame);
        jIn.add(options);
        jIn.add(exit);
        jIn.setBackground(Color.red);
    }
    private void setUpCenterPanelO(JPanel jIn)
    { //Set up the Options Menu for Usage
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        JLabel optionsL = new JLabel("Options Menu");
        JLabel returnL = new JLabel("Return to Main Menu");
        CustomJPanel south = new CustomJPanel(new GridLayout(1, 1), "Return to Main Menu", this);
        
        //These are causing a problem with the player not being able to move
        JCheckBox muteSound = new JCheckBox("Afflict Main Character With Paralysis", false);
        JCheckBox option2 = new JCheckBox("Option Number 2", false);
        JCheckBox option3 = new JCheckBox("Option Number 3", false);

        
        optionsL.setFont(font);
        jIn.setBackground(Color.red);
        optionsL.setVisible(true);
        returnL.setVisible(true);
        south.setVisible(true);
        muteSound.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(true);

        jIn.add(optionsL, SwingConstants.CENTER);
        jIn.add(muteSound);
        jIn.add(option2);
        jIn.add(option3);

        south.add(returnL, SwingConstants.CENTER);
        jIn.add(south);
    }
    
    public void swapCenter()
    {//If the Main Menu is displayed, swap to the Options Menu
        if(centerPanel.isVisible())
        {
            centerPanel.setVisible(false);
            wholePanel.remove(centerPanel);
            centerPanelO.setVisible(true);
            wholePanel.add(centerPanelO);
        }
        else
        {//If the Options is displayed, swap to the Main Menu
            centerPanelO.setVisible(false);
            wholePanel.remove(centerPanelO);
            centerPanel.setVisible(true);
            wholePanel.add(centerPanel);
        }
    }
    
    private void loadIconImage()
    {//Try and Load the Image from the bin images
        try
        {
            System.out.println(iconPath);
            imgIcon = ImageIO.read(new File(iconPath));
        }
        catch(IOException e)
        {
            System.out.println("Unable to load Icon Image!");
        }
    }
    private void loadTitleImage()
    {//Try to Load the Image from the bin Images
        try
        {
            System.out.println(titlePath);
            imgTitle = ImageIO.read(new File(titlePath));
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
