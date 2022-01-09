
/**
 * Write a description of DistanceFromFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DistanceFromFilter implements Filter{
    private double distMax; 
    private Location from;
    public DistanceFromFilter(double dist, Location city){
        distMax = dist;
        from = city;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(from) < distMax;
    }
    
    public String getName(){return "Distance";}
}
