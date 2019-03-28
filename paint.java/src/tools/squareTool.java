package tools;

import processing.core.PApplet;
import processing.core.PGraphics;

public class squareTool extends tool {
	public squareTool(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
		name = "square tool";
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
		drawinglayer.rect(sx, sy, xdif-sx, ydif-sy);
		drawinglayer.endDraw();
	}

}
