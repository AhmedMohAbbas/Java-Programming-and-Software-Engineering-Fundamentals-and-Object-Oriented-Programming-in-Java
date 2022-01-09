
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void tester(){
        FileResource fh = new FileResource("exportdata.csv");
        CSVParser table = fh.getCSVParser();
        //System.out.println(countryInfo(table, "Nauru"));
        //listExportersTwoProducts(table, "cotton", "flowers");
        //System.out.println(numberOfExporters(table, "cocoa"));
        bigExporters(table, "$999,999,999,999");
        
    }
    
    public String countryInfo(CSVParser x, String Country){
        String info = "";
        
        for(CSVRecord row : x){
            if(row.get("Country").equalsIgnoreCase(Country)){
                info = row.get("Country") + " : " + row.get("Exports") + " : " + row.get("Value (dollars)");
            }
        }
        
        if(info.isEmpty()){
            return "NOT FOUND";
        }
        
        return info;
    }
    
    public void listExportersTwoProducts(CSVParser x, String exItem1, String exItem2 ){
        for(CSVRecord row : x){
            String exports = row.get("Exports");
            if(exports.contains(exItem1) && exports.contains(exItem2)){               
                System.out.println(row.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser x, String exItem){
        int count = 0;
        
        for(CSVRecord row : x){
            String exp = row.get("Exports");
            if(exp.contains(exItem)){
                count = count + 1;
            }
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser x, String amount){
        for(CSVRecord row : x){
            String usd = row.get("Value (dollars)");
            if(usd.length() > amount.length()){
                System.out.println(row.get("Country")+row.get("Value (dollars)"));
            }
        }        
    }

}
