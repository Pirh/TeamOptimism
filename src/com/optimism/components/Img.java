package com.optimism.components;

import com.artemis.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Img extends Component {

	public BufferedImage sprite;
	private static HashMap<String,BufferedImage> cache = new HashMap<String,BufferedImage>();
		
	public Img(BufferedImage img) {
		this.sprite = img;
	}
	public Img(String i) {
			if (Img.cache.containsKey(i)){
				sprite = Img.cache.get(i);
			}else{
				try{
					sprite = ImageIO.read(new File(i));
					Img.cache.put(i, sprite);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
	}
}


