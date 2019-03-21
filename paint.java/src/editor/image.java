package editor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;


public class image{
	
	public ArrayList<layer> layers=new ArrayList<layer>();
	int width;
	int height;
	public int selectedLayer=0;

	Semaphore s = new Semaphore(1);
	
	
	public image(int w,int h){
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
		lockget();
		for(layer l:layers) {
			l.resize(w, h);
		}
		realese();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addLayer() {
		lockget();
		layer l = new layer(width,height);
		l.setLayerName("layer "+String.valueOf(layers.size()));
		//make layer transparent rather than white
		l.getImage().beginDraw();
		l.getImage().background(0,0);
		l.getImage().endDraw();
		layers.add(l);
		realese();
		
	}
	
	public void addLayer(layer l) {
		lockget();
		layers.add(l);
		realese();

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
	
	public PGraphics getPreview(PApplet c) {
		PGraphics pic= globals.getInstance().window.createGraphics(width, height);
		pic.beginDraw();
		for(int e=0;e<layers.size();e++) {
			if(layers.get(e).visible) {
				pic.tint(255, (255*layers.get(e).getOpacity())/100); 
				pic.image(layers.get(e).image, 0, 0);
			}
			if(e==globals.getInstance().selectedlayerN) {
				pic.image(globals.getInstance().drawinglayer, 0, 0);
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
		lockget();

		if(i>layers.size()-1) {
			layers.add(l);
		}else {
		layers.set(i, l);
		}
		realese();
	}
	
	public layer getLayer() {
		return layers.get(selectedLayer);
		
	}
	
	public layer getLayer(int i) {
		
		return layers.get(i);
	}
	
	public void updateLayer(layer l) {
		lockget();
		layers.set(selectedLayer, l);
		realese();
	}
	
	public void updateLayer(int i,layer l) {
		lockget();
		layers.set(i, l);
		realese();
	}
	//wait while image array is locked
	public void lockget() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void realese() {
		s.release();
	}
}
