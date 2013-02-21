package com.optimism.components;

import com.artemis.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Img extends Component {

	private BufferedImage sprite;
	private HashMap<String,BufferedImage>( cache;
		
	public Img(BufferedImage img) {
		this.sprite = img;
	}
	public Img(String i) {
			if (cache.containsKey(i)){
				sprite = cache.get(i);
			}else{
				try{
					sprite = ImageIO.read(new File(i));
					cache.put(i, sprite);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
	}
}


