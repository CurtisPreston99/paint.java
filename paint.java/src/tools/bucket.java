package tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

import editor.layer;
import main.globals;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class bucket  extends tool{
	
	
	

	public bucket(PApplet c) {
		super(c);
		// TODO Auto-generated constructor stub
		name="bucket";
	}
	
	
	
		 
	
	@Override
	public void click(int x, int y, PGraphics l) {
		int colored=0;
		long startTime =System.nanoTime();
		System.out.println("bucket start");
		globals.getInstance().selectedImg.lockget();
		l.point(x,y);
		PGraphics layer=globals.getInstance().selectedlayer.getImage();
		int prevC=layer.get(x, y);
		int newC=globals.getInstance().selectedColor;
		TreeSet<myPoint> checkedPoints=new TreeSet<myPoint>();
		TreeSet<myPoint> toCheck=new TreeSet<myPoint>();
		toCheck.add(new myPoint(x,y));
		int count=0;
		if(layer.get(x, y)!=newC) {
			l.loadPixels();
		while(!toCheck.isEmpty()) {
			count++;
			
			myPoint p=toCheck.last();
			toCheck.remove(p);
			checkedPoints.add(p);
			
			//checks point
			if(layer.get(p.x,p.y)==prevC) {
				l.pixels[getIndex(l.width, p.x, p.y)]=newC;
				colored++;
				//
				
				if(!containsPOINT(checkedPoints,new myPoint(p.x+1, p.y))){
				toCheck.add(new myPoint(p.x+1, p.y));
				}
				if(!containsPOINT(checkedPoints,new myPoint(p.x-1, p.y))){
					toCheck.add(new myPoint(p.x-1, p.y));
					}
				if(!containsPOINT(checkedPoints,new myPoint(p.x, p.y+1))){
					toCheck.add(new myPoint(p.x, p.y+1));
					}
				if(!containsPOINT(checkedPoints,new myPoint(p.x, p.y-1))){
					toCheck.add(new myPoint(p.x, p.y-1));
					}
				
				
				
			}
			
		}
		l.updatePixels();
		}
		
		
		globals.getInstance().selectedImg.realese();
		long timeElapsed = System.nanoTime() - startTime;
		System.out.println("Execution time in nanoseconds  : " + timeElapsed);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
		System.out.println("Execution time in seconds : " + (timeElapsed / 1000000)/1000);
		System.out.println("for "+colored+" pixels");
	}
	
	public boolean containsPOINT(TreeSet<myPoint> i,myPoint p) {
		  return i.contains(p);
	}

	


	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics l) {
	}

}
