package framework;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject  {
	
	protected int x, y;
	protected int velX, velY;
	protected int width, height;
	protected ObjectId id;
	protected int utility;
	
	protected int health;
	protected int priority;
	
	public GameObject(int x, int y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public int getPriority() {
		return priority;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public int getUtility() {
		return utility;
	}
	
	public void setX(int x) { 
		this.x = x; 
	}
	
	public void setY(int y) { 
		this.y = y; 
	}
	
	public int getX() { 
		return x; 
	}
	
	public int getY() { 
		return y; 
	}
	
	public int getVelX() {
		return velX;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}