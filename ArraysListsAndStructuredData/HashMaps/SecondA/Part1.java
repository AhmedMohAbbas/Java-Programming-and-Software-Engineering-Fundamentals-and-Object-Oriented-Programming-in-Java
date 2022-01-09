
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Part1 {
    private HashMap<String, Integer> codcount;
    
    public Part1(){
        codcount = new HashMap<String, Integer>();        
    }
    
    private void buildCodonMap(int frame, String dna){
        codcount.clear();
        String cod = new String();
        
        for(int k = frame; k < dna.length(); k+=3){
            if(  k+3 > dna.length()){
                break;                
            }else{
                cod = dna.substring(k, k+3);
            }
            
            if( ! codcount.containsKey(cod)){
                codcount.put(cod, 1);
            }else{
                codcount.put(cod, codcount.get(cod) + 1);
            }
        }
    }
    
    
    private String getMostCommonCodon(){
        String maxcod = new String();
        int max = 0;
        
        for(String x : codcount.keySet()){
            int val = codcount.get(x);
            if( val > max ){
                max = val;
                maxcod = x;
            }
        }
                
        return maxcod;
    }
    
    
    public void printCodonCounts(int start, int end){
        for(String x : codcount.keySet()){
            int val = codcount.get(x);
            if( val >= start && val <= end ){
                System.out.println( x + "\t" + val);
            }
        }
    }
    
    private void printallcodons(){
        for(String x : codcount.keySet()){
            System.out.println( x + "\t" + codcount.get(x));
        }
    }
    
    public void tester(){
        FileResource fh = new FileResource();
        String dna = fh.asString().toUpperCase().trim();
        int s = 7;
        int e = 7;
        for(int k = 0 ; k < 3; k++){
            buildCodonMap(k, dna);
            System.out.println("The Number ofCodons for frame " + k + " are : " + 
                                                 codcount.size());
            String hc = getMostCommonCodon();
            System.out.println("The Codons with the highest count is : " + hc + " with "+codcount.get(hc) + " occurances");
            printCodonCounts(s, e);                        
        }
    }
}
