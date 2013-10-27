
import javax.swing.*;
import java.awt.*;

public class OptionsMenu {
    
    JFrame frame = new JFrame();
    
    JPanel allContent  = new JPanel(new BorderLayout());
    JPanel northPanel  = new JPanel(new GridLayout(1,1));
    JPanel centerPanel = new JPanel(new GridLayout(3,1));
    CustomJPanel southPanel = new CustomJPanel(new GridLayout(1,1), "Return to Main Menu", frame);
    
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    
    JLabel optionsMenu = new JLabel("Options Menu");
    JLabel returnL = new JLabel("Return to Main Menu", SwingConstants.CENTER);
    
    JCheckBox muteSound = new JCheckBox("Enable Global Sound", true);
    JCheckBox option2   = new JCheckBox("Option Number 2",     true);
    JCheckBox option3   = new JCheckBox("Option Number 3",     true);

    
    public OptionsMenu(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/3-frame.getSize().width/3, dim.height/3-frame.getSize().height/3);
        
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Game");
        
        optionsMenu.setFont(font);
        optionsMenu.setVisible(true);
        northPanel.setBackground(Color.red);
        northPanel.setSize(frame.getWidth(), 75);
        northPanel.add(optionsMenu, SwingConstants.CENTER);
        
        muteSound.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(true);
        centerPanel.add(muteSound);
        centerPanel.add(option2);
        centerPanel.add(option3);
        
        returnL.setVisible(true);
        southPanel.add(returnL, SwingConstants.CENTER);
        
        northPanel.setVisible(true);
        centerPanel.setVisible(true);
        southPanel.setVisible(true);
        allContent.add(northPanel, BorderLayout.NORTH);
        allContent.add(centerPanel, BorderLayout.CENTER);
        allContent.add(southPanel, BorderLayout.SOUTH);
        frame.add(allContent);
    }
    
}
