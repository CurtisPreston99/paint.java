package main;

import colorSelect.colorPlane;
import controlP5.FrameRate;
import processing.core.PApplet;
import tools.toolSys;
import tools.toolWindow;
import processing.core.*;
import editor.changeSystem;
import editor.image;

public class main extends PApplet{
	globals global=globals.getInstance();
	Boolean drag=false;
	changeSystem image;
	
	public static void main(String[] args) {
		PApplet.main("main.main");
		
	}
	
	public void settings(){
		System.out.println(dataPath(""));
		
		size(800,800);
    }

    public void setup(){
    	frameRate(10);
    	global.window=this;
    	String[] args= {"color"};
    	PApplet.runSketch(args, new colorPlane());
    	global.selectedColor=color(255,255,255);
    	global.secondaryColor=color(255,255,255);
		surface.setResizable(true);
    	image=new changeSystem(640,480);
		global.selectedlayerN=0;
		global.selectedImage=image;
    	PApplet.runSketch(args, new toolWindow());
    	
    	

    }

    public void draw(){
//    	System.out.println(frameRate);
    	background(global.selectedColor);
    	fill(global.secondaryColor);
    	ellipse(0,0,300,300);
    	image(globals.getInstance().selectedImg.getPic(this),130,130);
    	
    	if(mousePressed) {
    	if(mouseButton==LEFT) {
    		global.mouse=0;
    	}
    	if(mouseButton==RIGHT) {
        	global.mouse=1;
        }
    	if(!drag) {
    		drag=true;
        	toolSys.getSystem().useTool(mouseX-130, mouseY-130);
    	}
    	if(drag) {
        	toolSys.getSystem().dragTool(mouseX-130, mouseY-130);
    	}
    	
    	}
    }
    
    
    public void mousePressed() {
//    	image=new changeSystem(image);
    	frameRate(250);

    }
    
    public void mouseReleased() {
    	drag=false;
    	image.next();
    	frameRate(10);

    }
    
    
    public void keyPressed(){
    	System.out.println(key);
    	
    	switch (key) {
    	case 'z':
    		image.undo();
    		break;
    		
    	case 'r':
    		image.redo();
    		break;
    	}
    }
}
