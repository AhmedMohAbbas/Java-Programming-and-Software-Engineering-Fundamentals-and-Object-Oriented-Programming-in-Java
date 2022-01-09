
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsandfiles;
    
    public WordsInFiles(){
        wordsandfiles = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        String fnam = f.getName();
        FileResource fh = new FileResource(fnam);
        
        for(String word : fh.words()){
            if( ! wordsandfiles.containsKey(word)){
                wordsandfiles.put(word, new ArrayList<String>());
                wordsandfiles.get(word).add(fnam);
            }else{
                if( ! wordsandfiles.get(word).contains(fnam)){
                    wordsandfiles.get(word).add(fnam);
                }                
            }
        }
    }
    
    
    private void buildWordFileMap(){
        wordsandfiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    
    private int maxNumber(){
        int max = 0;
        for(String word : wordsandfiles.keySet()){
            ArrayList<String> files = wordsandfiles.get(word);
            int len = files.size();
            if(len > max){
                max = len;
            }
        }
        return max;
    }
    
    
    private ArrayList<String>  wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        
        for(String word : wordsandfiles.keySet()){
            int numofil = wordsandfiles.get(word).size();
            if(numofil == number){
                words.add(word);
            }
        }
        
        return words;
    }
    
    
    private void printFilesIn(String word){
        ArrayList<String> files = wordsandfiles.get(word);
        System.out.println("The files in which the word '" + word + "' appears are :- " );
        for(int k = 0 ; k < files.size(); k++){
            System.out.println(files.get(k));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        //System.out.println("the word 'sad' appears in the files" +  wordsandfiles.get("sad"));
        int max = maxNumber();
        System.out.println("The highest No. of files any word appears in is : "+ max);
        ArrayList<String> x = wordsInNumFiles(4);
        System.out.println("The number of these words is: " + x.size());
        for(int k = 0; k < x.size(); k++){
            String word = x.get(k);
            printFilesIn(word);
        }
        
        ArrayList<String> y = wordsandfiles.get("tree");
        System.out.println("the files where 'tree' appears:-");
        for(int k = 0; k < y.size(); k++){
            System.out.println(y.get(k));
        }
    }
}
