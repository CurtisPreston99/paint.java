package tools;


import java.util.TreeSet;

import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;

public class bucket  extends tool{
	
	
	int dif=80;

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
			
				//makes sure the point is in the picture so we dont get errors latter on 
			if(p.x>=0 && p.x<layer.width && p.y>=0 && p.y< layer.height) {
				
				
				//checks point
			if(colorDif(layer.get(p.x,p.y),prevC)<dif) {
				l.pixels[getIndex(l.width, p.x, p.y)]=newC;
				colored++;
				
				myPoint c;
				
				//NOTE: this adds point that are not in the picture to the list eg x:0 y:-1
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
		l.updatePixels();
		}
		
		
		long timeElapsed = System.nanoTime() - startTime;
		System.out.println("Execution time in nanoseconds  : " + timeElapsed);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
		System.out.println("Execution time in seconds : " + (timeElapsed / 1000000)/1000);
		System.out.println("for "+colored+" pixels");
		System.out.println("and "+count+" cycles");
	}	
	
	public boolean containsPOINT(TreeSet<myPoint> i,myPoint p) {
		  return i.contains(p);
	}

	


	@Override
	public void drag(int x, int y, int xdif, int ydif, PGraphics l) {
		
	}

}
