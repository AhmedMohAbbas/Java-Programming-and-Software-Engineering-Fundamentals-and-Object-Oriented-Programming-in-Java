
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        String lastWord1 = title1.substring(title1.lastIndexOf(" ")+1);
        String lastWord2 = title2.substring(title2.lastIndexOf(" ")+1);
        if( title1.compareTo(title2) != 0){
            return lastWord1.compareTo(lastWord2);
        }else{
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }        
    }
}
