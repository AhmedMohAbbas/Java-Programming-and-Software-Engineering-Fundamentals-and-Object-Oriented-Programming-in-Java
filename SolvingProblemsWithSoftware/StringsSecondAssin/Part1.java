
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
       int currindex = dna.indexOf(stopCodon, startIndex + 3);
       while (currindex != -1){
          if((currindex - startIndex) % 3 == 0){
            return currindex;
          }
          else{
              currindex = dna.indexOf(stopCodon, currindex + 1);
          }
       }
        
        return dna.length();
    }
    
    public String findGene(String dna, int where){
        
        int startindex = dna.indexOf("ATG", where);
        if(startindex == -1){
            return "";
        }
        
        int taaindex = findStopCodon(dna, startindex, "TAA");
        int tagindex = findStopCodon(dna, startindex, "TAG");
        int tgaindex = findStopCodon(dna, startindex, "TGA");
        int minindex = Math.min(taaindex, Math.min(tagindex, tgaindex));
        if(minindex == dna.length()){
            return "";
        }
        
        return dna.substring(startindex, minindex +3 );        
    }
    
    public void testFindStopCodon(){
        //            012345678901234567890123456789012345678
        String ex1 = "TAGGGAACAAGCATTTAAACTGACACTCGTATTCTGTAA";
        int dex = findStopCodon(ex1,0,"TAA");
        System.out.println(dex);    
    }
    
    public void testFindGene (){
        String ex = "ATGTTGCACGGTGGGGACAGATAATCGAGGTAC";
        String ex2 = "TAGGGAACAAGCATAGTTATGAACTGACACTAACGTATTCTGTAA";
        System.out.println(ex);
        System.out.println(findGene(ex,0));
        System.out.println(ex2);
        System.out.println(findGene(ex2,0));       
    }
    
    public void printAllGenes(String dna){
        
        int startindex = 0;
        
        while(true){
            String gene = findGene(dna, startindex);
            if(gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            startindex = dna.indexOf(gene, startindex) + gene.length();                        
        }
    }

}
