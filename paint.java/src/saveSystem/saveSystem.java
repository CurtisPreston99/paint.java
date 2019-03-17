package saveSystem;

import java.awt.Component;
import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;

import main.globals;
import processing.core.PApplet;

public class saveSystem {
	
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
}
