package main;

import editor.changeSystem;
import editor.image;
import editor.layer;
import processing.core.PApplet;
import processing.core.PGraphics;

public class globals {
	static globals singilton = new globals();
	public Boolean SecondaryOnTop = false;
	public PApplet window;
	public int selectedColor;
	public int secondaryColor;
	public layer selectedlayer;
	public image selectedImg;
	public int selectedlayerN;
	public changeSystem selectedImage;
	public int mouse=0;//left==0 right==1
	public PGraphics drawinglayer;
	
	public float colorDistance(int a, int b) 
	{
	      float redDiff = window.red(a) - window.red(b);
	      float grnDiff = window.green(a) - window.green(b);
	      float bluDiff = window.blue(a) - window.blue(b);

	      return window.sqrt( window.sq(redDiff) + window.sq(grnDiff) + window.sq(bluDiff) );
	}
	
	public void newDrawingLayer() {
    	PGraphics p = window.createGraphics(selectedImage.current.getWidth(), selectedImage.current.getHeight());
    	p.beginDraw();
    	p.background(0, 0);
    	drawinglayer=p;
    	}
	
	static public globals getInstance() {
		
		return singilton;
	}
	
}
