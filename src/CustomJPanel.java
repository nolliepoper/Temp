import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomJPanel extends JPanel implements MouseListener{
    String name;
    JFrame container;
    
    public CustomJPanel(GridLayout gl, String name, JFrame container){
        super(gl);
        this.name = name;
        this.container = container;
        addMouseListener(this);
        setBackground(Color.gray);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(name + " was clicked!");
        if(name.equals("New Game")){
            System.out.println("New Game Begin");
            System.out.println("Main begin");
            
        
            Frame frame = new Frame();
        
            Thread logic = new Thread(new Logic(frame, frame.getManager()));
            Thread paint = new Thread(new Paint(frame, frame.getManager()));
        
            logic.start();
            paint.start();
        
            System.out.println("Main end");
            container.setVisible(false);
        }
        
        if(name.equals("Options")){
            System.out.println("Options Begin");
            container.dispose();
            OptionsMenu om = new OptionsMenu();
        }
        
        if(name.equals("Exit")){
            System.out.println("Exit Begin");
            container.dispose();
        }
        
        if(name.equals("Return to Main Menu")){
            System.out.println("Return to Menu Begin");
            container.dispose();
            //set the options
            MainMenu mm = new MainMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.gray);
    }  
}

