package com.optimism.tools;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
	
	public static Clip bomb;
	public static AudioInputStream death;
	public static AudioInputStream playerShoot;
	//public static AudioInputStream troll;
	
	public Sound(){
			try{
				AudioInputStream bomb1 = AudioSystem.getAudioInputStream(new File("res/bomb.wav"));
				AudioFormat format = new AudioFormat(24000, 4, 2, true,false);
	
			    bomb = AudioSystem.getClip();;
			    bomb.open(AudioSystem.getAudioInputStream(format,bomb1));
			  //  bomb.start();
			    LineListener listener = new LineListener() {
			        public void update(LineEvent event) {
							if (event.getType() == Type.STOP) {
								event.getLine().close();
							}
			        }
			    };
			bomb.addLineListener(listener );
			} catch (UnsupportedAudioFileException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	public static void playBomb(){
		bomb.setFramePosition(0);
		bomb.start();
	}
	private static void playAudio(AudioInputStream j) throws Exception {
		AudioFormat format = j.getFormat();
		Tool.print(""+format);
		Tool.print(""+format.getEncoding());
	    //if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	    //  format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
	    //      .getSampleRate(), format.getSampleSizeInBits() * 2, format
	    //      .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
	    //      true); // big endian
	    //  j = AudioSystem.getAudioInputStream(format, j);
	    //}
	    Tool.print(""+j);

	    DataLine.Info info = new DataLine.Info(Clip.class, j.getFormat(),
	        ((int) j.getFrameLength() * format.getFrameSize()));
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    Tool.print(""+clip);
	    clip.open(j);
    
	}
	public static void play(int i){
		switch (i){
		//case 1: try{Sound.playAudio(bomb);} catch (Exception e){e.printStackTrace();}
		case 2: try{Sound.playAudio(death);} catch (Exception e){e.printStackTrace();}
		case 3: try{Sound.playAudio(playerShoot);} catch (Exception e){e.printStackTrace();}
		}
	}
}
