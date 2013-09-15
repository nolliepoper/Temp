import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Main begin");
        
        Frame met = new Frame();
        
        Thread logic = new Thread(new Logic(met, met.getManager()));
        Thread paint = new Thread(new Paint(met, met.getManager()));
        
        logic.start();
        paint.start();
        
        System.out.println("Main end");
    }
}

