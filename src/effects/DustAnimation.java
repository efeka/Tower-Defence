package effects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class DustAnimation extends GameObject {
	
	Animation dustAnim;
	Texture tex = GameMain.getTexture();
	Handler handler;

	public DustAnimation(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		dustAnim = new Animation(4, tex.dustEffect[0], tex.dustEffect[1], tex.dustEffect[2], tex.dustEffect[3], tex.dustEffect[4]);
	}

	public void tick() {
		dustAnim.runAnimation();		
	}

	public void render(Graphics g) {
		dustAnim.drawAnimation(g, (int) x, (int) y, 48, 48);
		if (dustAnim.getPlayedOnce())
			handler.removeObject(this); 

	}

	public Rectangle getBounds() {
		return null;
	}

	public Rectangle getAttackBounds() {
		return null;
	}

}
