package saveSystem;

import java.awt.Component;
import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;

import editor.changeSystem;
import editor.layer;
import main.globals;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class IOSystem {
	
	//jfile save
	public static void saveFile() {
		File myFilename;
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new OpenFileFilter("png","PNG image") );
		chooser.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("tga","Photo in TARGA format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("tif","Photo in TIFF format") );		
		int returnVal = chooser.showSaveDialog(new Component() {});
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		     myFilename = chooser.getSelectedFile();
		     String ex=((OpenFileFilter) chooser.getFileFilter()).getFileEXT();
		     String filepath=myFilename.getAbsolutePath();
		     System.out.println(myFilename);
		     
		     System.out.println(filepath+"."+ex);
		     globals.getInstance().selectedImage.current.getPic(globals.getInstance().window).save(filepath+"."+ex);		
		     }
		
	}
	
	
	//awt system filesaver
	public static void saveFile(PApplet p) {
//		Dialog d = new Dialog();
		FileDialog fd = new FileDialog(p.frame,"save file");
        fd.setMode(FileDialog.SAVE);
        fd.setFile("*.png");
        fd.setFilenameFilter(new FilenameFilter() {
    		public boolean accept(File dir, String name) {
    			String[] supportedFiles = { "PNG", "png" ,"jpg","JPG","tif","tga" };
    			for (int i = 0; i < supportedFiles.length; i++) {
    				if (name.endsWith(supportedFiles[i])) {
    					return true;
    				}
    			}
    			return false;
    		}
    	});
        fd.setDirectory(fd.getDirectory()+File.separator+"Pictures");
        fd.setLocation(50, 50);
        
        fd.show();
        String dir = fd.getDirectory();
        String file = fd.getFile();
        if(dir ==null|| file==null) {
        	return;
        }
        System.out.println(dir+file);
        globals.getInstance().selectedImage.current.getPic(p).save(dir+file);
	}
	
	//laod file using jfile
	public static void loadFile() {
		File myFilename;
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new OpenFileFilter("png","PNG image"));
		chooser.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("tga","Photo in TARGA format") );
		chooser.addChoosableFileFilter(new OpenFileFilter("tif","Photo in TIFF format") );		
		int returnVal = chooser.showOpenDialog(new Component() {});
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		     myFilename = chooser.getSelectedFile();
//		     String ex=((OpenFileFilter) chooser.getFileFilter()).getFileEXT();
		     String filepath=myFilename.getAbsolutePath();
		     System.out.println(filepath);
		     loadfile(String.valueOf(filepath));
//		     System.out.println(filepath+"."+ex);
//		     globals.getInstance().selectedImage.current.getPic(globals.getInstance().window).save(filepath+"."+ex);		
		     }
		
	}
	
	//loads file from string
	private static void loadfile(String s) {
		PApplet p=globals.getInstance().window;
		PImage img;
		img = p.loadImage(s);
		PGraphics grah=p.createGraphics(img.width, img.height) ;
		grah.beginDraw();
		grah.image(img,0,0);
		grah.endDraw();
		layer l = new layer(img.width,img.height);
		l.setImage(grah);
		
		changeSystem nc=new changeSystem(img.width,img.height);
		nc.current.updateLayer(l,0);
		
		globals.getInstance().selectedImage=nc;
		globals.getInstance().selectedImg=nc.current;
		globals.getInstance().selectedlayer=nc.current.getLayer();
		globals.getInstance().selectedImage.next();
		
	}
	
}
