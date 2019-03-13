package layerWindow;

import java.util.ArrayList;

import colorSelect.colorWheel;
import controlP5.CColor;
import controlP5.ColorWheel;
import controlP5.ControlP5;
import editor.layer;
import main.SecondaryWindows;
import main.globals;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.MouseEvent;

public class layerSelector extends SecondaryWindows{
	int background = color(255,255,255);
	int selected=color(100,100,255);
	ControlP5 cp5;
	int scroll=0;
	public void settings() {
		size(128,128);
	}
	
	
	public void setup() {
//		surface.setIcon(null);
		cp5=new ControlP5(this);

		cp5.addButton("addlayer")
		.setPosition(width/2,2)
		.setSize(20, 20)
		.setColorCaptionLabel(color(150))
		.setColorLabel(color(0))
		.setColorBackground(color(150))
		.setLabel("+")
		.setValueLabel("+");
	}
	
	//draws list
	public void draw() {
		background(background);
		onTopCheck();
		ArrayList<layer> layers;
		layers=globals.getInstance().selectedImage.current.layers;
		layer[] shown= new layer[3];
		//gets shown layers
		for(int i=scroll;i<scroll+3;i++) {
			if(i>=layers.size()) {
				break;
			}
			shown[i-scroll]=layers.get(i);
		}
		//draw the list items
		for (int l=0;l<shown.length;l++) {
			if(shown[l]==null) {
				break;
			}
			if(l+scroll==globals.getInstance().selectedlayerN) {
				int backTmp=background;
				background = selected;
				image(layerButton(shown[l]),0,(l*42)+20);
				background=backTmp;
			}else {
				image(layerButton(shown[l]),0,(l*42)+20);
			}
		}
		
		background = color(255,255,255);
	}
	
	//converts layer to the a PGraphic
	public PGraphics layerButton(layer l) {
		//the graphic
		PGraphics r = createGraphics(width, 42);
		
		//resize dimensions based on size of window
		int imageW=l.getImage().width;
		int imageH=l.getImage().height;
		int SimageH=r.height-4;
		float SizeRatio=imageH/SimageH;
		int SimageW=(int) (imageW/SizeRatio);
		
		//drawing the layer to the image
		r.beginDraw();
		r.background(background);
		r.image(l.getImage(),2,2,SimageW, SimageH);
//		//draw box around image
		r.noFill();
		r.rect(2, 2, SimageW, SimageH);
//		//draw layer title
		r.textSize(12);
		r.fill(0);
		r.textAlign(LEFT,RIGHT);
		r.text(l.getLayerName(),r.width/2,r.height/2);
		
		if(l.getVisible()) {
			r.fill(0,0,255);
		}else {
			r.noFill();			
		}
		r.rect(r.width-20,(r.height/2)-5,10,10);
		//draw top divider line 
		r.line(0,0,width,0);
		r.endDraw();
		return r;
	}
	
	public void addlayer(int i) {
		globals.getInstance().selectedImage.current.lockWait();
		globals.getInstance().selectedImage.current.lock();
		globals.getInstance().selectedImage.addlayer();
		globals.getInstance().selectedImage.current.unlock();
		globals.getInstance().selectedImage.next();
	}
	
	public void mousePressed() {
		
		//selects new selected layer and updates globals
		if(mouseY>20){
			int y=((mouseY-20)/42)+scroll;
			if(mouseButton == LEFT) {
				updateLayerSelect(y);
			}
			if(mouseButton == RIGHT) {
				
			}
		}
	}
	
	public void mouseWheel(MouseEvent event) {
		  float e = event.getCount();
//		  println(e);
		  if(scroll+e>0 && scroll+e<globals.getInstance().selectedImage.current.layers.size()) {
			  scroll+=e;
		  }
		}
	
	//update global 
	public void updateLayerSelect(int i) {
		globals.getInstance().selectedlayerN=i;
		globals.getInstance().selectedlayer=globals.getInstance().selectedImage.getSelectedImage().getLayer(i);
		
	}
	
	
}
