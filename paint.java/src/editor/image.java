package editor;

import java.util.ArrayList;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class image {
	public ArrayList<layer> layers=new ArrayList<layer>();
	int width;
	int height;
	public int selectedLayer=0;
	Boolean lck=false;
	
	public image(int w,int h){
//		layer one = new layer(w,h);
		width=w;
		height=h;
		layer first=new layer(w, h);
		first.setLayerName("layer 0");
		layers.add(first);
		globals.getInstance().selectedlayer=layers.get(0);
	}
	
	public image(image cp){
		
		width=cp.width;
		height=cp.height;
		selectedLayer=cp.selectedLayer;
		for(layer l:cp.layers) {
			layers.add(l.clone());
		}
	}
	
	
	
	public void resize(int w, int h) {	
		for(layer l:layers) {
			l.resize(w, h);
		}
	}
	
	public void addLayer() {
		layer l = new layer(width,height);
		//make layer transparent rather than white
		l.getImage().beginDraw();
		l.getImage().background(0,0);
		l.getImage().endDraw();
		layers.add(l);
	}
	
	public void addLayer(layer l) {
		layers.add(l);
	}
	
	
	public PGraphics getPic(PApplet c) {
		PGraphics pic= globals.getInstance().window.createGraphics(width, height);
		pic.beginDraw();
		for(layer l:layers) {
			pic.image(l.image, 0, 0);
		}
		pic.endDraw();
		return pic;
	}
	
	public image copy() {
		image i = new image(width,height);
		
//		for(layer l:layers) {
		for(int e=0;e<layers.size();e++) {
			i.addLayer();
			layer tmp= i.getLayer(e);
			tmp.image.beginDraw();
			tmp.image.image(getLayer(e).image,0,0);
			tmp.image.endDraw();
			i.layers.set(e, tmp);
		}
		return i;
	}
	
	public void updateLayer(layer l,int i) {
		if(i>layers.size()-1) {
			layers.add(l);
		}else {
		layers.set(i, l);
		}
	}
	
	public layer getLayer() {
		
		return layers.get(selectedLayer);
	}
	
	public layer getLayer(int i) {
		
		return layers.get(i);
	}
	
	public void updateLayer(layer l) {
		layers.set(selectedLayer, l);
		
	}
	
	public void updateLayer(int i,layer l) {
		layers.set(i, l);
		
	}
	//wait while image array is locked
	public void lockWait() {
		while(lck) {}
	}
	//lock/unlock layers
	public void lock() {
		lck=true;
	}
	public void unlock() {
		lck=false;
	}
}
