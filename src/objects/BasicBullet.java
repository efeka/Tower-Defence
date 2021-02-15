package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import effects.Trail;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class BasicBullet extends GameObject {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private int damage;

	public BasicBullet(int x, int y, int direction, int damage, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.damage = damage;
		width = height = 12;
		
		switch(direction) {
		case LEFT:
			velX = -3;
			velY = 0;
			break;
		case RIGHT:
			velX = 3;
			velY = 0;
			break;
		case UP:
			velX = 0;
			velY = -3;
			break;
		case DOWN:
			velX = 0;
			velY = 3;
			break;
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		//handler.addObject(new Trail(x - velX, y - velY, ObjectId.Trail, tex.cannonBall[1], 0.15f, handler), Handler.BOTTOM_LAYER);
		Rectangle window = new Rectangle(0, 0, GameMain.WIDTH, GameMain.HEIGHT);
		if (!getBounds().intersects(window)) 
			handler.removeObject(this);
		
		try {
			collision();
		} catch(Exception ignored) {}
	}
	
	private void collision() {
		for (int i = 0; i < handler.layer2.size(); i++) {
			GameObject tempObject = handler.layer2.get(i);
			if (tempObject.getId() == ObjectId.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					tempObject.setHealth(tempObject.getHealth() - damage);
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.cannonBall[1], x, y, width, height, null);
	}

	public Rectangle getBounds() {	
		return new Rectangle(x, y, width, height);
	}

}
