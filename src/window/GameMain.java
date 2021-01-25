package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import framework.MouseInput;

@SuppressWarnings("serial")
public class GameMain extends Canvas implements Runnable {

	private Thread thread;
	public static boolean running = false;

	private MouseInput mouse;
	private Handler handler;
	
	public static final int WIDTH = 21 * 32, HEIGHT = 21 * 32;

	public GameMain() {
		requestFocus();
		new Window(WIDTH, HEIGHT, "Tower Defence", this);
		
		handler = new Handler();
		mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

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
		g.setColor(new Color(143, 203, 98));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		//objects
		try {
			handler.render(g);
		} catch(Exception ignored) {}

		//---draw end---	
		g.dispose();
		bs.show();
	}

	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) 
				render();
		}
		stop();
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

	public static void main(String[] args) {
		new GameMain();	
	}

}
