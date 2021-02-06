package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class TowerSpace extends GameObject {

	private Handler handler;
	private Texture tex = GameMain.getTexture();
	
	public final int EMPTY = 0;
	public final int TOWER1 = 1;
	public final int TOWER2 = 2;
	public final int TOWER3 = 3;
	public final int TOWER4 = 4;
	public boolean isEmpty = true;
	public int towerType = EMPTY;
	
	private TowersMenu menu;

	public TowerSpace(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
		menu = new TowersMenu(x - 24, y - 82, this, handler, ObjectId.TowersMenu);
	}

	public void tick() {
		if (MouseInput.leftPressed) {
			if (!menu.isOpen && isEmpty && getBounds().contains(MouseInput.x, MouseInput.y)) {
				menu.isOpen = true;
				handler.addObject(menu, Handler.TOP_LAYER);
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.grassFloor[15], x, y, width, height, null);
		
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y)) {
			g.drawImage(tex.towerSpace, x, y, width, height, null);
			g.setColor(new Color(255, 255, 255, 50));
			g.fillRect(x, y, width, height);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
