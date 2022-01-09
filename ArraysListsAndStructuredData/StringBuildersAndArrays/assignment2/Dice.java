
/**
 * Write a description of Dice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Dice {
    public void simpleSimulate(int rolls){
        Random x = new Random();
        int twoes = 0;
        int twelves = 0;
        
        for(int k = 0; k < rolls; k++){
            int d1 = x.nextInt(6) + 1;
            int d2 = x.nextInt(6) + 1;
            if(d1 + d2 == 2){
                twoes += 1;
            }else if(d1 + d2 == 12){
                twelves += 1;
            }
          
        }
        
        
        System.out.println("No. of 2s = \t" + twoes + "\t" + 100.0 * twoes/rolls);
        System.out.println("No. of 12s = \t" + twelves + "\t" + 100.0 * twelves/rolls);
    }
    
    
    public void simulate(int rolls){
        Random x = new Random();
        int [] counts = new int[13];
        
        for(int k = 0; k < rolls; k++){
            int d1 = x.nextInt(6) + 1;//represents a roll ofthe first dice
            int d2 = x.nextInt(6) + 1;//represents a roll ofthe second dice
            counts[d1+d2] += 1 ;          
        }
        
        for(int k = 2; k < counts.length; k++){
            System.out.println("No. of " + k + "s =\t" + counts[k] + "\t" + 100.0 * counts[k]/rolls);
        }        
    }

}
