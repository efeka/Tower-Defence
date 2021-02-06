package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import window.GameMain;
import window.Handler;

public class BasicBullet extends GameObject {
	
	private Handler handler;
	
	private float aimX, aimY;
	private float vX, vY;
	private double hypot;
	private double angle = 0;
	
	public BasicBullet(int x, int y, float aimX, float aimY, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.aimX = aimX;
		this.aimY = aimY;
		width = 4;
		height = 16;
		hypot = Math.hypot(aimX - x, aimY - y);
		vX = (float) (7 * (aimX - x) / hypot);
		vY = (float) (7 * (aimY - y) / hypot);
		float relativeX = aimX - (x + width / 2);
		float relativeY = aimY - (y + height / 2);
		angle = Math.atan2(relativeY, relativeX) + 1.5596856728972892*3;
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angle, x + width/2, y + height/2);
		g2d.setColor(Color.orange);
		g.fillOval(x, y, width, height);
		g2d.setColor(Color.white);
		g.drawOval(x, y, width, height);
		g2d.rotate(-angle, x + width/2, y + height/2);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
