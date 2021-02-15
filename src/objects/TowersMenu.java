package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import window.Handler;

public class TowersMenu extends GameObject {

	private TowerSpace tower;
	private Handler handler;

	private int disableTimer = 0, disableCooldown = 15;
	public boolean isOpen = false;

	public TowersMenu(int x, int y, TowerSpace tower, Handler handler, ObjectId id) {
		super(x, y, id);
		this.tower = tower;
		this.handler = handler;
		width = height = 80;
	}

	public void tick() {
		if (!isOpen)
			return;
		if (disableTimer < disableCooldown) 
			disableTimer++;
		else {
			if (MouseInput.leftPressed && !getBounds().contains(MouseInput.x, MouseInput.y)) { 
				disableTimer = 0;
				isOpen = false;
			}
		}
		
		if (tower.isEmpty && MouseInput.leftPressed && getBoundsTower1().contains(MouseInput.x, MouseInput.y)) {
			tower.isEmpty = false;
			tower.towerType = tower.TOWER1;
			handler.addObject(new BasicTower(tower.getX(), tower.getY(), handler, ObjectId.BasicTower), Handler.MIDDLE_LAYER);
			handler.removeObject(this);
			isOpen = false;
		}
		else if (tower.isEmpty && MouseInput.leftPressed && getBoundsTower2().contains(MouseInput.x, MouseInput.y)) {
			tower.isEmpty = false;
			tower.towerType = tower.TOWER2;
			handler.addObject(new PlusShooterTower(tower.getX(), tower.getY(), handler, ObjectId.PlusShooterTower), Handler.MIDDLE_LAYER);
			handler.removeObject(this);
			isOpen = false;
		}
		else if (tower.isEmpty && MouseInput.leftPressed && getBoundsTower3().contains(MouseInput.x, MouseInput.y)) {
			tower.isEmpty = false;
			tower.towerType = tower.TOWER3;
			handler.addObject(new BallistaTower(tower.getX(), tower.getY(), handler, ObjectId.BallistaTower), Handler.MIDDLE_LAYER);
			handler.removeObject(this);
			isOpen = false;
		}
		else if (tower.isEmpty && MouseInput.leftPressed && getBoundsTower4().contains(MouseInput.x, MouseInput.y)) {
			tower.isEmpty = false;
			tower.towerType = tower.TOWER4;
			handler.addObject(new MortarTower(tower.getX(), tower.getY(), handler, ObjectId.MortarTower), Handler.MIDDLE_LAYER);
			handler.removeObject(this);
			isOpen = false;
		}

	}

	public void render(Graphics g) {
		if (!isOpen)
			return;
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.pink);
		g2d.fill(getBoundsTower1());
		g2d.setColor(Color.orange);
		g2d.fill(getBoundsTower2());
		g2d.setColor(Color.green);
		g2d.fill(getBoundsTower3());
		g2d.setColor(Color.yellow);
		g2d.fill(getBoundsTower4());
	}

	public Rectangle getBounds() {	
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getBoundsTower1() {
		return new Rectangle(x + 5, y + 5, 32, 32);
	}

	public Rectangle getBoundsTower2() {
		return new Rectangle(x + 32 + 10, y + 5, 32, 32);
	}

	public Rectangle getBoundsTower3() {
		return new Rectangle(x + 5, y + 32 + 10, 32, 32);
	}

	public Rectangle getBoundsTower4() {
		return new Rectangle(x + 32 + 10, y + 32 + 10, 32, 32);
	}

}
