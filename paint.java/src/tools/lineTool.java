package tools;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class lineTool extends tool {
	public lineTool(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
		name="line tool";
	}
	
	int width=10;
	
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
		drawinglayer.strokeWeight(width);
		drawinglayer.line(sx, sy, xdif, ydif);
		drawinglayer.endDraw();
	}

}
