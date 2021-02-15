package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import effects.DustAnimation;
import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class BasicTower extends GameObject {

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private int shootTimer = 0, shootCooldown = 60;
	private int damage = 40;

	private GameObject target;

	public BasicTower(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 48;
	}

	public void tick() {
		if (shootTimer < shootCooldown)
			shootTimer++;
		else {
			shootTimer = 0;
			target = findBestTarget();
			if (target != null) 
				handler.addObject(new TargetBullet(x + width / 2 - 16, y + height / 2 - 16, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER, 0);
		}
	}

	private GameObject findBestTarget() {
		for(GameObject o : handler.enemies) 
			if (getRangeBounds().intersects(o.getBounds())) 
				return o;
		return null;
	}

	public void render(Graphics g) {
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y)) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(x - 32 * 4 + 32, y - 32 * 4 + 32, 32 * 7, 32 * 7);
			g.setColor(new Color(255, 255, 255, 150));
			g.drawRect(x - 32 * 4 + 32, y - 32 * 4 + 32, 32 * 7, 32 * 7);	
		}

		if (target != null) {
			float aimX = target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5;
			float aimY = target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5;
			float relativeX = aimX - (x + width / 2);
			float relativeY = aimY - (y + height / 2);
			double angle = Math.atan2(relativeY, relativeX) + 1.57079633;

			Graphics2D g2d = (Graphics2D) g;
			g2d.rotate(angle, x + width / 2 - 8, y + height / 2 - 8);
			g.drawImage(tex.towers[0], x - 8, y - 8, width, height, null);
			g2d.rotate(-angle, x + width / 2 - 8, y + height / 2 - 8);
		}
		else
			g.drawImage(tex.towers[0], x - 8, y - 8, width, height, null);
		
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y))
			g.drawImage(tex.towerSpace, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getRangeBounds() {
		return new Rectangle(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);
	}

}
