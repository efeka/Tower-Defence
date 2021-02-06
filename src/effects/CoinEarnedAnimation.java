package effects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class CoinEarnedAnimation extends GameObject {
	
	Animation coinAnim;
	Texture tex = GameMain.getTexture();
	Handler handler;

	public CoinEarnedAnimation(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		coinAnim = new Animation(5, tex.coinEarned[0], tex.coinEarned[1], tex.coinEarned[2], tex.coinEarned[3], tex.coinEarned[4]);
		velY = -1;
	}

	public void tick() {
		y += velY;
		coinAnim.runAnimation();
	}

	public void render(Graphics g) {
		coinAnim.drawAnimation(g, (int) x, (int) y, 39, 32);
		if (coinAnim.getPlayedOnce())
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
