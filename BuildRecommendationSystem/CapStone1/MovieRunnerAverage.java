
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    private String movieFileName;
    private String ratingFileName;
    private SecondRatings x;
    
    public MovieRunnerAverage(){
        movieFileName = "ratedmoviesfull.csv";
        ratingFileName = "ratings.csv";
        x = new SecondRatings(movieFileName, ratingFileName);
    }
    
    public void printAverageRatings(){
        System.out.println("The number of movies is: " + x.getMovieSize());
        System.out.println("The number of raters is: " + x.getRaterSize());
        int numRating = 12;
        ArrayList<Rating> ratings = x.getAverageRatings(numRating);
        Collections.sort(ratings);
        
        System.out.println("Rating values of Movies with at least " + numRating + " ratings:");
        for (Rating currRating: ratings){
            double currValue = currRating.getValue();
            if(currValue != 0.0){
                String currMovieID = currRating.getItem();
                System.out.println(currValue + "  " + x.getTitle(currMovieID));
            }
        }
        
        int num = 0;
        for (Rating currRating: ratings){
            double currValue = currRating.getValue();
            if(currValue != 0.0){
                num += 1;
            }
        }
        System.out.println("There are " + num + " movies have " + numRating + " ratings.");
    }
    
    public void getAverageRatingOneMovie(String Moviename){
        int numRating = 0;
        ArrayList<Rating> ratings = x.getAverageRatings(numRating);
        
        String movieTitle = Moviename;
        String movieID = x.getID(movieTitle);
        double value = -1;
        for (Rating currRating: ratings){
            if (currRating.getItem().equals(movieID)){
                value = currRating.getValue();
            }
        }
        System.out.println("The average rating for " + movieTitle + " is " + value + ".");
    }

}
