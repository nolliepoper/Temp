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
        
        Vector v = new Vector(2, 2);
        Vector[] arr = new Vector[]{new Vector(1, 1), new Vector(1, 3), new Vector(3, 1), new Vector(3, 3)};
        
        System.out.println(v.outside(arr));
        
        System.out.println("Main end");
    }
}
