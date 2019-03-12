package tools;

import editor.layer;
import main.globals;

public class toolSys {
	static toolSys instance=new toolSys();
	public tool selected;
	
	int prevX,prevY;
	public static toolSys getSystem() {
		return instance;
	}
	
	public void useTool(int x,int y,layer l) {
		selected.click(x,y,l);
		
	}
	
	public void useTool(int x,int y) {
		//get selected layer
		layer sl=globals.getInstance().selectedImg.getLayer();
//		globals.getInstance().selectedImg.=selected.click(x,y,globals.getInstance().selectedlayer);
//		System.out.println("memes");
		//draw onto layer
		sl=selected.click(x,y,sl);
		//update layer									at the layer in list			   the layer
		globals.getInstance().selectedImg.updateLayer(globals.getInstance().selectedlayerN,sl);
//		globals.getInstance().selectedImage.getSelectedImage().updateLayer(globals.getInstance().selectedlayer, globals.getInstance().selectedlayerN);
		//set previous x/y
		prevX=x;
		prevY=y;
				
	
	}
	
	public void dragTool(int x,int y) {
		
		globals.getInstance().selectedlayer=selected.drag(prevX,prevY,x,y,globals.getInstance().selectedlayer);
//		System.out.println("memes");
		globals.getInstance().selectedImage.getSelectedImage().updateLayer(globals.getInstance().selectedlayer, globals.getInstance().selectedlayerN);
		prevX=x;
		prevY=y;
	}
	
	
	public void update() {
		globals.getInstance().selectedlayer=globals.getInstance().selectedlayer;
	}
	
}
