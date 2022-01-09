
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part2 {
    public String findSimpleGene (String dna, String StCodon, String EndCodon){
        
        if (dna.contains("a")){
            dna = dna.toLowerCase();
            StCodon = StCodon.toLowerCase();
            EndCodon = EndCodon.toLowerCase();
        } 
        
        String result = "";
        
        int startindex = dna.indexOf(StCodon);
        if( startindex == -1 ){
            return "Start Codon Not found";
        }
        
        int endindex = dna.indexOf( EndCodon, startindex + StCodon.length() );
        if( endindex == -1 ){
            return "End Codon Not found";
        }
        
        result = dna.substring( startindex, endindex + EndCodon.length() );
        if( result.length() % 3 != 0 ){
            return "Gene Sequence invalid";
        }
        
        return result;    
    }
    
    public void testSimpleGene (){
        String dna1 = "CCCGCTCAAGCCTGCCGACGTGTCTAAGTGCTACTGTAGAACTTGCATTTAGCAGATGGTTCTG";
        String dna2 = "taatgtacatgactaggwactaaagagctasgdats";
        String dna3 = "AGTAACTTGCATCACCAGTGCGTCACCTCAGAGAAATTAAACGTTTGCATGGCACACCAGTCGC";
        String dna4 = "TGGCTATGATGATTGTACCAGTGGGTTAAGATATTGCCCCTATTGTTTAGTCATAAGTCTACCAG";
        String dna5 = "TGATTGTACCAGGGGTTATAACAGGGTCCATAGGAAATTAAACGTCCCGCTCAAGCCTCGAAAT";
        System.out.println("DNA Strand is:  " + dna1);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna1, "ATG", "TAA"));
        System.out.println("DNA Strand is:  " + dna2);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna2, "ATG", "TAA"));
        System.out.println("DNA Strand is:  " + dna3);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna3, "ATG", "TAA"));
        System.out.println("DNA Strand is:  " + dna4);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna4, "ATG", "TAA"));
        System.out.println("DNA Strand is:  " + dna5);
        System.out.println("Gene Sequence is:  " + findSimpleGene(dna5, "ATG", "TAA"));        
    }
}
