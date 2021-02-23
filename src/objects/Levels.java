package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Stack;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Levels extends GameObject {

	private Handler handler;

	private Spawner spawner;
	private int spawnTimer = 0, spawnCooldown = 60;

	private int waitTimer = 0, waitCooldown = 180;
	private boolean canSpawn = true;

	public static int level = 1;
	public static int currentWave = 1, maxWaves = 4;

	private Stack<ObjectId> level1 = new Stack<ObjectId>();

	public Levels(int x, int y, Spawner spawner, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.spawner = spawner;

		for (int i = 0; i < 7; i++)
			level1.push(ObjectId.BasicEnemy);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		for (int i = 0; i < 5; i++)
			level1.push(ObjectId.BasicEnemy);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		for (int i = 0; i < 3; i++)
			level1.push(ObjectId.BasicEnemy);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		level1.push(ObjectId.Empty);
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0)
				level1.push(ObjectId.BasicEnemy);
			else	
				level1.push(ObjectId.FlyingEnemy);
		}
	}

	public void tick() {
		if (!level1.isEmpty()) {
			if (level1.peek() == ObjectId.Empty) {
				waitTimer++;
				if (waitTimer >= waitCooldown) {
					waitTimer = 0;
					level1.pop();
					if (level1.peek() != ObjectId.Empty)
						if (currentWave < maxWaves)
							currentWave++;
				}
			}
			else {
				spawnTimer++;
				if (spawnTimer >= spawnCooldown) {
					spawnTimer = 0;
					spawner.spawnNext(level1.pop());
				}
			}
		}

	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
		return null;
	}

}
