
/**
 * Write a description of DecryptingCeaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;
public class DecryptingCeaser {
    public int[] countLetters(String cipher){
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for(int i = 0; i < cipher.length(); i++){
            char ch = Character.toUpperCase(cipher.charAt(i));
            int ind = alph.indexOf(ch);
            if(ind != -1){
                counts[ind] += 1;
            }            
        }
        return counts;
    }
    public int maxIndex(int[] x){
        int max = 0;
        int maxind = 0;
        int val = 0;
        for(int i =0; i < x.length; i++){
            val = x[i];
            if(val > max){
                max = val;
                maxind = i;
            }            
        }
        return maxind;
    }
    
    public String decrypt(String encrypted){
        Ceaser x = new Ceaser();
        int[] freq = countLetters(encrypted);
        int maxind = maxIndex(freq);
        int dkey = maxind - 4;
        if(maxind < 4){
            dkey = 26 - (4 - maxind);
        }
        System.out.println(dkey);
        return x.encrypt(encrypted, 26-dkey);        
    }
        public void Decrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = decrypt(message);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);//for de-cryption.
        //System.out.println(decrypted);
    }
    
    
    public String twoKeyDecrypt(String encrypted){
        Ceaser x = new Ceaser();
        StringBuilder workpiece1 = new StringBuilder() ;
        StringBuilder workpiece2 = new StringBuilder();        
        StringBuilder total = new StringBuilder();
        int counter1 = 0;
        int counter2 = 0;
        
        for(int i = 0; i < encrypted.length(); i++) {
            if( i%2 == 0 ){
                workpiece1.append(encrypted.charAt(i));
            }else{
                workpiece2.append(encrypted.charAt(i));
                    }
        }
        
        String part1 = decrypt(workpiece1.toString());
        String part2 = decrypt(workpiece2.toString());
        
        for(int i = 0; i < encrypted.length(); i++) {
            if( i%2 == 0 ){
                total.append(part1.charAt(counter1++));
            }else{
                total.append(part2.charAt(counter2++));
            }
        }
        System.out.println(total.toString());
        return total.toString();
    }
    public void TesttwoKeyDecrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = twoKeyDecrypt(message);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);//for de-cryption.
        //System.out.println(decrypted);
    }

}
