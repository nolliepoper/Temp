import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainMenu extends JPanel{
    
    Frame parent;
    private boolean currentCenterPanel = false; //False is main menu, else options
    JPanel centerPanel;
    
    ArrayList<JLabel> mainButtons = new ArrayList<JLabel>();
    ArrayList<JLabel> optionsButtons = new ArrayList<JLabel>();
    Boolean[] options = new Boolean[3];
    BufferedImage imgTitle;
    
    public MainMenu(Frame fIn, BufferedImage iIn){
        parent = fIn;
        imgTitle = iIn;
        Dimension panelSize = new Dimension(800, 600);
        setPreferredSize(panelSize);
        setMaximumSize(panelSize);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBackground(Color.white);
        initBasicMenu();
        setVisible(true);
        
        for(int i = 0; i < 3; i++)
            options[i] = false;
        
        initMainMenu();
        initOptionsMenu();
    }
    
    private void initBasicMenu(){
        Dimension td = new Dimension(800, 150);
        Dimension cd = new Dimension(800, 450);
        ImagePanel ip = new ImagePanel(imgTitle);
        ip.setVisible(true);
        
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBackground(Color.gray);
        titlePanel.setPreferredSize(td);
        titlePanel.setMaximumSize(td);
        titlePanel.add(ip);
        titlePanel.setVisible(true);
        
        centerPanel = new JPanel(new GridLayout(1,1));
        centerPanel.setBackground(Color.red);
        centerPanel.setPreferredSize(cd);
        centerPanel.setMaximumSize(cd);
        centerPanel.setVisible(true);
        
        add(titlePanel);
        add(centerPanel);
    }
    
    private void initMainMenu(){
        JPanel centerMain = new JPanel(new GridLayout(3,1));
        final Font fontP = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        final Font fontB = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        
        JLabel temp = new JLabel("New Game");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.black);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                parent.beginGame();
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setFont(fontB);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setFont(fontP);
            }
        });
        mainButtons.add(temp);
        
        temp = new JLabel("Options");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.black);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                swapCenterPanel();
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setFont(fontB);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setFont(fontP);
            }
        });
        mainButtons.add(temp);
        
        temp = new JLabel("Exit");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.black);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setFont(fontB);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setFont(fontP);
            }
        });
        mainButtons.add(temp);
        
        centerMain.add(mainButtons.get(0));
        centerMain.add(mainButtons.get(1));
        centerMain.add(mainButtons.get(2));
        
        centerMain.setVisible(true);
        centerPanel.add(centerMain);
    }
    
    private void initOptionsMenu(){
        JPanel centerOptions = new JPanel(new GridLayout(5,1));
        centerOptions.setBackground(Color.gray);
        final Font fontP = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        final Font fontOptions = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        JLabel optionsTitle = new JLabel("Options Menu");
        optionsTitle.setFont(fontOptions);
        optionsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        optionsTitle.setForeground(Color.black);
        optionsButtons.add(optionsTitle);
        
        JLabel temp = new JLabel("Option 1");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.red);
        temp.setBackground(Color.gray);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                options[0] = !options[0];
                if(options[0])
                    setForeground(Color.green);
                else
                    setForeground(Color.red);
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setBackground(Color.darkGray);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setBackground(Color.gray);
            }
        });
        optionsButtons.add(temp);
        
        temp = new JLabel("Option 2");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.red);
        temp.setBackground(Color.gray);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                options[1] = !options[1];
                if(options[1])
                    setForeground(Color.green);
                else
                    setForeground(Color.red);
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setBackground(Color.darkGray);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setBackground(Color.gray);
            }
        });
        optionsButtons.add(temp);
        
        temp = new JLabel("Option 3");
        temp.setFont(fontP);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.red);
        temp.setBackground(Color.gray);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                options[2] = !options[2];
                if(options[2])
                    setForeground(Color.green);
                else
                    setForeground(Color.red);
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setBackground(Color.darkGray);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setBackground(Color.gray);
            }
        });
        optionsButtons.add(temp);
        
        temp = new JLabel("Return to Main Menu");
        temp.setFont(fontOptions);
        temp.setHorizontalAlignment(SwingConstants.CENTER);
        temp.setForeground(Color.black);
        temp.setBackground(Color.darkGray);
        temp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                swapCenterPanel();
            }
            @Override
            public void mouseEntered(MouseEvent arg0){
                setBackground(Color.red);
            }
            @Override
            public void mouseExited(MouseEvent arg0){
                setBackground(Color.darkGray);
            }
        });
        optionsButtons.add(temp);

    }
    
    private void swapCenterPanel(){
        if(currentCenterPanel){
            System.out.println("Swap Options to Main");
            centerPanel.setVisible(false);
            centerPanel.removeAll();
            loadMainCenter();
            currentCenterPanel = !currentCenterPanel;
        }else{
            System.out.println("Swap Main to Options");
            centerPanel.setVisible(false);
            centerPanel.removeAll();
            loadOptionsCenter();
            currentCenterPanel = !currentCenterPanel;
        }
    }
    
    private void loadMainCenter(){
        JPanel centerMain = new JPanel(new GridLayout(3,1));
        
        for(int i = 0; i < mainButtons.size(); i++)
            centerMain.add(mainButtons.get(i));
       
        centerMain.setVisible(true);
        centerPanel.add(centerMain);
        centerPanel.setVisible(true);
    }
    
    private void loadOptionsCenter(){
        JPanel centerOptions = new JPanel(new GridLayout(5,1));
        
        for(int i = 0; i < optionsButtons.size(); i++)
            centerOptions.add(optionsButtons.get(i));
        
        centerOptions.setVisible(true);
        centerPanel.add(centerOptions);
        centerPanel.setVisible(true);
    }
    
}
