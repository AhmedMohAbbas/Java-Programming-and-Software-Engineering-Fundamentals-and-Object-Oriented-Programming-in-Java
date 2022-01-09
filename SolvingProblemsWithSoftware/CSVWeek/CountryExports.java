
/**
 * Write a description of CountryExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports {
    
    public void ListExporters(CSVParser x, String WantedGoods){
        //StorageResource  Clist = new StorageResource()  ;
        
        for(CSVRecord row : x){
            String exports = row.get("Exports");
            if(exports.contains(WantedGoods)){
                //Clist.add(row.get("Country"));
                System.out.println(row.get("Country"));
            }
        }
        
        //return Clist;
    }
    
    public void whoExportsCoffee(){
        FileResource fh = new FileResource();
        CSVParser data = fh.getCSVParser();
        ListExporters(data, "coffee");
    }
    
    
}
