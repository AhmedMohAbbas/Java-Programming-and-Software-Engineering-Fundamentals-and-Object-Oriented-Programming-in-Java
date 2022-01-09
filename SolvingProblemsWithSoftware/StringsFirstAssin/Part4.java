
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import java.util.Locale;

public class Part4 {
    public void FindURL(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        String flag ="youtube.com";
        String Target = "Fuck" ;
        String x = "";
        
        for(String s : ur.words()){
            x = s.toLowerCase(Locale.ENGLISH);
            if(x.contains("youtube.com")){
                int y = x.indexOf("\"");
                int z = x.lastIndexOf("\"");
                Target = s.substring(y + 1, z);
                System.out.println(Target);
            }                                   
        }        
    }
}
