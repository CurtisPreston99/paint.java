package saveSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import editor.image;
import editor.layer;
import main.globals;

public class PDJsave {
	

	public static void savePDJ(String f) {
		System.out.println("saveDPJ");
		String tmpFolder=f.substring(0, f.length()-4);
		System.out.println(tmpFolder);
		
		String xml="<PDJ>\n";
		image toSave=globals.getInstance().selectedImage.current;
		File foldertmp=new File(tmpFolder);
		Boolean success = (foldertmp.mkdirs());
		xml+="<width>"+String.valueOf(toSave.getWidth())+"</width>\n";
		xml+="<height>"+String.valueOf(toSave.getHeight())+"</height>\n";
		
		if(success) {
			ArrayList<layer> layers=toSave.layers;
			xml+="<layers>\n";
			for(int i=0;i<layers.size();i++) {
				layer c=layers.get(i);
				xml+="<layer>\n";
				xml+="<name>"+c.getLayerName()+"</name>\n";
				xml+="<opacity>"+String.valueOf(c.getOpacity())+"</opacity>\n";
				if(c.getVisible()) {
				xml+="<visable>1</visable>\n";
				}else {
				xml+="<visable>0</visable>\n";	
				}
				String layerpic=String.valueOf(i)+".png";
				c.getImage().save(tmpFolder+"/"+layerpic);
				xml+="<file>"+layerpic+"</file>\n";
				xml+="</layer>\n";
			}
			xml+="</layers>\n";
			xml+="</PDJ>";
			
			try {
				PrintWriter out = new PrintWriter(tmpFolder+"/layers.xml");
				out.write(xml);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String tmpname=tmpFolder.split("/")[tmpFolder.split("/").length-1];
			try {
				zipDirectory(foldertmp,f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//deleting tmp folder
			
			String[]entries = foldertmp.list();
			for(String s: entries){
			    File currentFile = new File(foldertmp.getPath(),s);
			    System.out.println("deleting tmp:"+currentFile);
			    currentFile.delete();
			}
		    System.out.println("deleting folder:"+foldertmp);
			foldertmp.delete();
		}
	}
	
	private static void zipDirectory(File dir, String zipDirName) {
        try {
            List<String> filesListInDir;

            filesListInDir=populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
                System.out.println("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @return 
     * @throws IOException
     */
    private static List<String> populateFilesList(File dir) throws IOException {
        List<String> filesListInDir = new ArrayList<String>();

        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(file);
        }
        return filesListInDir;
    }
}