package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;

public class Tile extends GameObject {
	
	private int type;

	public Tile(int x, int y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		width = height = 32;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		switch(type) {
		case 0:
			g.setColor(new Color(143, 203, 98));
			break;
		case 1:
			g.setColor(new Color(187, 154, 62));
			break;
		}
		g.fillRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return null;
	}

}
