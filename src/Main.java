
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("New Game Begin");
		System.out.println("Main begin");
		long startTime;
		
		try {
			Frame frame = new Frame();
			startTime = System.currentTimeMillis();
			frame.gameComplete.acquire();
			//End the game after calculating how long the player took to 
			//finish it.
			frame.endGame((System.currentTimeMillis() - startTime)/1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		System.out.println("Main end");
	}
}
