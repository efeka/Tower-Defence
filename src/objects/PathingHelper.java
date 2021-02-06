package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;

public class PathingHelper extends GameObject {
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private boolean mirrored;
	
	private Texture tex = GameMain.getTexture();

	public PathingHelper(int x, int y, int direction, boolean mirrored, ObjectId id) {
		super(x, y, id);
		this.mirrored = mirrored;
		utility = direction;
		width = height = 32;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (!mirrored) {
			switch(utility) {
			case UP:
				g.drawImage(tex.grassFloor[2], x, y, width, height, null);
				break;
			case DOWN:
				g.drawImage(tex.grassFloor[2], x, y, width, height, null);
				break;
			case LEFT:
				g.drawImage(tex.grassFloor[0], x, y, width, height, null);
				break;
			case RIGHT:
				g.drawImage(tex.grassFloor[0], x, y, width, height, null);
				break;
			}
		}
		else {
			switch(utility) {
			case UP:
				g.drawImage(tex.grassFloor[3], x, y, width, height, null);
				break;
			case DOWN:
				g.drawImage(tex.grassFloor[3], x, y, width, height, null);
				break;
			case LEFT:
				g.drawImage(tex.grassFloor[1], x, y, width, height, null);
				break;
			case RIGHT:
				g.drawImage(tex.grassFloor[1], x, y, width, height, null);
				break;
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
