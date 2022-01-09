
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIdx = from;
        for (int i=from; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }
    
    
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes, int numSorted){
        for(int i = 0; i < (quakes.size() - numSorted); i++ ){
            QuakeEntry q1 = quakes.get(i);
            if(! ((i+1) < quakes.size()) ){continue;}
            QuakeEntry q2 = quakes.get(i+1);
            if(q1.getMagnitude() > q2.getMagnitude()){
                quakes.set(i, q2);
                quakes.set(i+1, q1);           
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int N = in.size();
        int counter= 0;
        for(int k = 0; k < (N-1); k++){
            /*
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }
            System.out.println("Printing Quakes after pass "+(k+1));
            */
            onePassBubbleSort(in, k);
            counter += 1;
            if(checkInSortedOrder(in)){break;}
        }
        System.out.println("the number of passes is "+counter);
    }
    
    
        public boolean checkInSortedOrder(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size(); i++ ){
            QuakeEntry q1 = in.get(i);
            if(! ((i+1) < in.size()) ){continue;}
            QuakeEntry q2 = in.get(i+1);
            if(q1.getMagnitude() > q2.getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in){
        int N = in.size();
        int counter= 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            counter += 1;
            if(checkInSortedOrder(in)){break;}
        }
        System.out.println("the number of passes is "+counter);
    }    
    

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes");
        
       
        /*sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
        
        
        /*sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
        
        
        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        
        
        /*sortByMagnitudeWithCheck (list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
