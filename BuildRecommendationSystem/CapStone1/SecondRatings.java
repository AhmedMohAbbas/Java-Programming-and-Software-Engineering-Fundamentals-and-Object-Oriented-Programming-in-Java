
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile){
        FirstRatings x = new FirstRatings();
        myMovies = x.loadMovies(movieFile);
        myRaters = x.loadRaters(ratingsFile);
    }
    
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> AverageRatings = new ArrayList<Rating>();
        for(Movie x : myMovies){
            String movieId = x.getID();
            double Avgrate = getAverageByID(movieId, minimalRaters);
            AverageRatings.add(new Rating(movieId, Avgrate));            
        }
        return AverageRatings;
    }
    
    public String getTitle(String movieID){
        for (Movie currMovie: myMovies){
            if (currMovie.getID().equals(movieID)){
                return currMovie.getTitle();
            }
        }
        return "N/A";
    }
    
    public String getID(String movieTitle){
        for (Movie currMovie: myMovies){
            if (currMovie.getTitle().equals(movieTitle)){
                return currMovie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
