/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medecine3d;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Victor
 */
class Post {

    private final String title; 
    private final String description; 
    private final int id_author;
    private  String category;
    private  String imagelink;
    private  String objlink;
    
    public Post(String tit, String desc, int auth,String cat) {
        this.title =tit ;
	this.description = desc;
	this.id_author = auth;
        this.category = cat;
        this.imagelink="";
        this.objlink="";
    }

    Post() {
        this.title ="";
	this.description = "";
	this.id_author = 1;
        this.category = "";
        this.imagelink="";
        this.objlink="";
    }
    
    public String getTitle() {
	return this.title;
    }

    public String getDescrition() {
	return this.description;
    }

    public int getId_auth() {
        return this.id_author;
    }
    
    public void breakDesc()
    {
    Pattern imageRegEx;
     imageRegEx = Pattern.compile("(https?:\\/\\/([\\da-z\\.-]+)([\\/\\w \\.-]*)*\\/?\\.(jpg|png|zip))" );
     
    
        
    Matcher img;
        //System.out.println(this.description);
        img = imageRegEx.matcher(this.description);
        img.find();
        this.imagelink=img.group();
        System.out.println(this.imagelink);
        
        System.out.println("cat : " + category);
               
     
        img.find();
        img.find();
            
        this.objlink=img.group();
            
        System.out.println("objlink"+this.objlink);
        
        
        Pattern imageRegEx2;
        imageRegEx2 = Pattern.compile("([A-Za-z0-9_-]+).([a-z0-9_-]+)$" );
        Matcher img2;
        //System.out.println(this.description);
        img2 = imageRegEx2.matcher(imagelink);
        img2.find();
        
        //TODO get name
        String name=img2.group();
        System.out.println(name);

        if(!isExist(category, "Images")){
            File theDir = new File("Images\\"+category);
            theDir.mkdir();
        }
   
        if(!isExist(name, "Images\\"+category)){
            System.out.println("EXISTEPAS");
            saveUrl("Images\\"+category+"\\"+name, imagelink);
            saveUrl("Images\\"+category+"\\"+name.split("\\.")[0]+".zip", objlink);
            FileService unZip = new Zip();
            unZip.unZip("Images\\"+category+"\\"+name.split("\\.")[0]+".zip","Images\\"+category, name.split("\\.")[0]);
            File MyFile = new File("Images\\"+category+"\\"+name.split("\\.")[0]+".zip"); 
            MyFile.delete();
        }
        else
            System.out.println("EXISTE");
        
    }
   
    
   @Override
    public String toString() {
	return "\n" + " title=" + title+
                "\n" + " desc=" + description+
                "\n"+"category="+category+
                "\n" + " link image=" + imagelink;

    }
    
    public void saveUrl(final String filename, final String urlString){
    BufferedInputStream in = null;
    FileOutputStream fout = null;
    try {
        in = new BufferedInputStream(new URL(urlString).openStream());
        fout = new FileOutputStream(filename);

        final byte data[] = new byte[1024];
        int count;
        while ((count = in.read(data, 0, 1024)) != -1) {
            fout.write(data, 0, count);
        }
    }   catch (MalformedURLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (fout != null) {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
    
    public boolean isExist(String path, String src){
        File repertoire = new File(src);
        File[] files=repertoire.listFiles();
        for(int i=0; i<files.length;i++){
            if(files[i].getName().equals(path))
                return true;
        }
        return false;
    }
    
    
}
