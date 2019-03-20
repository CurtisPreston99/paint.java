package saveSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import editor.changeSystem;
import editor.image;
import editor.layer;
import main.globals;
import processing.core.PGraphics;
import processing.core.PImage;

public class PDJload {

	
	static public void loadPDJ(String f) {
		String unzipPath=f.substring(0, f.length()-4);
		unzip(f,unzipPath);
		System.out.println(unzipPath);
		ArrayList<layer> layers=xmltolayers(unzipPath);
		
		image i = new image(layers.get(0).getImage().width, layers.get(0).getImage().height);
		i.layers=layers;
		
		changeSystem c = new changeSystem(layers.get(0).getImage().width, layers.get(0).getImage().height);
		c.current=i;
		c.next();
		
		
		globals.getInstance().selectedImage=c;
		c.updateGlobal();
		
		
		
		//deleting tmp folder
		File foldertmp = new File(unzipPath);
		String[]entries = foldertmp.list();
		for(String s: entries){
		    File currentFile = new File(foldertmp.getPath(),s);
//		    System.out.println("deleting tmp:"+currentFile);
		    currentFile.delete();
		}
//	    System.out.println("deleting folder:"+foldertmp);
		foldertmp.delete();
		
	}
	
	public static ArrayList<layer> xmltolayers(String xml){
		ArrayList<layer> layers = new ArrayList<layer>();
		try {
		File fXmlFile = new File(xml+"/layers.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList Layerlist = doc.getElementsByTagName("layer");
		
		// go through all layer ellements and get a layer from it
		for(int i=0;i<Layerlist.getLength();i++) {
		    Element LayerElement = (Element) Layerlist.item(i);
		    layers.add(ElementtoLayer(LayerElement,xml));
		}
		
		
		
//		printChildren(nList);
		
		}
		catch(Exception e) {
			
			
		}
		return layers;
		
	}
	
	static int[] getDimensions(Element root) {
		int[] Dimensions= new int[2];
		System.out.println("enter getD");
		
	    System.out.print("width == ");
	    NodeList Lwidth=root.getElementsByTagName("width");
	    NodeList Lheight=root.getElementsByTagName("height");
	    
	    String Swidth = Lwidth.item(0).getFirstChild().getTextContent();
	    String Sheight =Lheight.item(0).getFirstChild().getTextContent();
	    Dimensions[0]=Integer.valueOf(Swidth);
	    Dimensions[1]=Integer.valueOf(Sheight);
	    System.out.print(Dimensions[0]);
	    System.out.println();
	    System.out.print("height == ");
	    System.out.print(Dimensions[1]);
	    System.out.println();
	    

	    return Dimensions;

		
	}
	
	static layer ElementtoLayer(Element LayerElement,String filePath) {
		//geting nodes
		NodeList name = LayerElement.getElementsByTagName("name");
	    NodeList opacity = LayerElement.getElementsByTagName("opacity");
	    NodeList visable = LayerElement.getElementsByTagName("visable");
	    NodeList file = LayerElement.getElementsByTagName("file");
	    //getting text elements 
	    String layerName = name.item(0).getFirstChild().getTextContent();
	    String layerOpacity = opacity.item(0).getFirstChild().getTextContent();
	    String layervisable = visable.item(0).getFirstChild().getTextContent();
	    String layerfile = file.item(0).getFirstChild().getTextContent();
	    
	    System.out.println(layerName);
	    System.out.println(layerOpacity);
	    System.out.println(layervisable);
	    System.out.println(filePath+"/"+layerfile);
	    
	    //loading picture and drawing it onto a PGraphic
	    PImage pimg=globals.getInstance().window.loadImage(filePath+"/"+layerfile);
	    
	    PGraphics layerImage=globals.getInstance().window.createGraphics(pimg.width, pimg.height);
	    layerImage.beginDraw();
	    layerImage.image(pimg,0,0);
	    layerImage.endDraw();
	    
	    //creating and setting the layer
	    layer layer=new layer(layerImage);
	    layer.setLayerName(layerName);
	    layer.setOpacity(Integer.valueOf(layerOpacity));
	    if(layervisable.equals("1")) {
	    	layer.setVisible(true);
	    }else {
	    	layer.setVisible(false);
	    }
	    return layer;
	}
	
	
	private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
