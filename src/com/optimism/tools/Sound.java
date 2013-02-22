package com.optimism.tools;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
	
	public static AudioInputStream bomb;
	public static AudioInputStream death;
	public static AudioInputStream playerShoot;
	
	public Sound(){
			try{
				bomb = AudioSystem.getAudioInputStream(new File("res/bomb.wav"));
				death = AudioSystem.getAudioInputStream(new File("res/enemy-death.wav"));
				playerShoot = AudioSystem.getAudioInputStream(new File("res/player-shoot.wav"));
			} catch (UnsupportedAudioFileException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	private static void playAudio(AudioInputStream j) throws Exception {
		AudioFormat format = j.getFormat();
	    if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	      format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
	          .getSampleRate(), format.getSampleSizeInBits() * 2, format
	          .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
	          true); // big endian
	      j = AudioSystem.getAudioInputStream(format, j);
	    }

	    DataLine.Info info = new DataLine.Info(Clip.class, j.getFormat(),
	        ((int) j.getFrameLength() * format.getFrameSize()));
	    Clip clip = (Clip) AudioSystem.getLine(info);

	    clip.open(j);
	}
	public static void play(int i){
		switch (i){
		case 1: try{Sound.playAudio(bomb);} catch (Exception e){e.printStackTrace();}
		case 2: try{Sound.playAudio(death);} catch (Exception e){e.printStackTrace();}
		case 3: try{Sound.playAudio(playerShoot);} catch (Exception e){e.printStackTrace();}
		}
	}
}
