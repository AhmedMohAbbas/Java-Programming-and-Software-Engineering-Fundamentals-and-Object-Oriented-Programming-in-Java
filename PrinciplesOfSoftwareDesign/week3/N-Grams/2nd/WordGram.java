
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < myWords.length; i++){
            ret = ret + myWords[i] + " ";            
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){return false;}else{
            for(int i = 0; i < myWords.length; i++){
                if( ! (myWords[i].equals(other.wordAt(i))) ){return false;}
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        out.myWords[myWords.length - 1] = word;
        for(int i = myWords.length -2; i >= 0; i--){
            out.myWords[i] = this.myWords[i+1];
        }
        return out;
    }
    
    public int hashCode () {
        return this.toString().hashCode();
    }
}