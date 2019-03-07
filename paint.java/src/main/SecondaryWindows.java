package main;

import processing.core.PApplet;

public class SecondaryWindows extends PApplet {
	
	
	public void onTopCheck() {
		surface.setAlwaysOnTop(globals.getInstance().SecondaryOnTop);
	}
	
	
	public void exit()
	  {
	    dispose();
	  }
}
