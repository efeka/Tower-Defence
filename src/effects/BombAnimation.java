package effects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import objects.MortarBomb;
import window.Animation;
import window.GameMain;
import window.Handler;

public class BombAnimation extends GameObject {
	
	Animation bombAnim;
	Texture tex = GameMain.getTexture();
	Handler handler;
	
	MortarBomb bomb;
	
	public BombAnimation(int x, int y, MortarBomb bomb, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.bomb = bomb;
		bombAnim = new Animation(4, tex.bomb[0], tex.bomb[1], tex.bomb[2], tex.bomb[3], tex.bomb[4], tex.bomb[5], tex.bomb[6], tex.bomb[7], tex.bomb[8], tex.bomb[7], tex.bomb[6], tex.bomb[5], tex.bomb[4], tex.bomb[3], tex.bomb[2], tex.bomb[1], tex.bomb[0]);
	}

	public void tick() {
		x = bomb.getX();
		y = bomb.getY();
		bombAnim.runAnimation();		
	}

	public void render(Graphics g) {
		bombAnim.drawAnimation(g, (int) x, (int) y - 48, 20, 48);
		if (bombAnim.getPlayedOnce())
			handler.removeObject(this); 

	}

	public Rectangle getBounds() {
		return null;
	}

	public Rectangle getAttackBounds() {
		return null;
	}

}
