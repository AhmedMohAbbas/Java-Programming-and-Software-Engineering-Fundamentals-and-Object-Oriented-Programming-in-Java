
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int leng;
        for(String word : resource.words()){        
            if(Character.isLetter(word.charAt(0)) == false && Character.isLetter(word.charAt(word.length() - 1)) == false){
                leng = word.length() - 2;
            }else if(Character.isLetter(word.charAt(0)) == false || Character.isLetter(word.charAt(word.length() - 1)) == false){
                leng = word.length() - 1;
            }else{
                leng = word.length();
            }
            
            if(leng < counts.length && leng >=0 ){
                counts[leng] += 1;
            }else if(leng < 0){
                counts[0] += 1;
            }else{
                counts[counts.length - 1] += 1;
            }    
        }
    }
    public void testCountWordLengths(){
        FileResource fh = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fh, counts);
        
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > 0){
                System.out.println(counts[i]+ " words of length " + i);
            }            
        }
        System.out.println("The most common word length is  "+ indexOfMax(counts));
        
    }
    
    
    public int indexOfMax(int[] values){
        int max = 0;
        int maxind = 0;
        int val = 0;
        for(int i =0; i < values.length; i++){
            val = values[i];
            if(val > max){
                max = val;
                maxind = i;
            }            
        }
        return maxind;
    }
}
