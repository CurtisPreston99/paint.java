package tools;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class colorDif extends tool {

	public colorDif(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
		name="colorDif";
	}

	@Override
	public void click(int x, int y, PGraphics drawinglayer) {
		int selected=globals.getInstance().selectedColor;
		int check=globals.getInstance().selectedlayer.getImage().get(x,y);
		System.out.println(colorDif(selected, check, true));
	}

	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics drawinglayer) {
		// TODO Auto-generated method stub

	}

}
