package com.optimism.components;

import com.artemis.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Img extends Component {

	public BufferedImage sprite;
		
	public Img(BufferedImage img) {
		this.sprite = img;
	}
	public Img(String i) {
			try{
				sprite = ImageIO.read(new File(i));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			
			}
	}
}


