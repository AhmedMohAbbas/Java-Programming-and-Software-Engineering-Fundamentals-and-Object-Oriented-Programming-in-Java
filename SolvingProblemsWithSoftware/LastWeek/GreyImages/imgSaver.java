
/**
 * Write a description of imgSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;

public class imgSaver {
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource img = new ImageResource(x);
            String fname = img.getFileName();
            String CopyName = "Copy-"+fname;
            img.setFileName(CopyName);
            img.save();
        }
    }
}
