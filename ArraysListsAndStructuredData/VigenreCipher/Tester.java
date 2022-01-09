
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testCeCiph(){
        FileResource fh = new FileResource();
        String message = fh.asString();
        char msgltr = 'a';
        CaesarCipher x = new CaesarCipher(7);
        String encryptedmessage =x.encrypt(message); 
        System.out.println( encryptedmessage +"\r\n\r\n");
        System.out.println(x.decrypt(encryptedmessage)+"\r\n\r\n");
        System.out.println(x.encryptLetter(msgltr));
        System.out.println(x.decryptLetter(x.encryptLetter(msgltr)));
    }
    
    public void testCesCrack(){
        FileResource fh = new FileResource();
        String message = fh.asString();
        CaesarCracker x = new CaesarCracker();
        CaesarCracker y = new CaesarCracker('a');
        String crackedmessage =y.decrypt(message);
        int messageKey = y.getKey(message);
        System.out.println("The key is : " + messageKey + " and the message is:-");
        System.out.println(crackedmessage);
    }
    
    public void testVigCiph(){
        FileResource fh = new FileResource();
        String message = fh.asString();
        int[] rome = {17, 14, 12, 4};
        VigenereCipher x = new VigenereCipher(rome);
        System.out.println(x.encrypt(message));
        System.out.println(x.decrypt(x.encrypt(message)));
    }
    
    public void testVigCracker(){
        FileResource fh = new FileResource();
        String message = fh.asString();
        VigenereBreaker x = new VigenereBreaker();
        int[] key = x.tryKeyLength(message, 5, 'e');
        System.out.println(Arrays.toString(key));
    }
    
    public void testesttest(){
        VigenereBreaker x = new VigenereBreaker();
        FileResource fh2 = new FileResource();
        HashSet<String> dict = new HashSet<String>();
        dict = x.readDictionary(fh2);
        x.mostCommonCharIn(dict);
    }

}
