package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Spawner extends GameObject {
	
	private int spawnTimer = 0, spawnCooldown = 90;
	
	private Handler handler;

	public Spawner(int x, int y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		width = height = 32;
	}

	public void tick() {
		spawnTimer++;
		if (spawnTimer >= spawnCooldown) {
			spawnTimer = 0;
			handler.addObject(new BasicEnemy(x, y, BasicEnemy.MOVE_RIGHT, handler, ObjectId.BasicEnemy), Handler.MIDDLE_LAYER);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return null;
	}

}
