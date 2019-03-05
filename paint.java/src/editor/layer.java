package editor;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class layer {
	int opacity=100;
	PGraphics image;
	globals global=globals.getInstance();
	
	public layer(int w,int h) {
//		PApplet p = global.window;
		image= global.window.createGraphics(w, h);
		image.beginDraw();
		image.background(255);
		image.endDraw();
//		image=(PGraphics) p.loadImage(p.dataPath("")+"/color_wheel.png");
	}
	
	
	public layer(PGraphics img) {
		
		
		image= global.window.createGraphics(img.width, img.height);
		
		image.beginDraw();
		image.image(img, 0, 0);
		image.endDraw();
	}
	
	
	
	public void resize(int w,int h) {
		image.resize(w, h);
	}
	
	
	@Override
	protected layer clone() {
		image.flush();
		image.loadPixels();
		layer cl=new layer(image.width,image.height);
		cl.image.loadPixels(); 
		cl.image.image(image, 0, 0);
		cl.image.updatePixels();
		return cl;
	}


	public PGraphics getImage() {
		return image;
	}
}
