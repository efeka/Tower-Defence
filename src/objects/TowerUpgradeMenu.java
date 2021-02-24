package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class TowerUpgradeMenu extends GameObject {

	private Texture tex = GameMain.getTexture();
	private Handler handler;

	private GameObject tower;
	private int type;

	private int disableTimer = 0, disableCooldown = 15;
	public boolean isOpen = false;

	private int upgradeTimer = 0, upgradeCooldown = 15;

	private int damageLevel = 0, damageLevelMax = 3;
	private int rangeLevel = 0, rangeLevelMax = 3;
	private int attackSpeedLevel = 0, attackSpeedLevelMax = 3;

	public TowerUpgradeMenu(int x, int y, GameObject tower, int type, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.tower = tower;	
		this.type = type;

		width = 177;
		height = 146;
	}

	public void tick() {
		if (isOpen) {
			if (disableTimer < disableCooldown) 
				disableTimer++;
			else {
				if (MouseInput.leftPressed && !getBounds().contains(MouseInput.x, MouseInput.y)) { 
					disableTimer = 0;
					isOpen = false;
				}
			}

			if (upgradeTimer < upgradeCooldown)
				upgradeTimer++;
			else {
				if (GameMenu.coins >= 10 && damageLevel < damageLevelMax && MouseInput.leftPressed && getUpgrade1Bounds().contains(MouseInput.x, MouseInput.y)) {
					upgradeTimer = 0;
					GameMenu.coins -= 10;
					damageLevel++;
					tower.setDamage(tower.getDamage() + 15);
				}
				if (GameMenu.coins >= 10 && attackSpeedLevel < attackSpeedLevelMax && MouseInput.leftPressed && getUpgrade2Bounds().contains(MouseInput.x, MouseInput.y)) {
					upgradeTimer = 0;
					GameMenu.coins -= 10;
					attackSpeedLevel++;
					tower.setAttackSpeed(tower.getAttackSpeed() - 1);
				}
				if (GameMenu.coins >= 10 && rangeLevel < rangeLevelMax && MouseInput.leftPressed && getUpgrade3Bounds().contains(MouseInput.x, MouseInput.y)) {
					upgradeTimer = 0;
					GameMenu.coins -= 10;
					rangeLevel++;
					tower.setRange(tower.getRange() + 2);
				}
				if (MouseInput.leftPressed && getSellBounds().contains(MouseInput.x, MouseInput.y)) {
					((BasicTower) tower).towerSpace.isEmpty = true; 
					((BasicTower) tower).towerSpace.addMenu(); 
					handler.removeObject(tower);
					handler.removeObject(this);
					GameMenu.coins += 15;
				}
			}
		}
	}

	public void render(Graphics g) {
		if (isOpen) {
			g.drawImage(tex.upgradeMenu, x - width / 2 + 16, y - 160, width, height, null);
			
			if (GameMain.state == GameMain.STATE.GAME) {
				int mx = MouseInput.x;
				int my = MouseInput.y;
				Point point = new Point(mx, my);
				if (getSellBounds().contains(point))
					g.drawImage(tex.sellButton[1], x - width / 2 + 23, y - 53, 163, 31, null);
				else
					g.drawImage(tex.sellButton[0], x - width / 2 + 23, y - 53, 163, 31, null);
				
				if (getUpgrade1Bounds().contains(point))
					g.drawImage(tex.priceTag[1], x + 49, y - 148, null);
				else
					g.drawImage(tex.priceTag[0], x + 49, y - 148, null);
				
				if (getUpgrade2Bounds().contains(point))
					g.drawImage(tex.priceTag[1], x + 49, y - 115, null);
				else
					g.drawImage(tex.priceTag[0], x + 49, y - 115, null);
				
				if (getUpgrade3Bounds().contains(point))
					g.drawImage(tex.priceTag[1], x + 49, y - 82, null);
				else
					g.drawImage(tex.priceTag[0], x + 49, y - 82, null);

				g.drawImage(tex.upgradeLevel[damageLevel], x + 12, y - 147, null);
				g.drawImage(tex.upgradeLevel[attackSpeedLevel], x + 12, y - 114, null);
				g.drawImage(tex.upgradeLevel[rangeLevel], x + 12, y - 81, null);
			}

		}
	}

	public Rectangle getBounds() {		
		return new Rectangle(x - width / 2 + 16, y - 160, width, height);
	}

	public Rectangle getSellBounds() {
		return new Rectangle(x - width / 2 + 23, y - 53, 163, 31);
	}

	public Rectangle getUpgrade1Bounds() {
		return new Rectangle(x + 48, y - 149, 44, 23);
	}

	public Rectangle getUpgrade2Bounds() {
		return new Rectangle(x + 48, y - 116, 44, 23);
	}

	public Rectangle getUpgrade3Bounds() {
		return new Rectangle(x + 48, y - 83, 44, 23);
	}

}
