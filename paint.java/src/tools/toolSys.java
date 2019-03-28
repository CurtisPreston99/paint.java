package tools;

import editor.layer;
import main.globals;
import processing.core.PGraphics;

public class toolSys {
	static toolSys instance=new toolSys();
	public tool selected;
	Boolean working=false;
	int prevX,prevY;
	public static toolSys getSystem() {
		return instance;
	}
	
	public void useTool(int x,int y,layer l) {
		working=true;
		selected.click(x,y,globals.getInstance().drawinglayer);
		working=false;
	}
	
	public void useTool(int x,int y) {
		working=true;
		//get selected layer
//		PGraphics sl=globals.getInstance().selectedImg.getLayer();
//		globals.getInstance().selectedImg.=selected.click(x,y,globals.getInstance().selectedlayer);
//		System.out.println("memes");
		//draw onto layer
		selected.click(x,y,globals.getInstance().drawinglayer);
		//update layer									at the layer in list			   the layer
//		globals.getInstance().selectedImg.updateLayer(globals.getInstance().selectedlayerN,sl);
//		globals.getInstance().selectedImage.getSelectedImage().updateLayer(globals.getInstance().selectedlayer, globals.getInstance().selectedlayerN);
		//set previous x/y
		prevX=x;
		prevY=y;
		working=false;
	
	}
	
	public void dragTool(int x,int y) {
		working=true;
		selected.drag(prevX,prevY,x,y,globals.getInstance().drawinglayer);
//		System.out.println("memes");
		globals.getInstance().selectedImage.getSelectedImage().updateLayer(globals.getInstance().selectedlayer, globals.getInstance().selectedlayerN);
		prevX=x;
		prevY=y;
		working=false;
	}
	
	
	public void finnish() {
		while(working);
		selected.finnish();
	}
	
	public void update() {
		globals.getInstance().selectedlayer=globals.getInstance().selectedlayer;
	}
	
}
