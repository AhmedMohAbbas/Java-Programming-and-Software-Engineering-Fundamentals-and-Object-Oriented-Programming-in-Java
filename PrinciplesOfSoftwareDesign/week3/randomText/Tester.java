
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
        FileResource fr = new FileResource();
    	String st = fr.asString();
    	st = st.replace('\n', ' ');
    	MarkovTwo markov = new MarkovTwo();
    	//markov.setRandom(273);
    	markov.setTraining(st);
    	ArrayList<String> x = markov.getFollows("he");
    	System.out.println(x.size());
    	System.out.println(x);
    }

}
