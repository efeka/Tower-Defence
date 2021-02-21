package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class EnemyShadow extends GameObject {
	
	private Texture tex = GameMain.getTexture(); 
	private Handler handler;

	public EnemyShadow(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.enemyShadow, x, y, width, height, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
