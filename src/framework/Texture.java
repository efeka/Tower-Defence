package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {
	
	private BufferedImage block_sheet = null;
	private BufferedImage menu_sheet = null;
	
	public BufferedImage[] cursors = new BufferedImage[2];
	
	public BufferedImage[] grassFloor = new BufferedImage[16];
	public BufferedImage towerSpace;
	
	public BufferedImage[] coins = new BufferedImage[5];
	public BufferedImage[] coinEarned = new BufferedImage[5];
	
	public BufferedImage[] gameMenu = new BufferedImage[4];
	public BufferedImage[] rockMenu = new BufferedImage[3];
	
	public BufferedImage[] rocks = new BufferedImage[3];
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			menu_sheet = loader.loadImage("/menu_sheet.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		//grass floor
		for (int i = 0; i < 10; i++)
			grassFloor[i] = block_sheet.getSubimage(1 + 33 * i, 1, 32, 32);
		for (int i = 0; i < 6; i++)
			grassFloor[i + 10] = block_sheet.getSubimage(1 + 33 * i, 34, 32, 32);
		
		towerSpace = block_sheet.getSubimage(1, 67, 32, 32);
		
		cursors[0] = block_sheet.getSubimage(1, 100, 32, 32);
		
		for (int i = 0; i < 5; i++)
			coins[i] = block_sheet.getSubimage(1 + 33 * i, 133, 32, 32);
		for (int i = 0; i < 5; i++)
			coinEarned[i] = block_sheet.getSubimage(1 + 40 * i, 166, 39, 32);
		
		for (int i = 0; i < 4; i++)
			gameMenu[i] = menu_sheet.getSubimage(1, 1 + 66 * i, 32 * 11, 32 * 2 + 1);
		
		rocks[0] = block_sheet.getSubimage(199, 34, 32, 32);
		
		for (int i = 0; i < 3; i++)
			rockMenu[i] = menu_sheet.getSubimage(1, 265 + 59 * i, 199, 58);
	}
}
