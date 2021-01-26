package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import window.Handler;

public class TowerSpace extends GameObject {

	private Handler handler;

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
		if (getBounds().contains(MouseInput.x, MouseInput.y)) {
			g.setColor(new Color(255, 255, 255, 80));
			g.fillRect(x, y, width, height);
		}

		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
