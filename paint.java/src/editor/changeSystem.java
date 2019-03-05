package editor;

import java.util.ArrayList;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class changeSystem {
	public ArrayList<image> changes=new ArrayList<>();
	public ArrayList<image> next=new ArrayList<>();
	int height,width;
	int selected=0;
	public changeSystem(int w,int h) {
		height=h;
		width=w;
		image i=new image(w,h);
		changes.add(i);
		globals.getInstance().selectedImg=i;
	}
	
	
	public void updateGlobal() {
//		globals.getInstance().selectedlayer=changes.get(selected).layers.get(0);
		globals.getInstance().selectedImg=changes.get(selected);
	}
	
	
	public void next() {

		image t=changes.get(selected).copy();
		changes.add(t);
		selected++;
		updateGlobal();
	}
	
	public void undo() {
		selected--;
		if(selected<0) {
			selected=0;
		}
		updateGlobal();
	}
	
	public void redo() {
		selected++;
		if(selected>changes.size()-1) {
			selected=changes.size()-1;
		}
		updateGlobal();
	}
	
	public image getSelectedImage() {
		return changes.get(selected);
		
	}
	
	public PGraphics getPic(PApplet c) {
		return changes.get(selected).getPic(c);
	}
	
	

}
