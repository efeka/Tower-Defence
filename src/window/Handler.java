package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;
import objects.PathingHelper;
import objects.Rock;
import objects.Spawner;
import objects.Tile;
import objects.TowerSpace;

public class Handler {

	public BufferedImage level1 = null;

	public static final int BOTTOM_LAYER = 0;
	public static final int MIDDLE_LAYER = 1;
	public static final int TOP_LAYER = 2;

	public ArrayList<GameObject> layer1 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer2 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer3 = new ArrayList<GameObject>();
	
	public ArrayList<GameObject> enemies;
	
	public Handler() {
		enemies = new ArrayList<GameObject>();
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level1.png");
		loadImageLevel(level1);
	}

	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 166 && green == 93 && blue == 53)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.UP, false, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 127 && blue == 127)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.DOWN, false, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 127 && blue == 70)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.LEFT, false, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 74 && blue == 127)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.RIGHT, false, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 205 && green == 93 && blue == 53)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.UP, true, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 70 && green == 124 && blue == 89)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.DOWN, true, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 143 && blue == 105)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.LEFT, true, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 74 && blue == 201)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.RIGHT, true, ObjectId.PathingHelper), MIDDLE_LAYER);
				
				if (red == 255 && green == 255 && blue == 255)
					addObject(new TowerSpace(xx * 32, yy * 32, this, ObjectId.TowerSpace), MIDDLE_LAYER);
				
				if (red == 67 && green == 94 && blue == 219) 
					addObject(new Spawner(xx * 32, yy * 32, Spawner.SPAWN_RIGHT, this, ObjectId.Spawner), TOP_LAYER);
				if (red == 92 && green == 116 && blue == 224) 
					addObject(new Spawner(xx * 32, yy * 32, Spawner.SPAWN_LEFT, this, ObjectId.Spawner), TOP_LAYER);
				if (red == 128 && green == 149 && blue == 242) 
					addObject(new Spawner(xx * 32, yy * 32, Spawner.SPAWN_UP, this, ObjectId.Spawner), TOP_LAYER);
				if (red == 158 && green == 175 && blue == 255) 
					addObject(new Spawner(xx * 32, yy * 32, Spawner.SPAWN_DOWN, this, ObjectId.Spawner), TOP_LAYER);
			
				if (red == 143 && green == 203 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 0, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 0 && green == 140 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 1, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 62 && green == 236 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 2, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 140 && blue == 194)
					addObject(new Tile(xx * 32, yy * 32, 3, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 217 && blue == 140)
					addObject(new Tile(xx * 32, yy * 32, 4, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 190 && blue == 201)
					addObject(new Tile(xx * 32, yy * 32, 5, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 140 && blue == 201)
					addObject(new Tile(xx * 32, yy * 32, 6, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 217 && blue == 201)
					addObject(new Tile(xx * 32, yy * 32, 7, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 140 && blue == 58)
					addObject(new Tile(xx * 32, yy * 32, 8, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 0 && green == 205 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 9, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 143 && green == 236 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 10, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 70 && green == 140 && blue == 244)
					addObject(new Tile(xx * 32, yy * 32, 11, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 31 && green == 255 && blue == 0)
					addObject(new Tile(xx * 32, yy * 32, 12, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 194 && green == 140 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 13, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 194 && green == 190 && blue == 98)
					addObject(new Tile(xx * 32, yy * 32, 14, ObjectId.Tile), BOTTOM_LAYER);
				
				if (red == 160 && green == 171 && blue == 177)
					addObject(new Rock(xx * 32, yy * 32, this, ObjectId.Rock), MIDDLE_LAYER);
			}
		}
	}

	public void tick() {
		for (int i = 0; i < layer2.size(); i++) 
			layer2.get(i).tick();
		for (int i = 0; i < layer1.size(); i++) 
			layer1.get(i).tick();
		for (int i = 0; i < layer3.size(); i++) 
			layer3.get(i).tick();
	}

	public void render(Graphics g) {
		for (int i = 0; i < layer1.size(); i++) 
			layer1.get(i).render(g);
		for (int i = 0; i < layer2.size(); i++) 
			layer2.get(i).render(g);
		for (int i = 0; i < layer3.size(); i++) 
			layer3.get(i).render(g);
	}

	public void addObject(GameObject object, int layer) {
		switch(layer) {
		case BOTTOM_LAYER:
			layer1.add(object);
			break;
		case MIDDLE_LAYER:
			layer2.add(object);
			break;
		case TOP_LAYER:
			layer3.add(object);
			break;
		}
	}

	public void removeObject(GameObject object) {
		if (layer2.contains(object)) {
			layer2.remove(object);
			return;
		}

		if (layer1.contains(object)) {
			layer1.remove(object);
			return;
		}

		if (layer3.contains(object)) {
			layer3.remove(object);
			return;
		}
	}

}
