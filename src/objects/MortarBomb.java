package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import effects.BombAnimation;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class MortarBomb extends GameObject {

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private float vX, vY;
	private float aimX, aimY;
	private double hypot;
	
	private float renderY;
	private float gravity = 0.5f;
	private float rvY = -7;
	private float limitVelY = 12;
	private int tickCount;
	
	public MortarBomb(int x, int y, float aimX, float aimY, int damage, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.aimX = aimX;
		this.aimY = aimY;
		width = height = 16;
		hypot = Math.hypot(aimX - x, aimY - y);
		vX = (float) (1 * (aimX - x) / hypot);
		vY = (float) (1 * (aimY - y) / hypot);
		renderY = y;
		
		float resultant = (float) Math.sqrt(vX * vX + vY * vY);
		tickCount = (int) (hypot / resultant);
	}

	public void tick() {
		x += vX;
		y += vY;
		
		renderY += rvY;
		if (rvY < limitVelY)
			rvY += gravity;
		
		tickCount--;
		if (tickCount <= 0)
			handler.removeObject(this);
//		else
//			handler.removeObject(this);
//		if (Math.abs(aimX - x) < 15 && Math.abs(aimY - y) < 15) {
//			handler.removeObject(this);
//		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.cannonBall[0], x, (int) renderY, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}
