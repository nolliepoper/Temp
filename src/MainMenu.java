import java.awt.*;
import javax.swing.*;

public class MainMenu {
    
    JFrame frame = new JFrame();
    
    JPanel totalPanel = new JPanel(new BorderLayout());
    JPanel northPanel = new JPanel(new GridLayout(1, 1));
    JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 10));
    
    CustomJPanel newGame = new CustomJPanel(new GridLayout(1,1), "New Game", frame);
    CustomJPanel options = new CustomJPanel(new GridLayout(1,1), "Options", frame);
    CustomJPanel exit    = new CustomJPanel(new GridLayout(1,1), "Exit", frame);
    
    public MainMenu(){
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/3-frame.getSize().width/3, dim.height/3-frame.getSize().height/3);
        
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Game");
        
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        JLabel title = new JLabel("THE GAME", SwingConstants.CENTER);
        title.setFont(f);
        title.setVisible(true);
        northPanel.setSize(frame.getWidth(), 75);
        northPanel.setBackground(Color.red);
        northPanel.add(title, BorderLayout.NORTH);
        
        JLabel newGameL = new JLabel("New Game", SwingConstants.CENTER);
        JLabel optionsL = new JLabel("Options",  SwingConstants.CENTER);
        JLabel exitL    = new JLabel("Exit",     SwingConstants.CENTER);
        newGame.add(newGameL);
        options.add(optionsL);
        exit.add(exitL);
        
        centerPanel.add(newGame);
        centerPanel.add(options);
        centerPanel.add(exit);
        
        totalPanel.add(northPanel, BorderLayout.NORTH);
        totalPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(totalPanel);
    }
    
    public void fadeOut(){
        
    }
}
