package editor;

import java.util.ArrayList;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class image {
	volatile public ArrayList<layer> layers=new ArrayList<layer>();
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
	
	public image(PGraphics p){
		
		width=p.width;
		height=p.height;
		layer l=new layer(p);
		layers.add(l);
		globals.getInstance().selectedlayer=l;
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
		while(lck) {}
		lck=true;
		for(layer l:layers) {
			l.resize(w, h);
		}
		lck=false;
	}
	
	public void addLayer() {
		while(lck) {}
		lck=true;
		layer l = new layer(width,height);
		l.setLayerName("layer "+String.valueOf(layers.size()));
		//make layer transparent rather than white
		l.getImage().beginDraw();
		l.getImage().background(0,0);
		l.getImage().endDraw();
		layers.add(l);
		
		lck=false;
	}
	
	public void addLayer(layer l) {
		while(lck) {}
		lck=true;
		layers.add(l);
		lck=false;
	}
	
	
	public PGraphics getPic(PApplet c) {
		PGraphics pic= globals.getInstance().window.createGraphics(width, height);
		pic.beginDraw();
		for(int e=0;e<layers.size();e++) {
			if(layers.get(e).visible) {
				pic.tint(255, (255*layers.get(e).getOpacity())/100); 
				pic.image(layers.get(e).image, 0, 0);
			}
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
		while(lck) {}
		lck=true;

		if(i>layers.size()-1) {
			layers.add(l);
		}else {
		layers.set(i, l);
		}
		lck=false;
	}
	
	public layer getLayer() {
		return layers.get(selectedLayer);
		
	}
	
	public layer getLayer(int i) {
		
		return layers.get(i);
	}
	
	public void updateLayer(layer l) {
		while(lck) {}
		lck=true;
		layers.set(selectedLayer, l);
		lck=false;

		
	}
	
	public void updateLayer(int i,layer l) {
		while(lck) {}
		lck=true;
		layers.set(i, l);
		lck=false;

		
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
