package main;

import processing.core.PApplet;

public class SecondaryWindows extends PApplet {
	
	
	public Boolean onTopCheck() {
		Boolean focus=globals.getInstance().SecondaryOnTop||focused;
		surface.setAlwaysOnTop(focus);
		if(focus) {
			frameRate(60);
		}else {
			frameRate(10);
		}
		return focus;
	}
	
	
	public void exit()
	  {
	    dispose();
	  }
}
