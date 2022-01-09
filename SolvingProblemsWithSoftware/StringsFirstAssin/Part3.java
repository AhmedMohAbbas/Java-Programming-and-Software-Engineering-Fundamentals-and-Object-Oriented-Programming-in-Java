
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part3 {
    public boolean twoOccurrences ( String stringa, String stringb ){
        int x = stringb.indexOf(stringa);
        
        if( x == -1 ){
                return false;
        }
            
        int count = 1;
        while (true) {            
            x = stringb.indexOf(stringa, x + stringa.length() );
            if( x == -1 ){
                break;
            }
            count = count + 1;
        }
        
        if(count < 2){
            return false;
        }
        
        return true;    
   }
     
   public String lastPart(String stringa, String stringb){
       int x = stringb.indexOf(stringa);
       String rem;
        
        if( x == -1 ){
                return stringb;
       }
       else{
           rem = stringb.substring(x + stringa.length(), stringb.length());           
       }
       
       return rem;
   }
   
   public void testing(){
       String ex1 = "Banana";
       String ex101 = "an";
       boolean a = twoOccurrences(ex101, ex1);
       String b = lastPart(ex101, ex1);
       System.out.println(ex1+" Contains the string('" + ex101 + "') at least twice:" + a);
       System.out.println("The part after '" + ex101 + "' in '"+ ex1 + "' is : " + b ); 
   }
}
   
