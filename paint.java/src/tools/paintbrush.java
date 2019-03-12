package tools;

import editor.layer;
import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;

public class paintbrush  extends tool{

	int size=10;
	PApplet can;
	
	
	public paintbrush(PApplet c) {
		can=c;
		icon=c.createImage(20, 20,PConstants.RGB);
	}
	
	@Override
	public layer click(int x, int y, layer l) {
		l.getImage().beginDraw();
		
		l.getImage().strokeWeight(0);
		l.getImage().ellipse(x, y, size, size);
		l.getImage().endDraw();
		return l;
	}

	@Override
	public layer drag(int x, int y, int xdif, int ydif, layer l) {
		l.getImage().beginDraw();
		l.getImage().fill(globals.getInstance().selectedColor);
		l.getImage().strokeWeight(size);
		colorSelect(l);
		l.getImage().line(x, y, xdif,ydif);
		l.getImage().endDraw();
		return l;
	}

}
