package select;

import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class selectArea {
	PApplet p=globals.getInstance().window;
	PGraphics selected = p.createGraphics(p.width, p.height);
	static selectArea selectedArea = new selectArea();
	
	public PGraphics getSelected(PGraphics l) {
		alternateMask(l,selected);
		return l;
	}
	
	void alternateMask(PGraphics target, PGraphics mask) {
		  target.loadPixels();
		  mask.loadPixels();
		  for (int i = 0; i < mask.pixels.length; i++) {
		    if(mask.pixels[i]!=p.color(0,0,0,0)) {
		    	target.pixels[i]=p.color(0,0,0,0);
		    }
		  }
		  target.updatePixels();
	}
	
	
	
}
