package editor;

import java.util.ArrayList;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class changeSystem {
	public ArrayList<image> previous=new ArrayList<>();
	public ArrayList<image> next=new ArrayList<>();
	public image current;
	int height,width;
	int selected=0;
	public changeSystem(int w,int h) {
		height=h;
		width=w;
		image i=new image(w,h);
//		previous.add(i);
		current = i;
		globals.getInstance().selectedImg=i;
		updateGlobal();
	}
	
	public changeSystem(PGraphics p) {
		height=p.height;
		width=p.width;
		image i=new image(width,height);
//		previous.add(i);
		current = i;
		globals.getInstance().selectedImg=i;
		updateGlobal();
		
	}
	
	
	public void updateGlobal() {
		globals.getInstance().selectedImg=current;
		globals.getInstance().selectedlayer=current.getLayer(globals.getInstance().selectedlayerN);

	}
	
	//make next image then add current to the previous array
	public void next() {

		image t=new image(current);
		previous.add(current);
		current=t;
		next=new ArrayList<>();
		updateGlobal();
	}
	
	public void undo() {
		if(previous.size()>0) {
			image newc= previous.get(previous.size()-1);
			next.add(current);
			current=newc;
			previous.remove(previous.size()-1);
		}
		updateGlobal();
	}
	
	public void redo() {
		if(next.size()>0) {
			image newc= next.get(next.size()-1);
			previous.add(current);
			current=newc;
			next.remove(next.size()-1);
		}
		
		updateGlobal();
	}
	
	public image getSelectedImage() {
		return current;
		
	}
	
	public PGraphics getPic(PApplet c) {
		return current.getPic(c);
	}


	public void addlayer() {
		current.addLayer();
		next();
		
	}
	
	
	

}
