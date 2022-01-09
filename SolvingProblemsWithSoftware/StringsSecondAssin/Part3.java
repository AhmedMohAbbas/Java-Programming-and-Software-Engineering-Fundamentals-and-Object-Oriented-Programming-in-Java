
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part3 {
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
    
    public StorageResource getAllGenes(String dna){
        StorageResource x = new StorageResource();
        int startindex = 0;
        while(true){
            String currgene = findGene(dna, startindex);
            if(currgene.isEmpty()){
                break;
            }
            x.add(currgene);
            startindex = dna.indexOf(currgene, startindex)+currgene.length();
        }
        return x;
    }
    
    public void testgetAll(String dna){
        System.out.println("Testing getAllGenes  on :");
        StorageResource xyz = getAllGenes(dna);
        for(String g : xyz.data() ){
            System.out.println(g);
        }
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
    
    public int countGenes(String dna){
        int startindex = 0;
        int count = 0;        
        while(true){
            String gene = findGene(dna, startindex);
            if(gene.isEmpty()){
                break;
            }
            count = count + 1;
            startindex = dna.indexOf(gene, startindex) + gene.length();                        
        }
        
        return count;        
    }
    
    public void testCountGenes(){
        String ex = "AATGCTAACTAGCTGACTAAT";
        int num = countGenes(ex);
        printAllGenes(ex);
        System.out.println(num);
    }

}
