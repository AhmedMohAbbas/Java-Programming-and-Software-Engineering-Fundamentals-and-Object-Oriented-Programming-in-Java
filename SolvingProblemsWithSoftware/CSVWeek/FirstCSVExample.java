
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    public void readFood(){
        //Either you provide the file name directly, or you leave it empty to
        //-choose from a dialoge box... 
        FileResource fh = new FileResource(); 
        CSVParser prrr = fh.getCSVParser();
        for(CSVRecord x : prrr){
            System.out.print(x.get("Name") + " : ");
            System.out.println(x.get("Favorite Food"));
            System.out.println(x);
        }
    }
    
}
