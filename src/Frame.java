import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame
{
    private final Content manager;
    private final Mouse mouse;
    private final KeyBoard keyboard;
    //private final JPanel panel;
    
    public Frame()
    {
        super("MET!");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new Exit());
        
        /*
        panel = new JPanel();
        panel.setSize(200, 150);
        panel.setLocation(100, 75);
        panel.setBackground(Color.BLUE);
        add(panel);
        */
        
        manager = new Content(this);
        add(manager);
            
        mouse = new Mouse();
        addMouseListener(mouse);
        
        keyboard = new KeyBoard(manager);
        addKeyListener(keyboard);
        
        setVisible(true);
    }
    public Content getManager()
    {
        return manager;
    }
    public KeyBoard getKeyboard()
    {
        return keyboard;
    }
    public Mouse getMouse()
    {
        return mouse;
    }
    private class Exit extends WindowAdapter
    {
        @Override
        public void windowClosed(WindowEvent we)
        {
            // Do exiting logic.
            System.out.println("Exiting.");
            System.exit(0); // Exit successfully.
        }
    }
}
