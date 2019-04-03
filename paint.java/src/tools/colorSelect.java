package tools;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class colorSelect  extends tool{

	public colorSelect(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
		name="color Select";
	}

	@Override
	public void click(int x, int y, PGraphics drawinglayer) {
		int color=globals.getInstance().selectedlayer.getImage().pixels[getIndex(drawinglayer.width, x, y)];
		if(globals.getInstance().mouse==0) {
		globals.getInstance().selectedColor=color;
		}else {
			globals.getInstance().secondaryColor=color;

		}
		
	}

	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics drawinglayer) {
		click(xdif,ydif,drawinglayer);
		
	}

}
