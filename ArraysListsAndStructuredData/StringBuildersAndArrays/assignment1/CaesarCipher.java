
import java.util.*;
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;


public class CaesarCipher {
        public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char testchar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(testchar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }else{
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaesar() {
        //FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";//fr.asString();
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);//for de-cryption.
        //System.out.println(decrypted);
    }
    
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char testchar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(testchar);
            if(idx != -1){
                if( i%2 == 0 ){
                    char newChar1 = shiftedAlphabet1.charAt(idx);
                    if(Character.isLowerCase(currChar)){
                        newChar1 = Character.toLowerCase(newChar1);
                        encrypted.setCharAt(i, newChar1);
                    }else{
                        encrypted.setCharAt(i, newChar1);
                    }
                }else{
                    char newChar2 = shiftedAlphabet2.charAt(idx);
                    if(Character.isLowerCase(currChar)){
                        newChar2 = Character.toLowerCase(newChar2);
                        encrypted.setCharAt(i, newChar2);
                    }else{
                        encrypted.setCharAt(i, newChar2);
                    }
                }
            }
        }
        return encrypted.toString();
    }
        public void testTwoKeys() {
        //FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";//fr.asString();
        int key1 = 8;
        int key2 = 21;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);//for de-cryption.
        //System.out.println(decrypted);
    }
}

