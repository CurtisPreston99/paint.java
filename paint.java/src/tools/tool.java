package tools;

import editor.layer;
import processing.core.PImage;

public abstract class tool {
	PImage icon;
	
	
	
	abstract public layer click(int x,int y,layer l);
	
	abstract public layer drag(int x,int y,int xdif,int ydif,layer l);
	
	public PImage getIcon() {
		return icon;
	}
	
//	abstract controlP5 getcontrols();
	
	
}
