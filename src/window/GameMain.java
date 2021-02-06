package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import objects.GameMenu;

@SuppressWarnings("serial")
public class GameMain extends Canvas implements Runnable {

	private Thread thread;
	public static boolean running = false;

	private MouseInput mouse;
	private Handler handler;
	
	private static Font font;
	private static Texture tex;
	
	private static GameMenu gameMenu;
	
	public static final int WIDTH = 32 * 25, HEIGHT = 32 * 21;
	
	public GameMain() {
		requestFocus();
		tex = new Texture();
		new Window(WIDTH, HEIGHT, "Tower Defence", this);
		gameMenu = new GameMenu(WIDTH / 2 - 32 * 11 / 2, 5, ObjectId.GameMenu);
		handler = new Handler();
		mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static Texture getTexture() {
		return tex;
	}
	
	public static enum STATE{
		MAIN_MENU,
		PAUSED,
		GAME,
		LEVELS
	};
	public static STATE state = STATE.GAME;

	private void tick() {
		try {
			handler.tick();
		} catch(Exception ignored) {}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//---draw begin---

		//background
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		//objects
		try {
			handler.render(g);
			gameMenu.render(g);
		} catch(Exception ignored) {}

		//---draw end---	
		g.dispose();
		bs.show();
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				if (state == STATE.GAME)
					tick();
				try {
					gameMenu.tick();
				} catch(NullPointerException ignored) {}
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(InterruptedException e) {}
	}
	
	public static Font getFont(int points) {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("FiveByFive.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont((float) points);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return font;
	}
	
	public static GameMenu getGameMenu() {
		return gameMenu;
	}

	public static void main(String[] args) {
		new GameMain();	
	}

}
