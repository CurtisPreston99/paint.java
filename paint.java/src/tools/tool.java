package tools;

import editor.image;
import editor.layer;
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
	
	float colorDif(int c1, int c2)
	{
	  float rmean =(can.red(c1) + can.red(c2)) / 2;
	  float r = can.red(c1);
	  float g = can.green(c1);
	  float b = can.blue(c1);
	  double d=Math.sqrt(Math.pow(r, 2)+Math.pow(g,2)+Math.pow(b,2));
	  double p=d/Math.sqrt(Math.pow(255, 2)*3);
	  return (float) p*100;
	} // colorDist()
	
	public int getIndex(int width,int x,int y) {
		return width * y + x;
	}
	
	abstract public void click(int x,int y,PGraphics drawinglayer);
	
	abstract public void drag(int x,int y,int xdif,int ydif,PGraphics drawinglayer);
	
	public void finnish() {
		
		image i=globals.getInstance().selectedImg;
		i.lockget();
		PGraphics s=globals.getInstance().drawinglayer;
		PGraphics l=i.getLayer().getImage();
		
		l.beginDraw();
		l.image(s,0,0);
		i.realese();
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
