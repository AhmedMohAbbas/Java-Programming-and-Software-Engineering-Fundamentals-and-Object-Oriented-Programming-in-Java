
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter (String whr , String phrs){
        where = whr;
        phrase = phrs;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String info = qe.getInfo();
        if (where.equals("start")) {
            if(info.startsWith(phrase)){return true;}
        }else if(where.equals("end")){
            if(info.endsWith(phrase)){return true;}
        }else{
            if(info.contains(phrase)){return true;}
        }
        return false;
    }
    
    public String getName(){return "Phrase";}

}
