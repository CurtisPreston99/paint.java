package main;

import processing.core.PApplet;

public class SecondaryWindows extends PApplet {
	
	
	public Boolean onTopCheck() {
		surface.setAlwaysOnTop(globals.getInstance().SecondaryOnTop);
		if(globals.getInstance().SecondaryOnTop) {
			frameRate(60);
		}else {
			frameRate(1);
		}
		return globals.getInstance().SecondaryOnTop;
	}
	
	
	public void exit()
	  {
	    dispose();
	  }
}
