import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    private int maxligblewords = 0;
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices ){
            slice.append(message.charAt(i));            
        }        
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker x = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String keyedLetrs = sliceString(encrypted, i, klength);
            int instanceKey = x.getKey(keyedLetrs);
            key[i] = instanceKey;
        }
        return key;
    }
    
    
    public HashSet<String> readDictionary(FileResource fh){
        HashSet<String> LangWords = new HashSet<String>();
        for(String line : fh.lines()){
            String x = line.toLowerCase();
            LangWords.add(x);            
        }
        return LangWords;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W", -1);
        int count = 0;
        for(int i = 0; i < words.length; i++){
            if(dictionary.contains(words[i].toLowerCase())){
                count = count +1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char commonLetter){
        int max = 0;
        int z = 0;
        String TheMessage = new String();
        for(int i = 1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, commonLetter);
            VigenereCipher x = new VigenereCipher(keys);
            String decryptedmessage = x.decrypt(encrypted);
            int legibleWords = countWords(decryptedmessage, dictionary);
            if(legibleWords > max){
                max = legibleWords;
                TheMessage = decryptedmessage;
                z = i;
            }
        }
        maxligblewords = max;
        //System.out.println(max);
        //System.out.println(z);
        return TheMessage;
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        char mstcmn = 'e';
        int max = 0;
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
        for(String x : dictionary){
            char[] letters = x.toCharArray();
            for(int i = 0; i < letters.length; i++){
                char letr = Character.toLowerCase(letters[i]);
                if( ! counter.containsKey(letr)){
                    counter.put(letr, 1);
                }else{
                    counter.put(letr, counter.get(letr) + 1);
                }
            }
        }
        
        for(Character y : counter.keySet()){
            int val = counter.get(y);
            if(val > max){
                max = val;
                mstcmn = y;
            }
        }
        
        return mstcmn;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxleg = 0;
        String TheMessage = new String();
        String lango = new String();
        for(String lang : languages.keySet()){
            HashSet<String> langdict = languages.get(lang);
            char cmnltr = mostCommonCharIn(langdict);
            String ADecryption = breakForLanguage(encrypted, langdict, cmnltr);
            int val = maxligblewords;
            if(val > maxleg){
                maxleg = val;
                TheMessage = ADecryption;
                lango = lang;
            }            
        }
        System.out.println(maxleg);
        System.out.println(lango);
        System.out.println(TheMessage);
    }

    public void breakVigenere () {
        FileResource fh = new FileResource();
        String message = fh.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String fnam = f.getName();
            FileResource fh2 = new FileResource("dictionaries/"+fnam);
            HashSet<String> langdict = readDictionary(fh2);
            languages.put(fnam, langdict );            
        }
        breakForAllLangs(message, languages);
        


    }
    
}
