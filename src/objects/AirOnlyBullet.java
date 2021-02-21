package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import effects.AirOnlyTrail;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class AirOnlyBullet extends GameObject {
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private float vX, vY;
	private double hypot;
	private int damage;
	
	public AirOnlyBullet(int x, int y, float aimX, float aimY, int damage, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.damage = damage;
		width = 16;
		height = 16;
		hypot = Math.hypot(aimX - x, aimY - y);
		vX = (float) (7 * (aimX - x) / hypot);
		vY = (float) (7 * (aimY - y) / hypot);
	}

	public void tick() {
		x += vX;
		y += vY;
		
		Rectangle window = new Rectangle(0, 0, GameMain.WIDTH, GameMain.HEIGHT);
		if (!getBounds().intersects(window)) 
			handler.removeObject(this);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.flyingEnemies.size(); i++) {
			GameObject tempObject = handler.flyingEnemies.get(i);
			if (tempObject.getId() == ObjectId.FlyingEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					tempObject.setHealth(tempObject.getHealth() - damage);
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		handler.addObject(new AirOnlyTrail(x, y, 0.11f, handler, ObjectId.Trail), Handler.MIDDLE_LAYER);
		g.drawImage(tex.airOnlyBullet[0], x, y, width, height, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
