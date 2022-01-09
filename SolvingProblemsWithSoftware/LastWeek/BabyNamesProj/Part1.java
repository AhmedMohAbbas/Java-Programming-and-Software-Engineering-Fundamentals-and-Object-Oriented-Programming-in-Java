
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
        public void TotalBirths(FileResource fh){
        int totalBirths = 0;
        int totalboys = 0;
        int totalgirls = 0;
        int counterTotal = 0;
        int counterBoys = 0;
        int counterGirls = 0;
        for(CSVRecord row : fh.getCSVParser(false)){
            int births = Integer.parseInt(row.get(2));
            totalBirths = totalBirths +  births;
            counterTotal = counterTotal +1; 
            if(row.get(1).equalsIgnoreCase("M")){
                totalboys = totalboys + births;
                counterBoys = counterBoys + 1;
            }
            else{
                totalgirls = totalgirls + births;
                counterGirls = counterGirls + 1; 
            }
        }
        System.out.println("Total Births: "+totalBirths+
                 "  Total No. of Names:" + counterTotal);
        System.out.println("Total Boys: "+totalboys+
                 "  Total No. of Boy Names:" + counterBoys);
        System.out.println("Total Girls: "+totalgirls+
                  "  Total No. of Girl Names:" + counterGirls);
    }
        public void TestTotalBirths(){
        FileResource fh = new FileResource();
        TotalBirths(fh);
    }
    
    
    
    public int getRank(int year, String name, String gender){
        String fname = "us_babynames_by_year/yob"+ year + ".csv";
        FileResource fh = new FileResource(fname);
        int counter = 0;
        CSVRecord nameRecord = null;
        for(CSVRecord row : fh.getCSVParser(false)){
            if(gender.equals(row.get(1))){
                counter = counter + 1;
                if(name.equalsIgnoreCase(row.get(0))){
                    nameRecord = row;
                    break;
                }                
            }
            else{continue;}
        }
        
        if(nameRecord == null){return -1;}
        
        return counter;
    }
    public void TestgetRank(){
        int rank = getRank(2012, "Olivia", "M");
        System.out.println(rank);
    }
    
    
    
    public String getName(int year, int Rank, String gender){
        String fnam = "us_babynames_by_year/yob"+ year + ".csv";
        FileResource fh = new FileResource(fnam);
        int counter = 0;
        CSVRecord RankRecord = null;
        for(CSVRecord row : fh.getCSVParser(false)){
            if(gender.equals(row.get(1))){
                counter = counter + 1;
                if(counter == Rank){
                    RankRecord = row;
                    break;
                }
            }
            else{continue;}
        }
        
        if(RankRecord == null){return "No Name";}
        
        return RankRecord.get(0);    
    }
        public void TestgetName(){
        String name = getName(2013, 2, "F"); 
        System.out.println(name);
    }
    
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String NewName = getName(newYear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+NewName+" in "+ newYear);
    }
    public void TestwhatIsNameInYear(){
        whatIsNameInYear("Mason", 2012, 2014, "M");
    }
    
    
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestYear = 0;
        int highestRank = 99999999;
        for(File x : dr.selectedFiles()){
            String fnam = x.getName();
            int yearIndex = fnam.indexOf("yob") + 3; 
            int year = Integer.parseInt(fnam.substring(yearIndex, yearIndex+4));
            int rank = getRank(year, name, gender);
            if(rank < highestRank && rank != -1){
                highestRank = rank;
                highestYear = year;
            }            
        }
        
        if(highestRank == 99999999){return -1;}
        
        return highestYear;
    }
    public void TestyearOfHighestRank(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }
    
    
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        int total = 0;
        for(File x : dr.selectedFiles()){
            String fnam = x.getName();
            int yearIndex = fnam.indexOf("yob") + 3; 
            int year = Integer.parseInt(fnam.substring(yearIndex, yearIndex+4));
            int rank = getRank(year, name, gender);
            if(rank != -1){
                count = count + 1;
                total = total + rank;
            }
        }
        
        if(count == 0){return -1;}
        
        return ((double)total) / count;
    }
    public void TestgetAverageRank(){
        double avg = getAverageRank("Mason", "M");
        System.out.println(avg);
    }
    
    
    
    public int getTotalBirthsRankedHigher( int year, String name, String gender){
        String fname = "us_babynames_by_year/yob"+ year + ".csv";
        FileResource fh = new FileResource(fname);
        int total = 0;
        CSVRecord nameRecord = null;
        for(CSVRecord row : fh.getCSVParser(false)){
            if(gender.equals(row.get(1))){
                if(name.equalsIgnoreCase(row.get(0))){
                    nameRecord = row;
                    break;
                }
                else{
                    total = total + Integer.parseInt(row.get(2));
                }
            }
            else{continue;}
        }
        
        if(nameRecord == null){return -1;}
        
        return total;
    }
    public void TestgetTotalBirthsRankedHigher(){
        int totalBirths = getTotalBirthsRankedHigher(2012, "Mason", "M");
        System.out.println(totalBirths);
    }
}
