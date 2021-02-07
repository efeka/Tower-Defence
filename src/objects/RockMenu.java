package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import window.GameMain;
import window.Handler;

public class RockMenu extends GameObject {
	
	private Texture tex = GameMain.getTexture();
	private Handler handler;
	
	private int disableTimer = 0, disableCooldown = 15;
	public boolean isOpen = false;
	
	private Rock rock;

	public RockMenu(int x, int y, Rock rock, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.rock = rock;
		width = 199;
		height = 58;
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
		
		if (MouseInput.leftPressed && getYesBounds().contains(MouseInput.x, MouseInput.y)) {  
			isOpen = false;
			handler.addObject(new TowerSpace(rock.getX(), rock.getY(), handler, ObjectId.TowerSpace), Handler.MIDDLE_LAYER);
			handler.removeObject(rock);
		}
		if (MouseInput.leftPressed && getNoBounds().contains(MouseInput.x, MouseInput.y))  
			isOpen = false;
	}

	public void render(Graphics g) {
		if (!isOpen)
			return;
		
		int mx = MouseInput.x;
		int my = MouseInput.y;
		if (getYesBounds().contains(mx, my))
			g.drawImage(tex.rockMenu[1], x, y, width, height, null);
		else if (getNoBounds().contains(mx, my))
			g.drawImage(tex.rockMenu[2], x, y, width, height, null);
		else
			g.drawImage(tex.rockMenu[0], x, y, width, height, null);
	}

	public Rectangle getBounds() {	
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getYesBounds() {
		return new Rectangle(x + 60, y + 27, 36, 29);
	}
	
	public Rectangle getNoBounds() {
		return new Rectangle(x + 100, y + 27, 36, 29);
	}

}
