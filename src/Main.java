import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Main begin");
        
        Frame frame = new Frame();
        
        Thread logic = new Thread(new Logic(frame, frame.getManager()));
        Thread paint = new Thread(new Paint(frame, frame.getManager()));
        
        logic.start();
        paint.start();
        
        System.out.println("Main end");
    }
}
