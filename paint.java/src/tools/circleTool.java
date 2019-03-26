package tools;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class circleTool extends tool {
	public circleTool(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	int sx,sy=0;
	@Override
	public void click(int x, int y, PGraphics drawinglayer) {
		sx=x;
		sy=y;

	}

	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics drawinglayer) {
		drawinglayer.beginDraw();
		drawinglayer.background(0,0);
		drawinglayer.ellipseMode(PConstants.CORNER);
		drawinglayer.ellipse(sx, sy, xdif-sx, ydif-sy);
		drawinglayer.endDraw();
	}

}
