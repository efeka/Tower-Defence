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
		menu.isOpen = false;
		handler.addObject(menu, Handler.TOP_LAYER);
	}

	public void tick() {
		if (MouseInput.leftPressed) {
			if (!menu.isOpen && isEmpty && getBounds().contains(MouseInput.x, MouseInput.y)) {
				menu.isOpen = true;
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.grassFloor[15], x, y, width, height, null);
		
		if (isEmpty && GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y)) 
			g.drawImage(tex.towerSpace, x, y, width, height, null);
	}
	
	public void addMenu() {
		if (!handler.layer3.contains(menu))
			handler.addObject(menu, Handler.MENU_LAYER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}