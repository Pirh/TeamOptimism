package com.optimism.tools;
package com.optimism.components;

import com.artemis.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Snd extends Component {

	public AudioInputStream sound;
	private static HashMap<String,AudioInputStream> cache = new HashMap<String,AudioInputStream>();
		
	public Snd(AudioInputStream snd) {
		this.sound = snd;
	}
	public Snd(String i) {
			if (Snd.cache.containsKey(i)){
				this.sound = Snd.cache.get(i);
			}else{
				try{
					this.sound = AudioSystem.getAudioInputStream(new File(i));
					Snd.cache.put(i, sound);
				} catch (UnsupportedAudioFileException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}



public class Sound {

}
