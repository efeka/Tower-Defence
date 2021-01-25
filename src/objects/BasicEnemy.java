package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class BasicEnemy extends GameObject {
	
	public static final int MOVE_UP = 0;
	public static final int MOVE_DOWN = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_RIGHT = 3;

	private Handler handler;
	
	private int velocity = 1;
	
	public BasicEnemy(int x, int y, int initialDirection, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
		
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
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.layer2.size(); i++) {
			GameObject tempObject = handler.layer2.get(i);
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
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
