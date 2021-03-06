
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class MarkovModel extends AbstractMarkovModel{
    private int numoChar;
    public MarkovModel(int N) {
        myRandom = new Random();
        numoChar = N;
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-numoChar);
        String key = myText.substring(index, index+numoChar);
        sb.append(key);
        
        for(int k = 0; k < numChars-numoChar; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
}
