package com.github.bot.curiosone.core.wordfetcher;

import edu.mit.jwi.morph.SimpleStemmer;
import edu.mit.jwi.morph.WordnetStemmer;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Stemmer { 

  /*
   * Singleton instance on class loading is thread-safe
   */
  private static final Stemmer INSTANCE = new Stemmer();
  
  //-----------------------------------------------------------------------------------------------
  
  /*
   * Path of Wordnet database files
   */
  private static final String WND_PATH = "src/main/res/dict";

  //===============================================================================================
  
  /*
   * Private constructor
   */
  private Stemmer() {
    load();
  }

  //-----------------------------------------------------------------------------------------------
  
  /*
   * Private dictionary
   */
  private Optional<edu.mit.jwi.IDictionary> dictionary;

  //-----------------------------------------------------------------------------------------------
  
  /*
   * Open dictionary
   */
  private void load() {

    try {
      dictionary = Optional.of(new edu.mit.jwi.Dictionary(new URL("file", null, WND_PATH)));
      dictionary.get().open();
      
    } catch (Exception e) {
      dictionary = Optional.empty();
    }
  }
  
  //-----------------------------------------------------------------------------------------------

  /*
   * Remove dictionary
   */
  private void lift() {
    
    if (dictionary.isPresent()) {
      if (dictionary.get().isOpen()) {
        dictionary.get().close();
        dictionary = Optional.empty();
      }
    }
  }

  //===============================================================================================

  /**
   * Update the dictionary.
   */
  public static final void update() {
    INSTANCE.lift();
    INSTANCE.load();
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Returns the root of a word.
   * @param word surface form
   * @return root form
   */
  public static final Optional<String> search(String word) {
    
    List<String> stems;
    
    stems = INSTANCE.dictionary.isPresent()
          ? new WordnetStemmer(INSTANCE.dictionary.get()).findStems(word, null)
          : new SimpleStemmer().findStems(word, null);
    
    Comparator<String> longer = (a, b) -> a.length() > b.length() ? 1 : -1; 
    return stems.stream().min(longer);
  }
}
