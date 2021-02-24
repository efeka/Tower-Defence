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

public class BasicTower extends GameObject {

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private int shootTimer = 0, shootCooldown = 60;
	private GameObject target;
	
	private TowerUpgradeMenu menu;
	public TowerSpace towerSpace;

	public BasicTower(int x, int y, TowerSpace towerSpace, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.towerSpace = towerSpace;
		damage = 40;
		range = 7;
		attackSpeed = 6;
		menu = new TowerUpgradeMenu(x, y, this, 0, handler, ObjectId.TowerUpgradeMenu);
		handler.addObject(menu, Handler.MENU_LAYER);
		width = height = 48;
	}

	public void tick() {
		if (!menu.isOpen && MouseInput.leftPressed && getBounds().contains(MouseInput.x, MouseInput.y))
			menu.isOpen = true;
		
		shootCooldown = attackSpeed * 10;
		
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
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(new Color(255, 255, 255, 30));
			g2d.fill(getRangeBounds());
			g.setColor(new Color(255, 255, 255, 150));
			g2d.draw(getRangeBounds());	
		}

		if (target != null) {
			float aimX = target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5;
			float aimY = target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5;
			float relativeX = aimX - (x + width / 2);
			float relativeY = aimY - (y + height / 2);
			double angle = Math.atan2(relativeY, relativeX) + 1.57079633;
			
			AffineTransform reset = new AffineTransform();
			reset.rotate(0, 0, 0);
			Graphics2D g2 = (Graphics2D)g;
			g2.rotate(angle, (int) x + width / 2 - 8, y + height / 2 - 8);
			g.drawImage(tex.towers[0], x - 8, y - 8, width, height, null);
			g2.setTransform(reset);
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
		return new Rectangle(x - 32 * range / 2 + 16, y - 32 * range / 2 + 16, 32 * range, 32 * range);
	}

}
