package tools;

import editor.layer;
import main.globals;
import processing.core.PImage;

public abstract class tool {
	PImage icon;
	int color;
	
	
	abstract public layer click(int x,int y,layer l);
	
	abstract public layer drag(int x,int y,int xdif,int ydif,layer l);
	
	public PImage getIcon() {
		return icon;
	}
	
	public void colorSelect(layer L) {
		if(globals.getInstance().mouse==1) {
		L.getImage().stroke(globals.getInstance().secondaryColor);
		L.getImage().fill(globals.getInstance().secondaryColor);
		}else {
		L.getImage().stroke(globals.getInstance().selectedColor);
		L.getImage().fill(globals.getInstance().selectedColor);
		}
	}
	
//	abstract controlP5 getcontrols();
	
	
}
