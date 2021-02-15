package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import effects.PlusTowerShootAnimation;
import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class PlusShooterTower extends GameObject {
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private int shootTimer = 0, shootCooldown = 90;
	private int damage = 25;
	
	public PlusShooterTower(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 48;
	}

	public void tick() {
		if (shootCooldown - shootTimer == 34)
			handler.addObject(new PlusTowerShootAnimation(x - 8, y - 8, handler, ObjectId.PlusShooterTower), Handler.TOP_LAYER);
		if (shootTimer < shootCooldown)
			shootTimer++;
		else {
			shootTimer = 0;
			handler.addObject(new BasicBullet(x + width / 2 - 14, y + height / 2 - 14, BasicBullet.LEFT, damage, handler, ObjectId.BasicBullet), Handler.MIDDLE_LAYER, 0);
			handler.addObject(new BasicBullet(x + width / 2 - 14, y + height / 2 - 14, BasicBullet.RIGHT, damage, handler, ObjectId.BasicBullet), Handler.MIDDLE_LAYER, 0);
			handler.addObject(new BasicBullet(x + width / 2 - 14, y + height / 2 - 14, BasicBullet.UP, damage, handler, ObjectId.BasicBullet), Handler.MIDDLE_LAYER, 0);
			handler.addObject(new BasicBullet(x + width / 2 - 14, y + height / 2 - 14, BasicBullet.DOWN, damage, handler, ObjectId.BasicBullet), Handler.MIDDLE_LAYER, 0);
		}
	}

	public void render(Graphics g) {
		if (shootCooldown - shootTimer > 25)
			g.drawImage(tex.plusTower[0], x - 8, y - 8, width, height, null);
		
		if (GameMain.state == GameMain.STATE.GAME && getBounds().contains(MouseInput.x, MouseInput.y))
			g.drawImage(tex.towerSpace, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
