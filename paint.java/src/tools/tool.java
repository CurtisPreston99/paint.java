package tools;

import editor.image;
import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class tool {
	PImage icon;
	int color;
	PApplet can;
	String name;
	public tool(PApplet c) {
		can=c;
		icon=c.createImage(16, 16,PConstants.RGB);
	}
	
	public int getIndex(int width,int x,int y) {
		return width * y + x;
	}
	
	abstract public void click(int x,int y,PGraphics drawinglayer);
	
	abstract public void drag(int x,int y,int xdif,int ydif,PGraphics drawinglayer);
	
	public void finnish() {
		image i=globals.getInstance().selectedImg;
		i.updateLayer(globals.getInstance().drawinglayer);
	};
	
	public PImage getIcon() {
		return icon;
	}
	
	public void colorSelect(PGraphics L) {
		if(globals.getInstance().mouse==1) {
		L.stroke(globals.getInstance().secondaryColor);
		L.fill(globals.getInstance().secondaryColor);
		}else {
		L.stroke(globals.getInstance().selectedColor);
		L.fill(globals.getInstance().selectedColor);
		}
	}


	
//	abstract controlP5 getcontrols();
	
	
}
