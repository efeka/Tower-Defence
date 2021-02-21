package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Spawner extends GameObject {
	
	public static final int SPAWN_UP = 0;
	public static final int SPAWN_DOWN = 1;
	public static final int SPAWN_LEFT = 2;
	public static final int SPAWN_RIGHT = 3;
	private int spawnDirection;
	
	private int spawnTimer = 0, spawnCooldown = 120;
	
	private Handler handler;
	
	private int enemyPriority = 0;
	private Comparator<GameObject> comparator;

	public Spawner(int x, int y, int spawnDirection, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.spawnDirection = spawnDirection;
		width = height = 32;
		comparator = new Comparator<GameObject>() {
			public int compare(GameObject o1, GameObject o2) {
				return o1.getPriority() - o2.getPriority();
			}
		};
	}

	public void tick() {

	}
	
	public void spawnNext(ObjectId object) {
		GameObject enemy;
		switch(object) {
		case BasicEnemy:
			enemy = new BasicEnemy(x, y, spawnDirection, enemyPriority++, handler, ObjectId.BasicEnemy);
			handler.addObject(enemy, Handler.MIDDLE_LAYER);
			handler.enemies.add(enemy);
			handler.enemies.sort(comparator);
			break;
		case FlyingEnemy:
			enemy = new FlyingEnemy(x, y, spawnDirection, enemyPriority++, handler, ObjectId.FlyingEnemy);
			handler.addObject(enemy, Handler.MIDDLE_LAYER);
			handler.flyingEnemies.add(enemy);
			handler.flyingEnemies.sort(comparator);
			break;
		default:
			break;
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
