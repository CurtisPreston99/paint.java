package tools;

import java.util.ArrayList;

import main.SecondaryWindows;
import main.globals;
import processing.core.PApplet;

public class toolWindow extends SecondaryWindows{
	ArrayList<tool> tools=new ArrayList<>();
	toolSys System=toolSys.getSystem();
	
	public void settings(){
		size(128,128);
		
		
	}
	
	public void setup() {
		surface.setAlwaysOnTop(true);
		tools.add(new paintbrush(this));
		tools.add(new squareTool(this));
		tools.add(new circleTool(this));
		tools.add(new lineTool(this));
		tools.add(new bucket(this));
		tools.add(new colorSelect(this));
		tools.add(new colorDif(this));
		System.selected=tools.get(0);
		
//		System.currentlayer=globals.getInstance().selectedlayer;
	}
	
	public void draw() {
		
		onTopCheck();
		for(int x=0;x<8;x++) {
			
			for(int y=0;y<8;y++) {
				if(8 * x+ y<tools.size()) {
					tool s=tools.get(8 * x+ y);
//					println(s.name);
					image(s.getIcon(),x*16,y*16);
				}
			}
			line(0,x*16,width,x*16);
			line(x*16,0,x*16,height);

		}
		
	}
	//gets the right index then sets the selected tool
	public void mousePressed() {
//		println(mouseX/16);
//		println(mouseY/16);
//		println((8 * (int)(mouseX/16))+ (int)(mouseY/16));
		int index=(8 * (int)(mouseX/16))+ (int)(mouseY/16);
		if(index<tools.size()) {
			tool t = tools.get(index);
			println(t.name);
			System.selected=t;
		}
	}

}
