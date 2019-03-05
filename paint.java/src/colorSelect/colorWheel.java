package colorSelect;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class colorWheel {
	PImage wheel;
	PApplet canvas;
	int size=170;
	public colorWheel(PApplet c,int s) {
		canvas=c;
		size=s;
		wheel=canvas.loadImage(canvas.dataPath("")+"/color_wheel.png");
	}
	//gets color at an x and why
	public int getColor(int x,int y) {
		PImage scaled=wheel;
		scaled.loadPixels();
		int c=scaled.pixels[(x*(wheel.width/size))+((y*(wheel.height/size))*scaled.width)];
		
		scaled.updatePixels();
//		System.out.println((x*size)+((y*size)*(wheel.width/size)));
//		System.out.println(x);
//		System.out.println(y);
		return c;
	}
	
	public Boolean inCircle(int x,int y,int Px, int Py) {
//		System.out.println(PApplet.dist(x, y, Px+size/2, Py+size/2));
//		System.out.println(size/2);
		if(PApplet.abs(PApplet.dist(x, y, Px+size/2, Py+size/2))<size/2) {
			return true;
		}
		return false;
	}
	
	//returns {x,y} of a
	public int[] getColorPos(int color) {
		if(color==-1 || color == -16777216) {//if color is white or black make it the middle
			return new int[]{size/2,size/2};
		}
		
		
		globals global=globals.getInstance();
		
		PImage wheels = getWheel();
		wheels = wheel;
		wheels.loadPixels();
		int c=wheels.pixels[0];
		int[] cords= {0,0};
		//go through the image to find the color or closest to it
		for(int x=0;x<wheels.width;x++) {
			for(int y=0;y<wheels.height;y++) {
				//only if the x and y are in the color wheel circle
				if(canvas.dist(x, y, wheels.width/2, wheels.height/2)<wheels.height/2) {
				int cu=wheels.pixels[y*wheels.width+x];//get color
				
				if(global.colorDistance(cu,color)==0) {//if the current color is the same as the input color
					c=cu;
					cords[0]=x/(wheel.width/size);
					cords[1]=y/(wheel.height/size);
					return cords;
				}else {//if its a closer match than the last closest
					if(Math.abs(global.colorDistance(cu,color))<Math.abs(global.colorDistance(c,color))) {
						cords[0]=x/(wheel.width/size);
						cords[1]=y/(wheel.height/size);
						c=cu;
					}
					
				}
				
				}
			}
		}
		wheels.updatePixels();

		return cords;
	}
	
	
	//returns scaled wheel
	public PImage getWheel() {
		PImage w=wheel.copy();
		w.resize(size, size);
		return w;
		
	}
	
}
