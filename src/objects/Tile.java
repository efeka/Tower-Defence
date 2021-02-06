package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;

public class Tile extends GameObject {
	
	private int type;
	private Texture tex = GameMain.getTexture();

	public Tile(int x, int y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		width = height = 32;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.grassFloor[type], x, y, width, height, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}
