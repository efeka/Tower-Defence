package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import effects.CoinEarnedAnimation;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GameMain;
import window.Handler;

public class BasicEnemy extends GameObject {

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

	private Animation walkAnim;
	private Animation walkLeftAnim;
	private Animation walkUpAnim;

	public BasicEnemy(int x, int y, int initialDirection, int priority, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.priority = priority;
		width = height = 32;
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

		walkAnim = new Animation(4, tex.basicEnemy[0], tex.basicEnemy[1], tex.basicEnemy[2], tex.basicEnemy[3], tex.basicEnemy[2], tex.basicEnemy[1], tex.basicEnemy[0], tex.basicEnemy[0], tex.basicEnemy[1], tex.basicEnemy[2], tex.basicEnemy[3], tex.basicEnemy[2], tex.basicEnemy[1], tex.basicEnemy[0], tex.basicEnemy[0], tex.basicEnemy[1], tex.basicEnemy[2], tex.basicEnemy[3], tex.basicEnemy[2], tex.basicEnemy[1]);
		walkLeftAnim = new Animation(4, tex.basicEnemy[4], tex.basicEnemy[5], tex.basicEnemy[6], tex.basicEnemy[7], tex.basicEnemy[6], tex.basicEnemy[5], tex.basicEnemy[4], tex.basicEnemy[4], tex.basicEnemy[5], tex.basicEnemy[6], tex.basicEnemy[7], tex.basicEnemy[6], tex.basicEnemy[5], tex.basicEnemy[4], tex.basicEnemy[4], tex.basicEnemy[5], tex.basicEnemy[6], tex.basicEnemy[7], tex.basicEnemy[6], tex.basicEnemy[5]);
		walkUpAnim = new Animation(4, tex.basicEnemy[8], tex.basicEnemy[9], tex.basicEnemy[10], tex.basicEnemy[11], tex.basicEnemy[10], tex.basicEnemy[9], tex.basicEnemy[8], tex.basicEnemy[8], tex.basicEnemy[9], tex.basicEnemy[10], tex.basicEnemy[11], tex.basicEnemy[10], tex.basicEnemy[9], tex.basicEnemy[8], tex.basicEnemy[8], tex.basicEnemy[9], tex.basicEnemy[10], tex.basicEnemy[11], tex.basicEnemy[10], tex.basicEnemy[9]);
	}

	public void tick() {
		if (health <= 0) {
			handler.removeObject(this);
			handler.enemies.remove(this);
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
		walkAnim.runAnimation();
		walkLeftAnim.runAnimation();
		walkUpAnim.runAnimation();
	}

	private void collision() {
		Rectangle window = new Rectangle(0, 0, GameMain.WIDTH, GameMain.HEIGHT);
		if (!getBounds().intersects(window)) {
			handler.removeObject(this);
			handler.enemies.remove(this);
		}

		for (int i = 0; i < handler.layer1.size(); i++) {
			GameObject tempObject = handler.layer1.get(i);
			if (tempObject.getId() == ObjectId.PathingHelper) {
				if (getBounds().intersects(tempObject.getBounds())) {
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
							y = tempObject.getY() - height;
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
							y = tempObject.getY() - height;
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
				walkAnim.drawAnimation(g, x, y, width, height);
			else
				walkLeftAnim.drawAnimation(g, x, y, width, height);
		}
		else
			walkUpAnim.drawAnimation(g, x, y, width, height);

		g.setColor(Color.red);
		g.fillRect(x - (maxHealth / 4 - width) / 2 - 1, y - 8, maxHealth / 4, 4);
		g.setColor(Color.green);
		g.fillRect(x - (maxHealth / 4 - width) / 2 - 1, y - 8, health / 4, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
