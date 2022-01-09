
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVMin {
    
     public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if(lowestSoFar == null){
            return currentRow;
        }
        
        double mintemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
        double temp = Double.parseDouble(currentRow.get("TemperatureF"));
        
        if(temp < mintemp && temp > -100){
            lowestSoFar = currentRow;
        } 
        
        return lowestSoFar;
    }   
    
    public CSVRecord ColdestHourInFile(CSVParser x){
        CSVRecord lowestSoFar = null;
        
        for(CSVRecord row : x){
             lowestSoFar = getLowestOfTwo(row,lowestSoFar);           
        }
         
        return lowestSoFar; 
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord leastHumidityRecord = null;
        int minHumidity = 100;
        
        for(File x : dr.selectedFiles()){
            FileResource fh = new FileResource(x);
            CSVRecord z = lowestHumidityInFile(fh.getCSVParser());
            int humidity = Integer.parseInt(z.get("Humidity"));
            if(humidity < minHumidity){
                minHumidity = humidity;
                leastHumidityRecord = z;
            }
                                             
        }
        
        return leastHumidityRecord;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord x = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+x.get("Humidity")+" at "+ x.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser x){
        CSVRecord leastHumidity = null;
        int minHumidity = 100;
        
        for(CSVRecord row : x){
            if(row.get("Humidity").contains("N/A")){continue;}
            int humidity = Integer.parseInt(row.get("Humidity"));
            if(humidity < minHumidity){
                minHumidity = humidity;
                leastHumidity = row;
            }
        }
        
        return leastHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+ csv.get("DateUTC"));
    }
    

    public CSVRecord ColdestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord DAloweztt = null;
        
        for(File x : dr.selectedFiles()){
            FileResource fh = new FileResource(x);
            CSVRecord z = ColdestHourInFile(fh.getCSVParser());
            DAloweztt = getLowestOfTwo(z, DAloweztt);                                  
        }
        
        return DAloweztt;
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        double mintemp = 70;
        File target = null;
        
        for(File x : dr.selectedFiles()){
            FileResource fh = new FileResource(x);
            CSVRecord z = ColdestHourInFile(fh.getCSVParser());
            double temp = Double.parseDouble(z.get("TemperatureF"));
            if(temp < mintemp && temp > -100){
                mintemp = temp;
                target = x;
            }
                                             
        }
        
        return target.getName();        
    }
    
    public void testFileWithColdestTemperature(){
        String x = fileWithColdestTemperature();
        System.out.println("Coldest day was in file is :  "+ x);
        FileResource fh = new FileResource("data/2014/"+x);
        CSVParser table = fh.getCSVParser();
        CSVRecord lowest = ColdestHourInFile(table);
        System.out.println("Coldest Temperature " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser q = fh.getCSVParser();
        for(CSVRecord row : q){
            System.out.println(row.get("DateUTC") + " " +row.get("TemperatureF"));
        }
    }
       
        
    public void testColdestInDay(){
        FileResource fh = new FileResource();
        CSVRecord lowest = ColdestHourInFile(fh.getCSVParser());
        System.out.println("Coldest record " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    
    public void testColdestInManyDays(){
        CSVRecord ColdestRecord = ColdestInManyDays();
        System.out.println("Coldest Record : " + ColdestRecord.get("TemperatureF")+ " at " + ColdestRecord.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser table){
        int count = 0;
        double total = 0;
        for(CSVRecord row : table){
            count = count + 1;
            total = total + Double.parseDouble(row.get("TemperatureF"));
        }
        return total / count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fh = new FileResource();
        CSVParser x = fh.getCSVParser();
        double avg = averageTemperatureInFile(x);
        System.out.println("Average temperature in file is "+avg);        
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int Humvalue){
        int count = 0;
        double total = 0;
        int humidity = 0;
        for(CSVRecord row : parser){
            humidity = Integer.parseInt(row.get("Humidity"));
            if(humidity >= Humvalue){
                count = count + 1;
                total = total + Double.parseDouble(row.get("TemperatureF"));
            }
        }
        
        if (count == 0){
            return 0;
        }
        return total / count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fh = new FileResource();
        CSVParser x = fh.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(x, 80);
        if(avg == 0){
            System.out.println("No temperatures exist with the specified Humidity");
        }
        else{
            System.out.println("Average temperature for the specified Humidity in file is "+avg);
        }
    }
    
}