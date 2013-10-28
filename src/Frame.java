
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame
{
    private final Content manager;
    
    public Frame()
    {
        super("MET!");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new Exit());

        manager = new Content(this);
        add(manager);

        new Collision(this, manager);

        addKeyListener(new Keyboard());
        addMouseListener(new Mouse());

        setVisible(true);
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
