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

public class layerSelector extends SecondaryWindows{
	int background = color(255,255,255);
	ControlP5 cp5;
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
	
	
	public void draw() {
		onTopCheck();
		ArrayList<layer> layers = new ArrayList<>();
		layers=globals.getInstance().selectedImage.current.layers;
		
		for (int l=0;l<layers.size();l++) {
			image(layerButton(layers.get(l)),0,(l*42)+20);
			
		}
		
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
		r.image(l.getImage(),2,2,SimageH,SimageW);
		//draw box around image
		r.noFill();
		r.rect(2, 2, SimageH, SimageW);
		
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
		
	}
}
