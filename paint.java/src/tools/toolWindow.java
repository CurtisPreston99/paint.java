package tools;

import java.util.ArrayList;

import main.SecondaryWindows;
import main.globals;
import processing.core.PApplet;

public class toolWindow extends SecondaryWindows{
	ArrayList<tool> tools=new ArrayList<>();
	toolSys System=toolSys.getSystem();
	
	public void settings(){
		size(45,230);
		
		
	}
	
	public void setup() {
		surface.setAlwaysOnTop(true);
		tools.add(new paintbrush(this));
		tools.add(new squareTool(this));
		tools.add(new circleTool(this));
		System.selected=tools.get(2);
		
//		System.currentlayer=globals.getInstance().selectedlayer;
	}
	
	public void draw() {
		onTopCheck();
		image(tools.get(0).icon,0,0);
		
	}

}
