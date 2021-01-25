package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;

public class PathingHelper extends GameObject {
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public PathingHelper(int x, int y, int direction, ObjectId id) {
		super(x, y, id);
		utility = direction;
		width = height = 32;
	}

	public void tick() {
		collision();
	}
	
	private void collision() {
		/*
		 	switch(direction) {
			case MOVE_UP:
				velX = 0;
				velY = -2;
				break;
			case MOVE_DOWN:
				velX = 0;
				velY = 2;
				break;
			case MOVE_LEFT:
				velX = -2;
				velY = 0;
				break;
			case MOVE_RIGHT:
				velX = 2;
				velY = 0;
				break;
			}
		 */
	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
