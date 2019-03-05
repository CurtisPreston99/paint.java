package tools;

import editor.layer;
import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;

public class line extends tool {
	PApplet can;
	
	public line(PApplet c) {
		can=c;
		icon=c.createImage(20, 20,PConstants.RGB);
		
	}
	@Override
	
	public layer click(int x, int y,layer l) {
		// TODO Auto-generated method stub
		return l;
	}

	@Override
	public layer drag(int x, int y, int xdif, int ydif,layer l) {
		l.getImage().line(x,y,xdif,ydif);
		return l;
	}

}
