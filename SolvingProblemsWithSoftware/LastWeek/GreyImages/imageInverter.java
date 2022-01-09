
/**
 * Write a description of imageInverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class imageInverter {
            public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel px : outImage.pixels()){
            Pixel inpx = inImage.getPixel(px.getX(), px.getY());
            int nred = 255 - inpx.getRed();
            int ngreen = 255 - inpx.getGreen();
            int nblue = 255 - inpx.getBlue();
            px.setRed(nred);
            px.setBlue(ngreen);
            px.setGreen(nblue);
        }
        return outImage;
    }
    
        public void SelectConvertSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource img = new ImageResource(x);
            ImageResource Inverimg = makeGray(img);
            Inverimg.draw();
            String fname = img.getFileName();
            String CopyName = "Inverted-"+fname;
            Inverimg.setFileName(CopyName);
            Inverimg.save();
        }
    }

}
