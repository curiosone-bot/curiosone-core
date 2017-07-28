package com.github.bot.curiosone.core.wordfetcher;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

import java.io.IOException;
import java.net.URL;

public class WNDict { 

  //===========================================================================
  // STATIC PRIVATE

  /*
   * Singleton instance on class loading is thread-safe
   */
  private static final WNDict INSTANCE = new WNDict();

  //===========================================================================
  // STATIC PUBLIC

  /**
   * Returns the singleton instance.
   * @return object instance
   */
  public static WNDict getInstance() {
    return INSTANCE;
  }
  
  //===========================================================================
  // PRIVATE

  /*
   * Private empty constructor
   */
  private WNDict() {}
  
  /*
   * Starts with an empty dictionary
   */
  private IDictionary dict;

  //===========================================================================
  // PUBLIC

  /**
   * Return the dictionary.
   * @return instance dictionary
   * @throws IOException if the dictionary won't load
   */
  public IDictionary getDictionary() throws IOException {
    
    if (dict == null) {
      
      URL path = new URL("file", null, "src/main/res/dict");
      dict = new Dictionary(path);
      dict.open();
      
    }
    return dict;
  }
}
