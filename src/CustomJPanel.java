
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomJPanel extends JPanel implements MouseListener
{//Used to make labels interactive, used on the Main Menu and Options Menu
    String name; //To check which Label was clicked
    Frame frame; //To link the action back to the Frame
    // Constructor
    public CustomJPanel(GridLayout gl, String name, Frame frame)
    {
        super(gl);
        this.name = name;
        this.frame = frame;
        addMouseListener(this);
        setBackground(Color.gray);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println(name + " was clicked!");
        if(name.equals("New Game"))
        {
            System.out.println("New Game Begin");
            System.out.println("Main begin");

            frame.beginGame();
        }

        if(name.equals("Options"))
        {
            System.out.println("Options Begin");
            frame.swapCenter();
        }

        if(name.equals("Exit"))
        {
            System.out.println("Exit Begin");
            frame.dispose();
        }

        if(name.equals("Return to Main Menu"))
        {
            System.out.println("Return to Menu Begin");
            frame.swapCenter();
        }
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
        setBackground(Color.red);
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        setBackground(Color.gray);
    }
    
    public void destroyMouseListener(){
        removeMouseListener(this);
    }
}
