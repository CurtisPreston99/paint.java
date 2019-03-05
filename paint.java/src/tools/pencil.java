package tools;

import editor.layer;
import processing.core.PApplet;
import processing.core.PConstants;

public class pencil extends tool{

	
	PApplet can;
	
	public pencil(PApplet c) {
		can=c;
		icon=c.createImage(20, 20,PConstants.RGB);
		
		
	}
	
	@Override
	public layer click(int x, int y, layer l) {
		l.getImage().beginDraw();
		l.getImage().point(x, y);
		l.getImage().endDraw();
		return l;
	}

	@Override
	public layer drag(int x, int y, int xdif, int ydif, layer l) {
		// TODO Auto-generated method stub
		return l;
		}

}
