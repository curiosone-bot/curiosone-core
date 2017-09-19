package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.List;

public class Sentence {
  
  private List<Word> words;
  
  //===============================================================================================
  
  public Sentence(List<Word> words) {
    words.forEach(this.words::add);
  }
    
  //-----------------------------------------------------------------------------------------------
}
