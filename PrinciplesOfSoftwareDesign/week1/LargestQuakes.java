
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(int howmany){
       EarthQuakeParser parser = new EarthQuakeParser();
       //String source = "data/nov20quakedata.atom";
       String source = "nov20quakedata.atom";//"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       System.out.println("read data for " + list.size());
       /*
       for (QuakeEntry qe : list) {
           System.out.println(qe); 
        }
        */
       
       /*
       int maxQuakeIndex = indexOfLargest(list);
       System.out.println("the index of the largest quake is "+ maxQuakeIndex);
       System.out.println(list.get(maxQuakeIndex));
       */
       
       ArrayList<QuakeEntry> largeQuakes = getLargest(list, howmany);
       for (QuakeEntry qe : largeQuakes) {
           System.out.println(qe); 
        }
             
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        double maxMagni = 0.0;
        for(int k=1; k < data.size(); k++){
            QuakeEntry quake = data.get(k);
            double magni = quake.getMagnitude();
            if (magni > maxMagni) {
                maxIndex = k;
                maxMagni = magni;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        for(int j=0; j < howMany; j++) {
            int index = indexOfLargest(copy);
            answer.add(copy.get(index));
            copy.remove(index);
        }
        
        return answer;
    }
}
