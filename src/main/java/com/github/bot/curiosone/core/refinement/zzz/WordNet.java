package com.github.bot.curiosone.core.refinement.zzz;

import edu.mit.jwi.morph.SimpleStemmer;
import edu.mit.jwi.morph.WordnetStemmer;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WordNet { 

  private static final WordNet INSTANCE = new WordNet();
  private static final String  WND_PATH = "src/main/res/dict";  
  private Optional<edu.mit.jwi.IDictionary> dictionary;

  //-----------------------------------------------------------------------------------------------

  private WordNet() {
    load();
  }

  //-----------------------------------------------------------------------------------------------

  private void load() {
    try {
      dictionary = Optional.of(new edu.mit.jwi.Dictionary(new URL("file", null, WND_PATH)));
      dictionary.get().open();
    } catch (Exception e) {
      dictionary = Optional.empty();
    }
  }
  
  //-----------------------------------------------------------------------------------------------

  private void lift() {
    if (dictionary.isPresent()) {
      if (dictionary.get().isOpen()) {
        dictionary.get().close();
        dictionary = Optional.empty();
      }
    }
  }

  //===============================================================================================

  public static final WordNet getInstance() {
    return INSTANCE;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Update the dictionary.
   */
  public final void update() {
    lift();
    load();
  }

  //-----------------------------------------------------------------------------------------------
  
  /** NOT WORKING SINCE WordPart DOES NOT CONTAIN A POS MAPPING FOR WORDNET ANYMORE.
   * 
   * Find the stem of a word.
   * @param w word
   * @param p part
   * @return list of stems
   *
  public final String getStem(String w, WordPart p) {
    
    Comparator<String> byLength = (a, b) -> a.length() > b.length() ? 1 : -1;
    
    List<String> stems = dictionary.isPresent()
        ? new WordnetStemmer(dictionary.get()).findStems(w, p.forWordNet())
        : new SimpleStemmer().findStems(w, p.forWordNet());
    
    Optional<String> stem = stems.stream()
        .filter(x -> w.contains(x))
        .min(byLength);
    
    return stem.isPresent() ? stem.get() : w;
  }*/

  //-----------------------------------------------------------------------------------------------
}
