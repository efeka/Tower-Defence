package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;

public class GameMenu extends GameObject {

	private Texture tex = GameMain.getTexture();
	
	private long pressTime = 0L;
	private Font font;
	
	public static int coins = 0;
	public static int currentWave = 1, maxWaves = 7;

	public GameMenu(int x, int y, ObjectId id) {
		super(x, y, id);
		font = GameMain.getFont(28);
		width = 32 * 11;
		height = 32 * 2;
	}

	public void tick() {
		if (MouseInput.leftPressed && System.currentTimeMillis() - pressTime > 300) {
			pressTime = System.currentTimeMillis();
			if (getPauseButtonBounds().contains(MouseInput.x, MouseInput.y)) {
				if (GameMain.state == GameMain.STATE.GAME)
					GameMain.state = GameMain.STATE.PAUSED;
				else if (GameMain.state == GameMain.STATE.PAUSED)
					GameMain.state = GameMain.STATE.GAME;
			}
		}
	}

	public void render(Graphics g) {
		if (GameMain.state == GameMain.STATE.PAUSED) {
			if (getPauseButtonBounds().contains(MouseInput.x, MouseInput.y)) 
				g.drawImage(tex.gameMenu[3], x, y, null);
			else
				g.drawImage(tex.gameMenu[2], x, y, null);
		}
		else {
			if (getPauseButtonBounds().contains(MouseInput.x, MouseInput.y)) 
				g.drawImage(tex.gameMenu[1], x, y, null);
			else
				g.drawImage(tex.gameMenu[0], x, y, null);
		}
		
		g.setColor(Color.white);
		g.setFont(font);
		
		String coin = coins + "";
		while (coin.length() < 3)
			coin = "0" + coin;
		g.drawString(coin, x + 70, y + height / 2 + 9);
		
		g.drawString(Levels.currentWave + "/" + Levels.maxWaves, x + width - 132, y + height / 2 + 9);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getPauseButtonBounds() {
		return new Rectangle(x + width / 2 - 32, y + 5, 64, 54);
	}

}
