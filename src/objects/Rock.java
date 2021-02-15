package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class Rock extends GameObject {
	
	private Handler handler;
	private Texture tex = GameMain.getTexture();
	
	private Font font = GameMain.getFont(15);
	
	private RockMenu rockMenu;
	
	public boolean destroyed = false;
	
	public Rock(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
		
		rockMenu = new RockMenu(x - 83, y - 60, this, handler, ObjectId.RockMenu);
		handler.addObject(rockMenu, Handler.TOP_LAYER);
	}

	public void tick() {
		if (MouseInput.leftPressed) {
			if (!rockMenu.isOpen && !destroyed && getBounds().contains(MouseInput.x, MouseInput.y)) {
				rockMenu.isOpen = true;
			}
		}
	}

	public void render(Graphics g) {
		if (!destroyed)
			g.drawImage(tex.rocks[0], x, y, width, height, null);

		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y)) 
			g.drawImage(tex.towerSpace, x, y, width, height, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
