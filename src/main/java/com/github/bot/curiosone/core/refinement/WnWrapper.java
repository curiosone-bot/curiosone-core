package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.SimpleStemmer;
import edu.mit.jwi.morph.WordnetStemmer;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WnWrapper { 

  private static final WnWrapper INSTANCE = new WnWrapper();
  private static final String  WND_PATH = "src/main/res/dict";  
  private Optional<edu.mit.jwi.IDictionary> dictionary;

  //-----------------------------------------------------------------------------------------------

  private WnWrapper() {
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

  /**
   * Update the dictionary.
   */
  public static final void update() {
    INSTANCE.lift();
    INSTANCE.load();
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Find the stem of a word.
   * @param w word
   * @param p pos
   * @return list of stems
   */
  public static final String getStem(String w, POS p) {
    
    Comparator<String> byLength = (a, b) -> a.length() > b.length() ? 1 : -1;
    
    List<String> stems = INSTANCE.dictionary.isPresent()
        ? new WordnetStemmer(INSTANCE.dictionary.get()).findStems(w, p)
        : new SimpleStemmer().findStems(w, p);

    Optional<String> stem = stems.stream()
        .filter(x -> w.contains(x))
        .min(byLength);
    
    return stem.isPresent() ? stem.get() : w;
  }

  //-----------------------------------------------------------------------------------------------
}
