
/**
 * Write a description of WeatherProblem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVMax {
    
     public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if(largestSoFar == null){
            return currentRow;
        }
        
        double maxtemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
        double temp = Double.parseDouble(currentRow.get("TemperatureF"));
        
        if(maxtemp < temp){
            largestSoFar = currentRow;
        } 
        
        return largestSoFar;
    }   
    
    public CSVRecord hottestHourInFile(CSVParser x){
        CSVRecord largestSoFar = null;
        
        for(CSVRecord row : x){
             largestSoFar = getLargestOfTwo(row,largestSoFar);           
        }
         
        return largestSoFar; 
    }

    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord DAlargest = null;
        
        for(File x : dr.selectedFiles()){
            FileResource fh = new FileResource(x);
            CSVRecord z = hottestHourInFile(fh.getCSVParser());
            DAlargest = getLargestOfTwo(z, DAlargest);                                  
        }
        
        return DAlargest;
    }
    
        
    public void testHottestInDay(){
        FileResource fh = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fh.getCSVParser());
        System.out.println("hottest record " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    
    
    public void testHottestInManyDay(){
        CSVRecord HottestRecord = hottestInManyDays();
        System.out.println("Hottest Record : " + HottestRecord.get("TemperatureF")+ " at " + HottestRecord.get("DateUTC"));
    }
    
}
