package com.github.bot.curiosone.core.wordfetcher;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.IStemmer;
import edu.mit.jwi.morph.WordnetStemmer;

public class Stemmer {

    /**
     * 
     * @param s
     * @return
     * @throws IOException
     */
    public static String toStem(String input) throws IOException {
      
      String path = "src/main/res/dict";
      
      System.out.println("a");
      IDictionary dict = new Dictionary(new URL("file", null , path)); dict.open();
      System.out.println("b");
      List<String> stems =  new WordnetStemmer(dict).findStems(input, null);
      System.out.println("c");
      
      return stems.get(stems.size() - 1);
    }
    
}
