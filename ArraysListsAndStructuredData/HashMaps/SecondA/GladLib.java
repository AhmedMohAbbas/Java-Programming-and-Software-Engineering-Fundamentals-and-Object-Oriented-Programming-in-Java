import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruiteList;
    private ArrayList<String> usedwords;
    private Random myRandom;
    private int replacementcounter;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt") ;
        fruiteList = readIt(source+"/fruit.txt") ;
        usedwords = new ArrayList<String>();
        replacementcounter = 0;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
     private String getSubstitute(String label) {
        if (label.equals("country")) {
            String x = randomFrom(countryList);
            return x;
        }else if (label.equals("color")){
            String x = randomFrom(colorList);
            return x ;
        }else if (label.equals("noun")){
            String x = randomFrom(nounList);
            return x ;
        }else if (label.equals("name")){
            String x = randomFrom(nameList);
            return x ;
        }else if (label.equals("adjective")){
            String x = randomFrom(adjectiveList);
            return x ;
        }else if (label.equals("animal")){
            String x = randomFrom(animalList);
            return x ;
        }else if (label.equals("timeframe")){
            String x = randomFrom(timeList);
            return x ;
        }else if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }else if(label.equals("verb")){
            String x = randomFrom(verbList);
            return x ;
        }else if(label.equals("fruit")){
            String x = randomFrom(fruiteList);
            return x ;
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
    
    public void makeStory(){
        usedwords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\r\n"+"Number of repalced tags = " + replacementcounter);
    }
    


}
