package effects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class PlusTowerShootAnimation extends GameObject {
	
	Animation shootAnim;
	Texture tex = GameMain.getTexture();
	Handler handler;

	public PlusTowerShootAnimation(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		shootAnim = new Animation(5, tex.plusTower[0], tex.plusTower[1], tex.plusTower[2], tex.plusTower[3], tex.plusTower[4]);
	}

	public void tick() {
		shootAnim.runAnimation();
	}

	public void render(Graphics g) {
		shootAnim.drawAnimation(g, (int) x, (int) y, 48, 48);
		if (shootAnim.getPlayedOnce())
			handler.removeObject(this); 
	}

	public Rectangle getBounds() {
		return null;
	}

	public Rectangle getAttackBounds() {
		return null;
	}

	public void takeDamage(int damage) {

	}
}
