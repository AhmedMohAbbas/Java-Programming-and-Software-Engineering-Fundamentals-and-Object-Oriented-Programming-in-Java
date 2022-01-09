
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WordPlay {
    public boolean isVowel(char ch){
        char testch = Character.toLowerCase(ch);
        String vowls = "aeiou";
        if(vowls.indexOf(testch) == -1){return false;};
        return true;
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder workpiece = new StringBuilder(phrase);
        for(int i = 0; i < workpiece.length(); i++){
            char letter = workpiece.charAt(i);
            if(isVowel(letter)){
                workpiece.setCharAt(i,ch);
            }
        }
        return workpiece.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder workpiece = new StringBuilder(phrase);
        char x = '+';
        char y = '*';
        for(int i = 0; i < workpiece.length(); i++){
            char letter = workpiece.charAt(i);
            if(Character.toLowerCase(letter) != Character.toLowerCase(ch)){continue;}
            if( (i+1)%2 == 0 ){
                workpiece.setCharAt(i,x);
            }else{
                workpiece.setCharAt(i,y);
            }
        }
        return workpiece.toString();
    }
}
