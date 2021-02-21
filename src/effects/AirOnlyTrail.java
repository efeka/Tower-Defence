package effects;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class AirOnlyTrail extends GameObject {

	private float alpha = 1;
	private float life;
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	public AirOnlyTrail(int x, int y, float life, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.life = life;
	}

	public void tick() {
		if (alpha > life) 
			alpha -= life - 0.01f;
		else
			handler.removeObject(this);
	}


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		int random = (int) (Math.random() * 2) + 1;
		g.drawImage(tex.airOnlyBullet[random], x, y, null);
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}
}
