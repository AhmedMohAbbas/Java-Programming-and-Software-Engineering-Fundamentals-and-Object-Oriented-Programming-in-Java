
/**
 * Write a description of MatchAllfilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllfilter implements Filter {
    private ArrayList<Filter> filters;
    public MatchAllfilter() {
        filters = new ArrayList<Filter>();
    }
    
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter f : filters){
            if(!f.satisfies(qe)){return false;}            
        }
        return true;
    }
    
    public String getName(){
        StringBuilder x = new StringBuilder();
        for(Filter f : filters){
            x.append(f.getName());
            x.append(" ");
        }
        return x.toString();
        
    }
}
