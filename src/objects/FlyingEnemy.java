package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import effects.CoinEarnedAnimation;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class FlyingEnemy extends GameObject {
	
	public static final int MOVE_UP = 0;
	public static final int MOVE_DOWN = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_RIGHT = 3;

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private int velocity = 1;
	private boolean slowed = false;
	private int slowCount = 0;
	private int facing = 1;

	private int maxHealth = 150;
	
	private Animation flyAnim;
	private Animation flyLeftAnim;
	private Animation flyUpAnim;
	private EnemyShadow shadow;
	
	public FlyingEnemy(int x, int y, int initialDirection, int priority, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.priority = priority;
		width = 32;
		height = 64;
		health = maxHealth;

		switch(initialDirection) {
		case MOVE_UP:
			velX = 0;
			velY = -velocity;
			break;
		case MOVE_DOWN:
			velX = 0;
			velY = velocity;
			break;
		case MOVE_LEFT:
			velX = -velocity;
			velY = 0;
			break;
		case MOVE_RIGHT:
			velX = velocity;
			velY = 0;
			break;
		}
		
		shadow = new EnemyShadow(x, y, handler, ObjectId.EnemyShadow);
		handler.addObject(shadow, Handler.BOTTOM_LAYER);
		
		flyAnim = new Animation(5, tex.flyingEnemy[0], tex.flyingEnemy[1], tex.flyingEnemy[2], tex.flyingEnemy[3], tex.flyingEnemy[4], tex.flyingEnemy[0], tex.flyingEnemy[1], tex.flyingEnemy[2], tex.flyingEnemy[3], tex.flyingEnemy[4], tex.flyingEnemy[0], tex.flyingEnemy[1], tex.flyingEnemy[2], tex.flyingEnemy[3], tex.flyingEnemy[4], tex.flyingEnemy[0], tex.flyingEnemy[1], tex.flyingEnemy[2], tex.flyingEnemy[3], tex.flyingEnemy[4]);
		flyLeftAnim = new Animation(5, tex.flyingEnemy[5], tex.flyingEnemy[6], tex.flyingEnemy[7], tex.flyingEnemy[8], tex.flyingEnemy[9], tex.flyingEnemy[5], tex.flyingEnemy[6], tex.flyingEnemy[7], tex.flyingEnemy[8], tex.flyingEnemy[9], tex.flyingEnemy[5], tex.flyingEnemy[6], tex.flyingEnemy[7], tex.flyingEnemy[8], tex.flyingEnemy[9], tex.flyingEnemy[5], tex.flyingEnemy[6], tex.flyingEnemy[7], tex.flyingEnemy[8], tex.flyingEnemy[9]);
		flyUpAnim = new Animation(5, tex.flyingEnemy[10], tex.flyingEnemy[11], tex.flyingEnemy[12], tex.flyingEnemy[13], tex.flyingEnemy[14], tex.flyingEnemy[10], tex.flyingEnemy[11], tex.flyingEnemy[12], tex.flyingEnemy[13], tex.flyingEnemy[14], tex.flyingEnemy[10], tex.flyingEnemy[11], tex.flyingEnemy[12], tex.flyingEnemy[13], tex.flyingEnemy[14], tex.flyingEnemy[10], tex.flyingEnemy[11], tex.flyingEnemy[12], tex.flyingEnemy[13], tex.flyingEnemy[14]);
	}

	public void tick() {
		shadow.setX(x);
		shadow.setY(y);
		
		if (health <= 0) {
			handler.removeObject(this);
			handler.removeObject(shadow);
			handler.flyingEnemies.remove(this);
			int random = (int)(Math.random() * 3 + 1);
			for (int i = 0; i < random; i++) {
				if (GameMenu.coins < 999)
					GameMenu.coins++;
				handler.addObject(new CoinEarnedAnimation(x, y + i * 10, handler, ObjectId.Coin), Handler.TOP_LAYER);
			}
		}

		if (velX >= 0)
			facing = 1;
		else
			facing = -1;

		if (!slowed) {
			x += velX;
			y += velY;
		}
		else {
			slowCount++;
			if (slowCount % 2 == 0) {
				x += velX;
				y += velY;
			}
		}

		collision();
		
		flyAnim.runAnimation();
		flyLeftAnim.runAnimation();
		flyUpAnim.runAnimation();
	}
	
	private void collision() {
		Rectangle window = new Rectangle(0, 0, GameMain.WIDTH, GameMain.HEIGHT);
		if (!getBounds().intersects(window))
			handler.removeObject(this);

		for (int i = 0; i < handler.layer1.size(); i++) {
			GameObject tempObject = handler.layer1.get(i);
			if (tempObject.getId() == ObjectId.PathingHelper) {
				if (getBottomBounds().intersects(tempObject.getBounds())) {
					switch(tempObject.getUtility()) {
					case PathingHelper.UP:
						if (velX > 0) {
							velX = 0;
							velY = -velocity;
							x = tempObject.getX() - width;
							y = tempObject.getY();
						}
						else {
							velX = 0;
							velY = -velocity;
							x = tempObject.getX() + tempObject.getWidth();
							y = tempObject.getY();
						}
						break;
					case PathingHelper.DOWN:
						if (velX > 0) {
							velX = 0;
							velY = velocity;
							x = tempObject.getX() - width;
							y = tempObject.getY();
						}
						else {
							velX = 0;
							velY = velocity;
							x = tempObject.getX() + tempObject.getWidth();
							y = tempObject.getY();
						}
						break;
					case PathingHelper.LEFT:
						if (velY > 0) {
							velX = -velocity;
							velY = 0;
							x = tempObject.getX();
							y = tempObject.getY() - 32;
						}
						else {
							velX = -velocity;
							velY = 0;
							x = tempObject.getX();
							y = tempObject.getY() + tempObject.getHeight();
						}
						break;
					case PathingHelper.RIGHT:
						if (velY > 0) {
							velX = velocity;
							velY = 0;
							x = tempObject.getX();
							y = tempObject.getY() - 32;
						}
						else {
							velX = velocity;
							velY = 0;
							x = tempObject.getX();
							y = tempObject.getY() + tempObject.getHeight();
						}
						break;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		if (velX != 0) {
			if (facing == 1)
				flyAnim.drawAnimation(g, x, y - 28, width, height);
			else
				flyLeftAnim.drawAnimation(g, x, y - 28, width, height);
		}
		else
			flyUpAnim.drawAnimation(g, x, y - 28, width, height);
		
		g.setColor(Color.red);
		g.fillRect(x - (maxHealth / 4 - width) / 2 - 1, y - 40, maxHealth / 4, 4);
		g.setColor(Color.green);
		g.fillRect(x - (maxHealth / 4 - width) / 2 - 1, y - 40, health / 4, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y - 32, width, height - 32);
	}
	
	public Rectangle getBottomBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
}
