
/**
 * Write a description of readOnFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;



public class BabyBirths {
    public void readOnFile(int year){
        String fname = "data/yob"+ year + ".txt";
        FileResource fh = new FileResource(fname);
        CSVParser pars= fh.getCSVParser(false);//Meaning: there are no headers.
        for(CSVRecord row : pars){
            String name = row.get(0);
            String gender = row.get(1);
        }
    }
    
    public void PrintNames(){
        FileResource fh = new FileResource();
        for(CSVRecord row : fh.getCSVParser(false)){
            System.out.println("Name: "+row.get(0)+", Gender: "+row.get(1));
        }
    }
    
    public void TotalBirths(FileResource fh){
        int totalBirths = 0;
        int totalboys = 0;
        int totalgirls = 0;
        for(CSVRecord row : fh.getCSVParser(false)){
            int births = Integer.parseInt(row.get(2));
            totalBirths += births;
            if(row.get(1).equalsIgnoreCase("M")){
                totalboys = totalboys + births;
            }
            else{
                totalgirls = totalgirls + births;
            }
        }
        System.out.println("Total Births: "+totalBirths);
        System.out.println("Total Boys: "+totalboys);
        System.out.println("Total Girls: "+totalgirls);
    }
    
    public void TestTotalBirths(){
        FileResource fh = new FileResource();
        TotalBirths(fh);
    }

}
