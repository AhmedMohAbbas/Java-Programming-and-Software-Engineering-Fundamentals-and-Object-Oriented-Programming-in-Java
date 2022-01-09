
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2 {
    public int howMany(String stringa, String stringb){
        
        int x = stringb.indexOf(stringa);        
        if( x == -1 ){
                return 0;
        }
            
        int count = 1;
        while (true) {            
            x = stringb.indexOf(stringa, x + stringa.length() );
            if( x == -1 ){
                break;
            }
            count = count + 1;
        }
        
        return count;
    }
    
    public void testHowMany(){
        String ex = "ATAAAA";
        int num = howMany("AA",ex);
        System.out.println(num);    
    }
}
