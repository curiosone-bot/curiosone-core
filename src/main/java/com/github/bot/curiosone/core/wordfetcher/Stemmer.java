package com.github.bot.curiosone.core.wordfetcher;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.morph.WordnetStemmer;

import java.io.IOException;
import java.util.List;

public class Stemmer {
  
  /**
   * Gets a base form of a word.
   * @param input non-base word
   * @return base word
   * @throws IOException stuff may go wrong
   */
  public static String toStem(String input) throws IOException {
    
    IDictionary dict = WDictionary.getInstance().getDictionary();
    
    List<String> stems = new WordnetStemmer(dict).findStems(input, null);
    
    stems.forEach(System.out::println);
    return stems.get(stems.size() - 1);
  }  
}
