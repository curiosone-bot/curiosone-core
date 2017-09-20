package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.LinkedList;
import java.util.List;

public class Sentence {
  
  private List<Word> words = new LinkedList<>();

  private void add(Word w, int i) {
    words.add(i, w);
  }
  
  //===============================================================================================
  
  /**
   * Append a word.
   * @param w word
   */
  public void add(Word w) {
    words.add(w);
  }    
    
  //-----------------------------------------------------------------------------------------------
}
