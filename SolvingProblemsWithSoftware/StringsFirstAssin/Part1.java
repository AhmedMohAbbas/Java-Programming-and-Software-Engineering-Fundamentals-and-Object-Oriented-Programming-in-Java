
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part1 {
    public String findSimpleGene (String dna){
        String result = "";
        int startindex = dna.indexOf("ATG");
        if( startindex == -1 ){
            return "Start Codon Not found";
        }
        int endindex = dna.indexOf( "TAA", startindex + 3 );
        if( endindex == -1 ){
            return "End Codon Not found";
        }
        result = dna.substring( startindex, endindex + 3 );
        if( result.length() % 3 != 0 ){
            return "Gene Sequence invalid";
        }
        return result;    
    }
    
    public void testSimpleGene (){
        String dna1 = "CCCGCTCAAGCCTGCCGACGTGTCTAAGTGCTACTGTAGAACTTGCATTTAGCAGATGGTTCTG";
        String dna2 = "AAATGCCCTAACTAGATTAAGAAACC";
        
        System.out.println("DNA Strand is:  " + dna1);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna1));
        System.out.println("DNA Strand is:  " + dna2);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna2));
                
    }
}
