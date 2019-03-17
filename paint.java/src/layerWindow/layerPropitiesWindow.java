package layerWindow;

import main.SecondaryWindows;
import main.globals;
import editor.layer;
import controlP5.ControlP5;
import controlP5.Textfield;

public class layerPropitiesWindow extends SecondaryWindows {
	public int layerN;
	layer layer;
	ControlP5 cp5;
	
	
	int opacity;
	String layerName;
	boolean visable;
	
	public void settings() {
		size(200,128);
		layer=globals.getInstance().selectedImage.current.getLayer(layerN);
		opacity=layer.getOpacity();
		layerName=layer.getLayerName();
		visable=layer.getVisible();
	}
	
	public void setup() {
		
		cp5=new ControlP5(this);
		
		
		cp5.addButton("visable")
		.setPosition((width/2)+(width/3),2)
		.setSize(20, 20)
		.setColorCaptionLabel(color(150))
		.setColorLabel(color(0))
		.setColorBackground(color(150))
		.setLabel("+")
		.setValueLabel("+");
		
		cp5.addSlider("opacity")
		.setPosition(10,(height/5)*4)
		.setValue(opacity);
		
		cp5.addTextfield("layerName")
		.setPosition(10,10)
		.setAutoClear(false)
		.setValue(layerName);
		
		
		cp5.addButton("save")
		.setPosition((width-30),(height-30))
		.setSize(20, 20)
		.setColorCaptionLabel(color(150))
		.setColorLabel(color(0))
		.setColorBackground(color(150));
	}
	
	
	public void draw() {
		
		layer=globals.getInstance().selectedImage.current.getLayer(layerN);

		onTopCheck();
		
		int imageW=layer.getImage().width;
		int imageH=layer.getImage().height;
		int SimageH=height-70;
		float SizeRatio=imageH/SimageH;
		int SimageW=(int) (imageW/SizeRatio);
		
		image(layer.getImage(),10,30,SimageW,SimageH);
		

	}
	
	public void visable(int i) {
		visable=!visable;
	}
	
	public void layerName(String i) {
		layerName=i;
		print(i);
	}
	
	public void opacity(int i) {
		opacity=i;
		
	}
	
	public void updateL(int N) {
		layerN=N;
	}
	
	public void save(int i) {
		println(layerN);
		layer = globals.getInstance().selectedImage.current.getLayer(layerN);
		Textfield t=(Textfield) cp5.get("layerName");
		
		layerName=t.getText();
		layer.setLayerName(layerName);
		layer.setOpacity(opacity);
		layer.setVisible(visable);
		
		surface.setVisible(false);
//		dispose();
	}

}
