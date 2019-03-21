package tools;

import editor.layer;
import main.globals;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class tool {
	PImage icon;
	int color;
	
	
	abstract public void click(int x,int y,PGraphics drawinglayer);
	
	abstract public void drag(int x,int y,int xdif,int ydif,PGraphics drawinglayer);
	
	public layer finnish(layer l) {
		layer newl=l.clone();
		PGraphics p=l.getImage();
		p.beginDraw();
		p.image(globals.getInstance().drawinglayer,0,0);
		p.endDraw();
		newl.setImage(p);
		return newl;
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
