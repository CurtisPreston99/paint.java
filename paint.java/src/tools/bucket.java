package tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
		globals.getInstance().selectedImg.lockget();
		l.point(x,y);
		PGraphics layer=globals.getInstance().selectedlayer.getImage();
		int prevC=layer.get(x, y);
		int newC=globals.getInstance().selectedColor;
		ArrayList<myPoint> tocolor=new ArrayList<myPoint>();
		ArrayList<myPoint> checkedPoints=new ArrayList<myPoint>();
		ArrayList<myPoint> toCheck=new ArrayList<myPoint>();
		toCheck.add(new myPoint(x,y));
		int count=0;
		if(layer.get(x, y)!=newC) {
		while(!toCheck.isEmpty()) {
			count++;
//			System.out.println(count);
//			System.out.println(toCheck.size());
//			System.out.println("-------------");
			
			myPoint p=toCheck.get(toCheck.size()-1);
			toCheck.remove(toCheck.size()-1);
			checkedPoints.add(p);
			
			if(layer.get(p.x,p.y)==prevC) {
				tocolor.add(p);
				
				
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
		}
		
		l.loadPixels();
		for(myPoint p:tocolor) {
//			l.set(p.x,p.y,newC);
			
			l.pixels[getIndex(l.width, p.x, p.y)]=newC;
		}
		l.updatePixels();
		globals.getInstance().selectedImg.realese();
	}
	
	public boolean containsPOINT(ArrayList<myPoint> i,myPoint p) {
		  for(myPoint e : i) {
			  if(e.equals(p)) {
				  return true;
			  }
		  }
		  return false;
	  
	}

	class myPoint {
		  int x;
		  int y;

		  myPoint(int a, int b) {
		    x=a;
		    y=b;
		  }
		  
		  
		  
		  boolean equals(myPoint other) {
		    return(x==other.x && y==other.y);
		  }
		}


	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics l) {
	}

}
