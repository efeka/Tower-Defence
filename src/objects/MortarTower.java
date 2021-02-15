package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import effects.DustAnimation;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class MortarTower extends GameObject {
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private int damage = 50;
	private int shootTimer = 0, shootCooldown = 60;
	
	private GameObject target;
	
	public MortarTower(int x, int y, Handler handler, ObjectId id) {
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
			if (target != null) {
				float aimX = target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5;
				float aimY = target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5;
				float relativeX = aimX - (x + width / 2);
				float relativeY = aimY - (y + height / 2);
				handler.addObject(new MortarBomb(x + width / 2 - 24, y + height / 2 - 24, target.getX() + target.getWidth() / 2 - 8 + target.getVelX() * 5, target.getY() + target.getHeight() / 2 - 8 + target.getVelY() * 5, damage, handler, ObjectId.MortarBomb), Handler.TOP_LAYER, 0);
				handler.addObject(new DustAnimation(x - 12, y - height, handler, ObjectId.DustAnimation),  Handler.TOP_LAYER);
			}
		}
	}
	
	private GameObject findBestTarget() {
		for(GameObject o : handler.enemies) 
			if (getRangeBounds().intersects(o.getBounds())) 
				return o;
		return null;
	}

	public void render(Graphics g) {
		g.drawImage(tex.towers[3], x - 8, y - 8, width, height, null);
	}

	public Rectangle getBounds() {	
		return null;
	}
	
	public Rectangle getRangeBounds() {
		return new Rectangle(x - 32 * 4 + width, y - 32 * 4 + height, 32 * 7, 32 * 7);
	}

}
