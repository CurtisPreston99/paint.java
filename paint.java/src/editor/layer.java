package editor;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class layer {
	int opacity=100;
	

	volatile PGraphics image;
	globals global=globals.getInstance();
	String layerName="";
	Boolean visible=true;
	
	

	public int getOpacity() {
		return opacity;
	}


	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	
	public Boolean getVisible() {
		return visible;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


	public String getLayerName() {
		return layerName;
	}


	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}


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
	protected layer clone() {//make new layer and copy image from this layer to that one and then return the new layer
		image.flush();
		image.loadPixels();
		layer cl=new layer(image.width,image.height);
		cl.setLayerName(getLayerName());
		PGraphics pg = global.window.createGraphics(image.width, image.height);
		cl.setVisible(getVisible());
		cl.getImage().loadPixels();
		PApplet.arrayCopy(getImage().pixels, cl.getImage().pixels);
		cl.getImage().updatePixels();
		cl.setOpacity(getOpacity());
		return cl;
		
	}


	public PGraphics getImage() {
		return image;
	}
}
