package tools;

import editor.layer;
import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class paintbrush  extends tool{

	public paintbrush(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	int size=10;
	
	
	@Override
	public void click(int x, int y, PGraphics l) { 
		l.beginDraw();
		
		l.strokeWeight(0);
		l.ellipse(x, y, size, size);
		l.endDraw();
		
	}

	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics l) {
		l.beginDraw();
		l.fill(globals.getInstance().selectedColor);
		l.strokeWeight(size);
		colorSelect(l);
		l.line(x, y, xdif,ydif);
		l.endDraw();
	}



}
