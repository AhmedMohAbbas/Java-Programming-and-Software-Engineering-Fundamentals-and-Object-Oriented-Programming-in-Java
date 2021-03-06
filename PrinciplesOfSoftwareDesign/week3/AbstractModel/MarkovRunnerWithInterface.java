
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        IMarkovModel mz = new MarkovZero();
        runModel(mz, st, size,1024);/*
    
        IMarkovModel mOne = new MarkovOne();
        runModel(mOne, st, size,42);
        
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,42);
        
        IMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size,42);*/
        
        //IMarkovModel mz = new EfficientMarkovModel(5);
        //runModel(mz, st, size,615);

    }

    private void printOut(String s){
    	String[] words = s.split("\\s+");
    	int psize = 0;
    	System.out.println("----------------------------------");
    	for(int k=0; k < words.length; k++){
    		System.out.print(words[k]+ " ");
    		psize += words[k].length() + 1;
    		if (psize > 60) {
    			System.out.println();
    			psize = 0;
    		}
    	}
    	System.out.println("\n----------------------------------");
    }
	
}
