
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
    private final Content manager;
    JPanel wholePanel = new JPanel(new BorderLayout());
    JPanel northPanel = new JPanel(new GridLayout(1, 1));
    JPanel southPanel = new JPanel(new GridLayout(1, 1));
    JPanel westPanel = new JPanel(new GridLayout(1, 1));
    JPanel eastPanel = new JPanel(new GridLayout(1, 1));
    JPanel centerPanel = new JPanel(new GridLayout(3, 1));
    JPanel centerPanelO = new JPanel(new GridLayout(5, 1));
    String iconPath = new String("bin\\images\\MainMenu\\icon.png");
    BufferedImage imgIcon;
    String titlePath = new String("bin\\images\\MainMenu\\Title.png");
    BufferedImage imgTitle;
    
    public Frame()
    {
        super("Tempovania");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new Frame.Exit());

        loadIconImage();
        setIconImage(imgIcon);
        loadTitleImage();
        ImagePanel titlePanel = new ImagePanel(imgTitle);
        titlePanel.setVisible(true);

        manager = new Content(this);

        wholePanel.setVisible(true);

        Dimension expectedDimension = new Dimension(300, 200);

        Dimension ud = new Dimension(800, 150);
        Dimension lr = new Dimension(200, 100);

        setUpFillerPanel(eastPanel, lr);
        setUpFillerPanel(westPanel, lr);
        setUpFillerPanel(northPanel, ud);
        setUpFillerPanel(southPanel, ud);
        setUpFillerPanel(centerPanel, expectedDimension);
        setUpCenterPanel(centerPanel);
        setUpFillerPanel(centerPanelO, expectedDimension);
        setUpCenterPanelO(centerPanelO);

        northPanel.add(titlePanel, SwingConstants.CENTER);

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
        //setAllVisible();
        remove(wholePanel);
        add(manager);

        Thread logic = new Thread(new Logic(this, manager));
        Thread paint = new Thread(new Paint(this, manager));

        logic.start();
        paint.start();

        new Collision(this, manager);

        addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        addMouseListener(m);
        addMouseMotionListener(m);
        setVisible(true);
    }
    private void setUpFillerPanel(JPanel jIn, Dimension d)
    { //Saves space Frame, looks cleaner
        jIn.setPreferredSize(d);
        jIn.setMaximumSize(d);
        jIn.setMinimumSize(d);
        jIn.setBackground(Color.gray);
        jIn.setVisible(true);
    }
    private void setUpCenterPanel(JPanel jIn)
    {
        CustomJPanel newGame = new CustomJPanel(new GridLayout(1, 1), "New Game", this);
        CustomJPanel options = new CustomJPanel(new GridLayout(1, 1), "Options", this);
        CustomJPanel exit = new CustomJPanel(new GridLayout(1, 1), "Exit", this);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        JLabel newGameL = new JLabel("New Game", SwingConstants.CENTER);
        JLabel optionsL = new JLabel("Options", SwingConstants.CENTER);
        JLabel exitL = new JLabel("Exit", SwingConstants.CENTER);

        newGameL.setFont(font);
        newGame.add(newGameL);
        optionsL.setFont(font);
        options.add(optionsL);
        exitL.setFont(font);
        exit.add(exitL);

        jIn.add(newGame);
        jIn.add(options);
        jIn.add(exit);
        jIn.setBackground(Color.red);
    }
    private void setUpCenterPanelO(JPanel jIn)
    {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        JLabel optionsL = new JLabel("Options Menu");
        JLabel returnL = new JLabel("Return to Main Menu");
        CustomJPanel south = new CustomJPanel(new GridLayout(1, 1), "Return to Main Menu", this);
        JCheckBox muteSound = new JCheckBox("Enable Global Sound", true);
        JCheckBox option2 = new JCheckBox("Option Number 2", true);
        JCheckBox option3 = new JCheckBox("Option Number 3", true);

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
    {
        if(centerPanel.isVisible())
        {
            centerPanel.setVisible(false);
            wholePanel.remove(centerPanel);
            centerPanelO.setVisible(true);
            wholePanel.add(centerPanelO);
        }
        else
        {
            centerPanelO.setVisible(false);
            wholePanel.remove(centerPanelO);
            centerPanel.setVisible(true);
            wholePanel.add(centerPanel);
        }
    }
    private void setAllVisible()
    {
        remove(centerPanel);
        remove(northPanel);
        remove(southPanel);
        remove(eastPanel);
        remove(westPanel);
        remove(wholePanel);
    }
    private void loadIconImage()
    {
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
    {
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
