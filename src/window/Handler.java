package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;
import objects.PathingHelper;
import objects.Spawner;
import objects.Tile;

public class Handler {

	public BufferedImage level1 = null;

	public static final int BOTTOM_LAYER = 0;
	public static final int MIDDLE_LAYER = 1;
	public static final int TOP_LAYER = 2;

	public ArrayList<GameObject> layer1 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer2 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer3 = new ArrayList<GameObject>();

	public Handler() {
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

				if (red == 187 && green == 154 && blue == 62) 
					addObject(new Tile(xx * 32, yy * 32, 1, ObjectId.Tile), BOTTOM_LAYER);
				if (red == 67 && green == 94 && blue == 219) 
					addObject(new Spawner(xx * 32, yy * 32, this, ObjectId.Spawner), TOP_LAYER);
				if (red == 166 && green == 93 && blue == 53)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.UP, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 127 && blue == 127)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.DOWN, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 127 && blue == 70)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.LEFT, ObjectId.PathingHelper), MIDDLE_LAYER);
				if (red == 0 && green == 74 && blue == 127)
					addObject(new PathingHelper(xx * 32, yy * 32, PathingHelper.RIGHT, ObjectId.PathingHelper), MIDDLE_LAYER);
			}
		}
		//addObject(new HUD(0, 0, this, ObjectId.HUD), TOP_LAYER);
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
