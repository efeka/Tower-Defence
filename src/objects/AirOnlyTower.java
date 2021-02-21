package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class AirOnlyTower extends GameObject {

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private int shootTimer = 0, shootCooldown = 60;
	private int damage = 40;

	private GameObject target;
	
	private int rotation = 2;

	public AirOnlyTower(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = 49;
		height = 59;
	}

	public void tick() {
		if (shootTimer < shootCooldown)
			shootTimer++;
		else {
			shootTimer = 0;
			target = findBestTarget();
			if (target != null) {
				switch(rotation) {
				case 0:
					handler.addObject(new AirOnlyBullet(x + 8, y, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER);
					break;
				case 1:
					handler.addObject(new AirOnlyBullet(x + 16, y - 8, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER);
					break;
				case 2:
					handler.addObject(new AirOnlyBullet(x + 24, y - 18, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER);
					break;
				case 3:
					handler.addObject(new AirOnlyBullet(x + 16, y - 24, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER, 0);
					break;
				case 4:
					handler.addObject(new AirOnlyBullet(x + 8, y - 32, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER, 0);
					break;	
				case 5:
					handler.addObject(new AirOnlyBullet(x, y - 28, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER, 0);
					break;
				case 6:
					handler.addObject(new AirOnlyBullet(x - 8, y - 18, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER);
					break;
				case 7:
					handler.addObject(new AirOnlyBullet(x - 8, y - 10, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 - 32 + target.getVelY() * 5, damage, handler, ObjectId.TargetBullet), Handler.MIDDLE_LAYER);
					break;
				}
			}
		}
	}

	private GameObject findBestTarget() {
		for(GameObject o : handler.flyingEnemies) 
			if (getRangeBounds().intersects(o.getBounds())) 
				return o;
		return null;
	}

	public void render(Graphics g) {
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y)) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(x - 32 * 4 + 32, y - 32 * 4 + 31, 32 * 7, 32 * 7);
			g.setColor(new Color(255, 255, 255, 150));
			g.drawRect(x - 32 * 4 + 32, y - 32 * 4 + 31, 32 * 7, 32 * 7);	
		}

		if (target != null) {
			float aimX = target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5;
			float aimY = target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5;
			float relativeX = aimX - (x + width / 2);
			float relativeY = aimY - (y + height / 2);
			double angle = Math.atan2(relativeY, relativeX) + 1.5707;
	
			if (isBetween(angle, -0.3926, 0.3926)) {
				g.drawImage(tex.airOnly[4], x - 9, y - 28, width, height, null);
				rotation = 4;
			}
			else if (isBetween(angle, 0.3926, 1.1779)) {
				g.drawImage(tex.airOnly[3], x - 9, y - 28, width, height, null);
				rotation = 3;
			}
			else if (isBetween(angle, 1.1779, 1.9632)) {
				g.drawImage(tex.airOnly[2], x - 9, y - 28, width, height, null);
				rotation = 2;
			}
			else if (isBetween(angle, 1.9632, 2.7485)) {
				g.drawImage(tex.airOnly[1], x - 9, y - 28, width, height, null);
				rotation = 1;
			}
			else if (isBetween(angle, 2.7485, 3.5338)) {
				g.drawImage(tex.airOnly[0], x - 9, y - 28, width, height, null);
				rotation = 0;
			}
			else if (isBetween(angle, 3.5338, 4.33)) {
				g.drawImage(tex.airOnly[7], x - 9, y - 28, width, height, null);
				rotation = 7;
			}
			else if (isBetween(angle, -1.57, -0.7852) || isBetween(angle, 4.33, 4.71)) {
				g.drawImage(tex.airOnly[6], x - 9, y - 28, width, height, null);
				rotation = 6;
			}
			else if (isBetween(angle, -0.7852, -0.3926)) { 
				g.drawImage(tex.airOnly[5], x - 9, y - 28, width, height, null);
				rotation = 5;
			}
			else
				g.drawImage(tex.airOnly[rotation], x - 9, y - 28, width, height, null);
		}
		else
			g.drawImage(tex.airOnly[rotation], x - 9, y - 28, width, height, null);
			
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y))
			g.drawImage(tex.towerSpace, x, y, 32, 32, null);

	}
	
	public boolean isBetween(double angle, double min, double max) {
		boolean compareMin = Double.compare(angle, min) >= 0;
		boolean compareMax = Double.compare(angle, max) <= 0;
		return compareMin && compareMax;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getRangeBounds() {
		return new Rectangle(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);
	}

}
