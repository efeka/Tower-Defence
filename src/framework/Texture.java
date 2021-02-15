package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {
	
	private BufferedImage block_sheet = null;
	private BufferedImage menu_sheet = null;
	private BufferedImage tower_sheet = null;
	private BufferedImage effects_sheet = null;
	private BufferedImage enemy_sheet = null;
	
	public BufferedImage[] cursors = new BufferedImage[2];
	
	public BufferedImage[] grassFloor = new BufferedImage[16];
	public BufferedImage towerSpace;
	
	public BufferedImage[] coins = new BufferedImage[5];
	public BufferedImage[] coinEarned = new BufferedImage[5];
	
	public BufferedImage[] gameMenu = new BufferedImage[4];
	public BufferedImage[] rockMenu = new BufferedImage[3];
	
	public BufferedImage[] rocks = new BufferedImage[3];
	
	public BufferedImage[] towers = new BufferedImage[4];
	public BufferedImage[] plusTower = new BufferedImage[5];
	public BufferedImage[] cannonBall = new BufferedImage[2];
	
	public BufferedImage[] dustEffect = new BufferedImage[5];
	
	public BufferedImage basicEnemy;
	
	public BufferedImage[] ballistaTower = new BufferedImage[6];
	public BufferedImage arrow; 
	
	public BufferedImage pauseMenu;
	public BufferedImage[] resumeButton = new BufferedImage[2], restartButton = new BufferedImage[2], levelsButton = new BufferedImage[2], settingsButton = new BufferedImage[2], mainMenuButton = new BufferedImage[2], quitButton = new BufferedImage[2];
	
	public BufferedImage[] bomb = new BufferedImage[9];
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			menu_sheet = loader.loadImage("/menu_sheet.png");
			tower_sheet = loader.loadImage("/tower_sheet.png");
			effects_sheet = loader.loadImage("/effects_sheet.png");
			enemy_sheet = loader.loadImage("/enemy_sheet.png");
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
		
		towers[0] = tower_sheet.getSubimage(1, 1, 48, 48);
		towers[1] = tower_sheet.getSubimage(50, 1, 48, 48);
		towers[2] = tower_sheet.getSubimage(1, 50, 48, 48);
		towers[3] = tower_sheet.getSubimage(1, 99, 48, 48);
		
		cannonBall[0] = tower_sheet.getSubimage(295, 1, 16, 16);
		cannonBall[1] = tower_sheet.getSubimage(295, 18, 12, 12);
		
		basicEnemy = enemy_sheet.getSubimage(1, 1, 32, 32);
		
		for (int i = 0; i < 5; i++)
			plusTower[i] = tower_sheet.getSubimage(50 + 49 * i, 1, 48, 48);
		
		for (int i = 0; i < 5; i++)
			dustEffect[i] = effects_sheet.getSubimage(1 + 33 * i, 1, 32, 32);
		
		for (int i = 0; i < 6; i++)
			ballistaTower[i] = tower_sheet.getSubimage(1 + 49 * i, 50, 48, 48);
		
		arrow = tower_sheet.getSubimage(295, 48, 7, 42);
		
		pauseMenu = menu_sheet.getSubimage(354, 1, 300, 400);
		resumeButton[0] = menu_sheet.getSubimage(1, 442, 274, 59);
		resumeButton[1] = menu_sheet.getSubimage(276, 442, 274, 59);
		restartButton[0] = menu_sheet.getSubimage(1, 502, 274, 59);
		restartButton[1] = menu_sheet.getSubimage(276, 502, 274, 59);
		levelsButton[0] = menu_sheet.getSubimage(1, 562, 274, 59);
		levelsButton[1] = menu_sheet.getSubimage(276, 562, 274, 59);
		settingsButton[0] = menu_sheet.getSubimage(1, 622, 274, 59);
		settingsButton[1] = menu_sheet.getSubimage(276, 622, 274, 59);
		mainMenuButton[0] = menu_sheet.getSubimage(1, 682, 274, 59);
		mainMenuButton[1] = menu_sheet.getSubimage(276, 682, 274, 59);
		quitButton[0] = menu_sheet.getSubimage(1, 742, 274, 59);
		quitButton[1] = menu_sheet.getSubimage(276, 742, 274, 59);
		
		for (int i = 0; i < 9; i++)
			bomb[i] = tower_sheet.getSubimage(1 + 21 * i, 148, 20, 48);
	
	}
}
