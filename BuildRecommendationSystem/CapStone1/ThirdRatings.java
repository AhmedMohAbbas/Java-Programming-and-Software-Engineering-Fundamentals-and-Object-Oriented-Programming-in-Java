
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){
        FirstRatings x = new FirstRatings();
        myRaters = x.loadRaters(ratingsFile);
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int numRatings = 0;
        double totalScore = 0;
        for (Rater currRater: myRaters){
            ArrayList<String> currMovies = currRater.getItemsRated();
            for (String s: currMovies){
                if (s.equals(id)){
                    numRatings += 1;
                    totalScore += currRater.getRating(id);
                }
            }
        }
        
        if (numRatings < minimalRaters){
            return 0.0;
        } else {
            return totalScore/numRatings;
        }
        
       
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> AverageRatings = new ArrayList<Rating>();
        for(String x : movies){
            double Avgrate = getAverageByID(x, minimalRaters);
            AverageRatings.add(new Rating(x, Avgrate));            
        }
        return AverageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for (String s: movieIDs){
            double ratingValue = getAverageByID(s, minimalRaters);
            averageRatings.add(new Rating(s, ratingValue));
        }
        return averageRatings;
    }
    
}
