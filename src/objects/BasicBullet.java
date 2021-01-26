package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class BasicBullet extends GameObject {
	
	private Handler handler;
	
	private float aimX, aimY;
	private float vX, vY;
	private double hypot;
	
	public BasicBullet(int x, int y, float aimX, float aimY, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.aimX = aimX;
		this.aimY = aimY;
		width = height = 16;
		hypot = Math.hypot(aimX - x, aimY - y);
		vX = (float) (5 * (aimX - x) / hypot) * 2;
		vY = (float) (5 * (aimY - y) / hypot) * 2;
	}

	public void tick() {
		x += vX;
		y += vY;
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.layer2.size(); i++) {
			GameObject tempObject = handler.layer2.get(i);
			if (tempObject.getId() == ObjectId.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					tempObject.setHealth(tempObject.getHealth() - 40);
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
