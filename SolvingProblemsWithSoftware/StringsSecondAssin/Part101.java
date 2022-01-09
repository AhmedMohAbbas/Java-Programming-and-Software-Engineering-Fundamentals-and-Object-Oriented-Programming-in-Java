
/**
 * Write a description of Part101 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part101 {
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
        
    public void test(){
        String ex = "gcvhqahfgjkteegk";
        StorageResource genelist = getAllGenes(ex);
        for(String item : genelist.data()){
            System.out.println(item);
        }
        double ratio = cgRatio(ex);
        System.out.println(ratio);
        System.out.println(countCTG(ex));
    }
    
    public double cgRatio(String dna){
        String x = "";
        int count = 0;
        
        for(int i = 0; i < dna.length(); i++){
            x = dna.substring(i, i+1);
            //System.out.println(x);
            if(x.equals("C") || x.equals("G")){
                count = count + 1;
            }            
        }
        
        return ((double)count)/dna.length();
    }
    
    public int countCTG(String dna){
         
        int x = dna.indexOf("CTG");        
        if( x == -1 ){
                return 0;
        }
            
        int count = 1;
        while (true) {            
            x = dna.indexOf("CTG", x + 3 );
            if( x == -1 ){
                break;
            }
            count = count + 1;
        }
        
        return count;
    }
    
    public void  processGenes(StorageResource sr){
        int count = 0;
        int counter1 = 0;
        int counter2 = 0;
        int longestlen = 0;
        String longeststr = "";
        double cgr;
        for(String item : sr.data()){
            count = count + 1;
            if(item.length() > 60 ){
                counter1 = counter1 + 1 ;
                System.out.println("A 'Longer than 60 gene' : "+item);
            }
            cgr = cgRatio(item);
            if(cgr > 0.35){
                System.out.println("A high CG ratio gene : "+item);
                counter2 = counter2 + 1 ;
            }
            if(item.length() > longestlen){
                longestlen = item.length();
                longeststr = item;
            }                        
        }
        System.out.println("The Longest gene is "+ longestlen + " charcters long, it is:\n"+longeststr+"\n");
        System.out.print("Number of 'Longer than 60' genes : "+ counter1+"\n");
        System.out.print("Number of 'high CG ratio' genes : "+ counter2+"\n");
        System.out.print("Total Number of Genes : "+ count);
    }
    
    public void testProcessGenes(){
        FileResource fh = new FileResource("GRch38dnapart.fa.txt");
        String dna = fh.asString();
        StorageResource Genelist = getAllGenes(dna);
        processGenes(Genelist);
        System.out.println("\n"+countCTG(dna));
    }

}
