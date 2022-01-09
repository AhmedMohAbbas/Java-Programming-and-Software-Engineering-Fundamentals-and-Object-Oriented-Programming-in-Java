
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
    private ArrayList<String> chNames;
    private ArrayList<Integer> chFreq;
    public CharactersInPlay(){
        chNames = new ArrayList<String>();
        chFreq = new ArrayList<Integer>();
    }
    
    private void update(String person ){
        if ( ! chNames.contains(person)){
            chNames.add(person);
            chFreq.add(1);
        }else {
            int x =chNames.indexOf(person);
            int freq = chFreq.get(x);
            chFreq.set(x,freq+1);
        }
    }
    
    private void findAllCharacters(){
        chNames.clear();
        chFreq.clear();
        FileResource fh = new FileResource();
        for(String line : fh.lines()){
            line = line.toLowerCase();
            int pind = line.indexOf(".");
            if(pind != -1){
                String name = line.substring(0, pind);
                update(name);
            }            
        }
    }
    
    private void charactersWithNumParts(int num1, int num2){
        System.out.println("characters who spoke less than "+ num2 + " times, and more than "+num1+" times are :-");
        for(int i = 0; i < chNames.size(); i++){
            int number = chFreq.get(i);
            if(number >= num1 && number <= num2){
                System.out.println(chFreq.get(i)+"\t"+ chNames.get(i));
            }            
        }
    }
    
    private int findMax(){
        int max = chFreq.get(0);
        int maxIndex = 0;
        for(int k=0; k < chFreq.size(); k++){
            if (chFreq.get(k) > max){
                max = chFreq.get(k);
                maxIndex = k;
             }
        }
        return maxIndex;
    }
    
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < chNames.size(); i++){
            System.out.println(chFreq.get(i)+"\t"+ chNames.get(i));
        }
        charactersWithNumParts(40,102);
        int index = findMax();
        System.out.println("The Character that spoke most often and its count are: "+ chNames.get(index)+" "+chFreq.get(index));
    }
}
