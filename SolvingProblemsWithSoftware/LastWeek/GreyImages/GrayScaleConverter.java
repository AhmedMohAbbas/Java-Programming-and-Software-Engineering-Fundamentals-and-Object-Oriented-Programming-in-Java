
/**
 * Write a description of Example here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel px : outImage.pixels()){
            Pixel inpx = inImage.getPixel(px.getX(), px.getY());
            int avg = (inpx.getRed()+inpx.getBlue()+inpx.getGreen())/3;
            px.setRed(avg);
            px.setBlue(avg);
            px.setGreen(avg);
        }
        return outImage;
    }

    public void TestMakeGray(){
        ImageResource x = new ImageResource();
        ImageResource gray = makeGray(x);
        gray.draw();
    }

    public void SelectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource img = new ImageResource(x);
            ImageResource grimg = makeGray(img);
            grimg.draw();
        }
    }
}
