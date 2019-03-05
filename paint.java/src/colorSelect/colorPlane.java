package colorSelect;

import controlP5.ControlP5;

import javax.swing.WindowConstants;

import controlP5.*;
import processing.core.PApplet;
import main.SecondaryWindows;
import main.globals;


public class colorPlane extends SecondaryWindows {
	ControlP5 cp5;
	colorWheel Wheel;
	int wheelX=62;
	int wheelY=66;
	int[] colorCords= {0,0};
	globals global=globals.getInstance();
	int current=global.selectedColor;
	
	
	public void draw() {
		background(255);
		surface.setAlwaysOnTop(true);
		
		textMode(CENTER);
		fill(0);
		text("RGB",320,20);
		
		
		text("hex",290,100);
		
		line(250,110,390,110);
		text("HSV",320,125);
		
		
		image(Wheel.getWheel(),wheelX,wheelY);//drawing color wheel
		
		//drawing the color rectangles
		fill(global.secondaryColor);
		rect(30,60,30,30);
		fill(global.selectedColor);
		rect(10,40,30,30);
		//drawing the selected color circle
		ellipse(colorCords[0]+wheelX,colorCords[1]+wheelY,5,5);
		//user input for color wheel
		if(mousePressed) {
			int x=mouseX;
			int y=mouseY;
			if(Wheel.inCircle(x,y,wheelX,wheelY)) {
				//getting new selected color	
			if(mouseButton== LEFT) {
				
				global.selectedColor=Wheel.getColor(x-wheelX,y-wheelY);
			}else {
				global.secondaryColor=Wheel.getColor(x-wheelX,y-wheelY);
				
			}
			}
		}//if selected color has been updated
		if(current!=global.selectedColor) {
			
			updateINFO();
			current=global.selectedColor;
		}
	}
	
	public void settings() {
		size(390, 270 ,JAVA2D); 
		Wheel=new colorWheel(this,140); 
	}
	
	
	public void setup() {
		frameRate(15);
		surface.setLocation(10, 10);
		//setting up gui
		cp5=new ControlP5(this);
		
		
		cp5.addButton("swapColors")
		.setPosition(50,40)
		.setSize(10, 10)
		.setColorCaptionLabel(color(150))
		.setColorLabel(color(0))
		.setColorBackground(color(150))
		.setLabel("⤵")
		.setValueLabel("⤵");
		
		
		//adding RGB sliders
		cp5.addSlider("R")
	     .setPosition(250,30)
	     .setWidth(70)
	     .setHeight(11)
	     .setRange(0,255) // values can range from big to small as well
	     .setValue(red(global.selectedColor))
	     .setColorActive(color(255,0,0))
	     .setColorBackground(color(255,255,255))
	     .setColorValue(color(0))
	     .setColorForeground(color(255,0,0))
	     ;
		
		cp5.addSlider("G")
	     .setPosition(250,50)
	     .setWidth(70)
	     .setHeight(11)
	     .setRange(0,255) // values can range from big to small as well
	     .setValue(green(global.selectedColor))
	     .setColorActive(color(0,255,0))
	     .setColorBackground(color(255,255,255))
	     .setColorValue(color(0))
	     .setColorForeground(color(0,255,0))
	     ;
		
		cp5.addSlider("B")
	     .setPosition(250,70)
	     .setWidth(70)
	     .setHeight(11)
	     .setRange(0,255) // values can range from big to small as well
	     .setValue(blue(global.selectedColor))
	     .setColorActive(color(0,0,255))
	     .setColorBackground(color(255,255,255))
	     .setColorValue(color(0))
	     .setColorForeground(color(0,0,255))
	     ;
		
		//adding rgb text fields 
		cp5.addTextfield("Rtext")
	    .setPosition(350, 30)
	    .setSize(30, 11)
	    .setInputFilter(ControlP5.INTEGER)
	    .setText(String.valueOf((int)red(global.selectedColor)))
	    .setColorBackground(color(255))
	    .setColorValue(color(0))
	    .setColorForeground(color(0));
		
		cp5.addTextfield("Gtext")
	    .setPosition(350, 50)
	    .setSize(30, 11)
	    .setInputFilter(ControlP5.INTEGER)
	    .setText(String.valueOf((int)green(global.selectedColor)))
	    .setColorBackground(color(255))
	    .setColorValue(color(0))
	    .setColorForeground(color(0));
		
		cp5.addTextfield("Btext")
	    .setPosition(350, 70)
	    .setSize(30, 11)
	    .setInputFilter(ControlP5.INTEGER)
	    .setText(String.valueOf((int)blue(global.selectedColor)))
	    .setLabel("")
	    .setColorBackground(color(255))
	    .setColorValue(color(0))
	    .setColorForeground(color(0));
		
		//adding +/- buttons to colors
		cp5.addButton("Bplus")
		.setPosition(330,70)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("+")
		.setValueLabel("+")
		;
		cp5.addButton("Bminus")
		.setPosition(320,70)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("-")
		.setValueLabel("-")
		;
		
		cp5.addButton("Gplus")
		.setPosition(330,50)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("+")
		.setValueLabel("+")
		;
		cp5.addButton("Gminus")
		.setPosition(320,50)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("-")
		.setValueLabel("-")
		;
		
		cp5.addButton("Rplus")
		.setPosition(330,30)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("+")
		.setValueLabel("+")
		;
		cp5.addButton("Rminus")
		.setPosition(320,30)
		.setSize(10, 10)
		.setColorCaptionLabel(color(0))
		.setColorLabel(color(0))
		.setColorBackground(color(255))
		.setLabel("-")
		.setValueLabel("-")
		;
		
		cp5.addTextfield("hex")
		.setPosition(320, 90)
		.setSize(40, 12)
		.setCaptionLabel("")
		.setColorBackground(color(255))
	    .setColorValue(color(0))
	    .setColorForeground(color(0))
	    .setText(hex(global.selectedColor))
		;
		
		//getting init cords of selected color
		colorCords=Wheel.getColorPos(global.selectedColor);
		
	}
	
	//blue - button
	public void Bminus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color),green(color),blue(color)-1);
	}
	//blue + button
	public void Bplus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color),green(color),blue(color)+1);
	}
	//green - button
	public void Gminus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color),green(color)-1,blue(color));
	}
	//green + button
	public void Gplus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color),green(color)+1,blue(color));
	}
	//Red - button
	public void Rminus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color)-1,green(color),blue(color));
	}
	//red + button
	public void Rplus(int value) {
		int color=global.selectedColor;
		global.selectedColor=color(red(color)+1,green(color),blue(color));
	}
	
	
	
	//swap color button
	public void swapColors(int theValue) {
		  int tmp=global.secondaryColor;
		  global.secondaryColor=global.selectedColor;
		  global.selectedColor=tmp;
		  updateINFO();
		}
	
	
	//red slider input
	public void R(int value) {
		updateRed(value);
		
	}
	//green slider input
	public void G(int value) {
		updateGreen(value);
	}
	//blue slider input
	public void B(int value) {
		updateBlue(value);
	}
	
	//Red's text input
	public void Rtext(String value) {
		int R;
		if(value.length()==0) {
			R=0;
		}else {
			R = Integer.parseInt(value);
		}
		updateRed(R);
	}
	//greens text input
	public void Gtext(String value) {
		int G;
		if(value.length()==0) {
			G=0;
		}else {
			G = Integer.parseInt(value);
		}
		updateGreen(G);
	}
	//blue text input
	public void Btext(String value) {
		int B;
		if(value.length()==0) {
			B=0;
		}else {
			B = Integer.parseInt(value);
		}
		updateBlue(B);
	}
	
	//update red for when red's value has changed
	public void updateRed(int x) {
		int current=global.selectedColor;
		int G=(int) green(current);
		int B=(int) blue(current);
		int newC=color(x,G,B);
		
		global.selectedColor=newC;
	}
	
	//changing the blue
	public void updateBlue(int x) {
		int current=global.selectedColor;
		int G=(int) green(current);
		int R=(int) red(current);
		int newC=color(R,G,x);
		
		global.selectedColor=newC;
	}
	//changing green
	public void updateGreen(int x) {
		int current=global.selectedColor;
		int R=(int) red(current);
		int B=(int) blue(current);
		int newC=color(R,x,B);
		
		global.selectedColor=newC;
	}
	
	public void hex(String value) {
		char[] hexvalues="0123456789ABCDEF".toCharArray();
		value=value.toUpperCase();
		String hexout="";
		
		for(int i=0;i<value.length();i++) {
			for(char c:hexvalues) {
				if(c==value.charAt(i)) {
					hexout+=c;
				}
			}
		}
		
		global.selectedColor=unhex(hexout.toUpperCase());
	}
	
	//updating all of the sliders colors and text boxes
	void updateINFO() {
		cp5.getController("R").setValue(red(global.selectedColor));
		cp5.getController("G").setValue(green(global.selectedColor));
		cp5.getController("B").setValue(blue(global.selectedColor));
		((Textfield) cp5.getController("Rtext")).setText(String.valueOf((int)red(global.selectedColor)));
		((Textfield) cp5.getController("Gtext")).setText(String.valueOf((int)green(global.selectedColor)));
		((Textfield) cp5.getController("Btext")).setText(String.valueOf((int)blue(global.selectedColor)));
		((Textfield) cp5.getController("hex")).setText(hex(global.selectedColor,6));

		colorCords=Wheel.getColorPos(global.selectedColor);

		
	}

	
//	public void exit()
//	  {
//	    dispose();
//	  }
	
}
