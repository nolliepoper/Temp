// Credit to: http://www.java2s.com/Code/Java/Development-Class/AnexampleofloadingandplayingasoundusingaClip.htm
// For the sound code example we reused.

import java.io.File;
import javax.sound.sampled.*;

public class Sound
{
	private Clip clip;
	private String url;
	// Constructor
	public Sound(String urlIn)
	{
		url = urlIn;
	}
	public void start()
	{
		try
		{
			// specify the sound to play
			// (assuming the sound can be played by the audio system)

			File soundFile = new File(url);
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

			// load the sound into memory (a Clip)
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);

			// due to bug in Java Sound, explicitly exit the VM when
			// the sound has stopped.
			clip.addLineListener(new LineListener()
			{
				public void update(LineEvent event)
				{
					if(event.getType() == LineEvent.Type.STOP)
					{
						event.getLine().close();
						//System.exit(0);
					}
				}
			});

			// play the sound clip
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			//clip.start();
		}
		catch(Exception eIn)
		{
			eIn.printStackTrace();
		}
	}
	public void stop()
	{
		clip.stop();
	}
	public void toggle()
	{
		if(isPlaying())
		{
			stop();
		}
		else
		{
			start();
		}
	}
	public boolean isPlaying()
	{
		return clip.isRunning();
	}
}
