package com.github.bot.curiosone.core.wordfetcher;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class WordnetDictionary { 

  /*
   * Singleton instance on class loading is thread-safe
   */
  private static final WordnetDictionary INSTANCE = new WordnetDictionary();

  //-----------------------------------------------------------------------------------------------
  
  /*
   * Private empty constructor
   */
  private WordnetDictionary() {
    WordnetDictionary.load();
  }

  //-----------------------------------------------------------------------------------------------
  
  /*
   * Starts with an empty dictionary
   */
  private Optional<edu.mit.jwi.IDictionary> dictionary = Optional.empty();

  //===============================================================================================

  /**
   * Loads the dictionary.
   */
  public static final void load() {
    
    INSTANCE.dictionary.ifPresent(x -> x.close());
    
    try {
      URL path = new URL("file", null, "src/main/res/dict");
      INSTANCE.dictionary = Optional.of(new edu.mit.jwi.Dictionary(path));
      INSTANCE.dictionary.get().open();
      
    } catch (IOException e) {
      INSTANCE.dictionary = Optional.empty();
    }
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns a WordnetWord object.
   * @param word the word
   * @return associated object
   */
  public static final WordnetWord seek(String word) {
    return null;
  }
}
