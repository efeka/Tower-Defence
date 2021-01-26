package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import window.Handler;

public class BasicTower extends GameObject {

	private Handler handler;

	private int shootTimer = 0, shootCooldown = 60;

	public BasicTower(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
	}

	public void tick() {
		if (shootTimer < shootCooldown)
			shootTimer++;
		else {
			shootTimer = 0;
			GameObject target = findBestTarget();
			if (target != null) 
				handler.addObject(new BasicBullet(x + width / 2 - 8, y + height / 2 - 8, target.getX() + target.getWidth() / 2 - 8, target.getY() + target.getHealth() / 2 - 8, handler, ObjectId.BasicBullet), Handler.MIDDLE_LAYER);
		}
	}

	private GameObject findBestTarget() {
		for(GameObject o : handler.enemies) 
			if (getRangeBounds().intersects(o.getBounds())) 
				return o;
		return null;
	}

	public void render(Graphics g) {
		GameObject target = findBestTarget();
		Graphics2D g2d = (Graphics2D) g;
		if (target != null) {
			g2d.setColor(new Color(255, 0, 0, 100));
			g2d.fill(target.getBounds());
		}
		
		if (getBounds().contains(MouseInput.x, MouseInput.y)) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);
			g.setColor(new Color(255, 255, 255, 150));
			g.drawRect(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);	
		}

		g.setColor(Color.pink);
		g.fillRect(x, y, width, height);

		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getRangeBounds() {
		return new Rectangle(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);
	}

}
