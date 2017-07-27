package com.github.bot.curiosone.core.wordfetcher;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.morph.WordnetStemmer;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Stemmer {
  
  /**
   * Get a base form of a word.
   * @param input non-base word
   * @return base word
   * @throws IOException stuff may go wrong
   */
  public static String toStem(String input) throws IOException {
      
    String path = "src/main/res/dict";
      
    IDictionary dict = new Dictionary(new URL("file", null , path));
    dict.open();
    
    List<String> stems =  new WordnetStemmer(dict).findStems(input, null);
      
    return stems.get(stems.size() - 1);
  }  
}
