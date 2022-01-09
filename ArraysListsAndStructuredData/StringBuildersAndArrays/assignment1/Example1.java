
/**
 * Write a description of Example1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;


public class Example1 {
    
    public String stringReverser(String s){
        String ret = "";
        for(int k=0; k < s.length(); k +=1){
            ret = s.charAt(k) + ret;
        }
        return ret;
    }
    
    

}
