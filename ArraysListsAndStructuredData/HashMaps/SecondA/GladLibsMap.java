import edu.duke.*;
import java.util.*;

public class GladLibsMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedwords;
    private Random myRandom;
    private int replacementcounter;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibsMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibsMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective","animal","color","country",
                                "fruit","name","noun","verb","timeframe"};
        for(int k = 0; k < categories.length; k++){
            ArrayList<String> x = readIt(source+"/"+categories[k]+".txt");
            myMap.put(categories[k], x);
        }
        usedwords = new ArrayList<String>();
        replacementcounter = 0;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if(myMap.containsKey(label)){
            ArrayList<String> substitutes = myMap.get(label);
            String x = randomFrom(substitutes);
            return x;
        }else if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }     

        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        replacementcounter += 1;
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (true){
            if(usedwords.indexOf(sub) == -1 ){
                usedwords.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first+1,last));
         }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int total = 0;
        for(String k : myMap.keySet()){
            total += myMap.get(k).size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int counter = 0;
        for(String k : myMap.keySet()){
            ArrayList<String> x = myMap.get(k);
            for(int i = 0; i < x.size(); i++){
                if(usedwords.contains(x.get(i))){
                    int len = x.size();
                    counter += len;
                    break;
                }
            }

        }
        return counter;
    }
    
    
    public void makeStory(){
        usedwords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\r\n"+"Number of repalced tags = " + replacementcounter);
        System.out.println("Total Number of all available substitute words: "+
                            totalWordsInMap());
        System.out.println("Total Number of words considered: " + totalWordsConsidered());
    }
    


}
