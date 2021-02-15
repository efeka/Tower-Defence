package effects;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Trail extends GameObject {

	private float alpha = 1;
	private float life;
	
	private Handler handler;
	private BufferedImage image;
	private int width, height;
	
	public Trail(int x, int y, ObjectId id, BufferedImage image, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.life = life;
		this.image = image;
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
		g.drawImage(image, x, y, null);
		g.fillRect(x, y, width, height);
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
