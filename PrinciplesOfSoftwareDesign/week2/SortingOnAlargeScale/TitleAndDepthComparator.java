
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        if( title1.compareTo(title2) != 0){
            return title1.compareTo(title2);
        }else{
            return Double.compare(q1.getDepth(), q2.getDepth());
        }        
    }
}
