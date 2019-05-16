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
		return colorDif(c1,c2,false);
	} // colorDist()
	
	float colorDif(int c1, int c2,boolean error)
	{
	  //color difference using Euclidean distance with RGB being the 3 dimensions 
	  double difRed = can.red(c1)-can.red(c2);
	  double difGreen = can.green(c1)-can.green(c2);
	  double difBlue = can.blue(c1)-can.blue(c2);
	  double TotalDif=Math.sqrt(Math.pow(difRed, 2)+Math.pow(difGreen,2)+Math.pow(difBlue,2));
	  double percentageDifference=(TotalDif/Math.sqrt(Math.pow(255, 2)*3))*100;
	  if(error) {
		  System.out.println(c1);
		  System.out.println(c2);
		  System.out.println(difRed);
		  System.out.println(difGreen);
		  System.out.println(difBlue);
		  System.out.println(TotalDif);
		  System.out.println(percentageDifference);
	  }
	  return (float) percentageDifference;
	}
	
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
		i.updateLayer(l);
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
