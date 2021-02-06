package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class Coin extends GameObject {

	private Handler handler;
	private Texture tex = GameMain.getTexture();
	
	public Coin(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 16;
		
	}

	public void tick() {

	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {	
		return new Rectangle(x - 10, y - 10, width + 20, height + 20);
	}

}
