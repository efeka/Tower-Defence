package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class BallistaArrow extends GameObject {
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private float vX, vY;
	private double hypot;
	private double angle = 0;
	private int damage;
	
	private ArrayList<Integer> targetsHit = new ArrayList<Integer>();

	public BallistaArrow(int x, int y, float aimX, float aimY, double angle, int damage, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.damage = damage;
		this.angle = angle;
		width = 7;
		height = 42;
		hypot = Math.hypot(aimX - x, aimY - y);
		vX = (float) (7 * (aimX - x) / hypot);
		vY = (float) (7 * (aimY - y) / hypot);
		float relativeX = aimX - (x + width / 2);
		float relativeY = aimY - (y + height / 2);
		//angle = Math.atan2(relativeY, relativeX) + 1.57079633;
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
			if (tempObject.getId() == ObjectId.BasicEnemy && !targetsHit.contains(tempObject.getPriority())) {
				if (getBounds().intersects(tempObject.getBounds())) {
					targetsHit.add(tempObject.getPriority());
					tempObject.setHealth(tempObject.getHealth() - damage);
			
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angle, x + width / 2, y + height / 2);
		g.drawImage(tex.arrow, x, y, width, height, null);
		g2d.rotate(-angle, x + width / 2, y + height / 2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
