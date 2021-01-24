package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {
	
	private BufferedImage block_sheet = null;
	
	public BufferedImage[] player = new BufferedImage[15];
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		//player
		for (int i = 0; i < 7; i++)
			player[i] = block_sheet.getSubimage(1 + 33 * i, 1, 32, 32);
	}
}
