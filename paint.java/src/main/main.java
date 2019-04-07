package main;


import colorSelect.colorPlane;
import processing.core.PApplet;
import processing.core.PGraphics;
import saveSystem.IOSystem;
import tools.toolSys;
import tools.toolWindow;
import editor.changeSystem;
import layerWindow.layerSelector;

public class main extends PApplet{
	globals global=globals.getInstance();
	Boolean drag=false;
	changeSystem image;
	
	public static void main(String[] args) {
		main("main.main");
	}
	
	public void settings(){
		System.out.println(dataPath(""));
		
		size(800,800);
    }

    public void setup(){
    	frameRate(10);
    	global.window=this;
    	String[] args= {"color"};
    	global.selectedColor=color(0,0,0);
    	global.secondaryColor=color(255,255,255);
		surface.setResizable(true);
    	image=new changeSystem(640,480);
		global.selectedlayerN=0;
		global.selectedImage=image;
		PApplet.runSketch(args, new colorPlane());
//		new toolWindow();
    	PApplet.runSketch(args, new toolWindow());    	
    	PApplet.runSketch(args, new layerSelector());
    	

    	globals.getInstance().selectedImage.next();
    	newDrawingLayer();
    	
    }

    public void draw(){
    	
    	surface.setIcon(createGraphics(1, 1));

    	global.SecondaryOnTop=focused;
    	background(0);
//    	System.out.println(frameRate);

    	drawImage();
    	
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
    
    //if mouse pressed ramp up fps for smother drawing 
    public void mousePressed() {
    	frameRate(250);
    }
    
    //turn of drag, start next in change system, slow down fps for battery saving
    public void mouseReleased() {
    	//finish drawing add tmp layer to selected layer 
    	toolSys.getSystem().finnish();
    	global.newDrawingLayer();
    	drag=false;
    	globals.getInstance().selectedImage.next();
    	frameRate(10);
    }
    
    public void drawImage() {
    	PGraphics pic=globals.getInstance().selectedImg.getPreview(this);
    	pic.beginDraw();
//    	pic.image();
    	pic.endDraw();
    	
    	image(pic,130,130);
//    	image();
    }
    
    
    public void newDrawingLayer() {
    	PGraphics p = createGraphics(image.current.getWidth(), image.current.getHeight());
    	p.beginDraw();
    	p.background(0, 0);
    	global.drawinglayer=p;
    	}
    	
    
    
    public void keyPressed(){
    	System.out.println(key);
    	
    	switch (key) {
    	case '':
    		globals.getInstance().selectedImage.undo();
    		break;
    		
    	case '':
    		globals.getInstance().selectedImage.redo();
    		break;
    	
    	case 's':
    		IOSystem.saveFile();
    		break;
    	
    	
    	case 'l':
    		IOSystem.loadFile();
    		break;
    	
    	case '': 
    		key = 0; 
    		break;
      }
    	
    }
  
    
	
}
