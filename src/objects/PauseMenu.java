package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;

public class PauseMenu extends GameObject {

	private Texture tex = GameMain.getTexture();
	
	public PauseMenu(int x, int y, ObjectId id) {
		super(x, y, id);
		width = 300;
		height = 400;
	}

	public void tick() {
		int mx = MouseInput.x;
		int my = MouseInput.y;
		if (MouseInput.leftPressed && getResumeBounds().contains(mx, my))
			GameMain.state = GameMain.STATE.GAME;
		if (MouseInput.leftPressed && getQuitBounds().contains(mx, my))
			System.exit(0);
	}

	public void render(Graphics g) {
		g.drawImage(tex.pauseMenu, x - width / 2, y - height / 2, width, height, null);
		
		int mx = MouseInput.x;
		int my = MouseInput.y;
		
		if (getResumeBounds().contains(mx, my))
			g.drawImage(tex.resumeButton[1], x - width / 2 + 13, y - height / 2 + 13, null);
		else
			g.drawImage(tex.resumeButton[0], x - width / 2 + 13, y - height / 2 + 13, null);
		
		if (getRestartBounds().contains(mx, my))
			g.drawImage(tex.restartButton[1], x - width / 2 + 13, y - height / 2 + 13 + 63, null);
		else
			g.drawImage(tex.restartButton[0], x - width / 2 + 13, y - height / 2 + 13 + 63, null);
		
		if (getLevelsBounds().contains(mx, my))
			g.drawImage(tex.levelsButton[1], x - width / 2 + 13, y - height / 2 + 13 + 63 * 2, null);
		else
			g.drawImage(tex.levelsButton[0], x - width / 2 + 13, y - height / 2 + 13 + 63 * 2, null);
		
		if (getSettingsBounds().contains(mx, my))
			g.drawImage(tex.settingsButton[1], x - width / 2 + 13, y - height / 2 + 13 + 63 * 3, null);
		else
			g.drawImage(tex.settingsButton[0], x - width / 2 + 13, y - height / 2 + 13 + 63 * 3, null);
		
		if (getMainMenuBounds().contains(mx, my))
			g.drawImage(tex.mainMenuButton[1], x - width / 2 + 13, y - height / 2 + 13 + 63 * 4, null);
		else
			g.drawImage(tex.mainMenuButton[0], x - width / 2 + 13, y - height / 2 + 13 + 63 * 4, null);
		
		if (getQuitBounds().contains(mx, my))
			g.drawImage(tex.quitButton[1], x - width / 2 + 13, y - height / 2 + 13 + 63 * 5, null);
		else
			g.drawImage(tex.quitButton[0], x - width / 2 + 13, y - height / 2 + 13 + 63 * 5, null);
	}
	
	public Rectangle getResumeBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13, 274, 59);
	}
	
	public Rectangle getRestartBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13 + 63, 274, 59);
	}
	
	public Rectangle getLevelsBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13 + 63 * 2, 274, 59);
	}
	
	public Rectangle getSettingsBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13 + 63 * 3, 274, 59);
	}
	
	public Rectangle getMainMenuBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13 + 63 * 4, 274, 59);
	}
	
	public Rectangle getQuitBounds() {
		return new Rectangle(x - width / 2 + 13, y - height / 2 + 13 + 63 * 5, 274, 59);
	}

	public Rectangle getBounds() {	
		return null;
	}

}
