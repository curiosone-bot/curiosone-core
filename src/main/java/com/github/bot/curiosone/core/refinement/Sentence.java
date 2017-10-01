package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {
  
  private List<Word> words = new ArrayList<>();
  private SentenceType type;
  
  //===============================================================================================
   
  /**
   * Construct a sentence.
   * @param type sentence type
   */
  public Sentence(SentenceType type) {
    this.type = type;
  }

  //-----------------------------------------------------------------------------------------------
  
  /**
   * Add a word.
   * @param word to add
   */
  public void add(Word word) {
    words.add(word);
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Refinement entry point.
   */
  @Override
  public String toString() {
    
    List<Word> temp = new ArrayList<>();
    
    for (Word word : words) {
      switch (word.getPart()) {

        case Noun:          
          break;

        case Verb:
          break;
        
        default:
          temp.add(word);
      }
    }
    
    String temp2 = temp.stream()
        .map(Word::getForm)
        .collect(Collectors.joining(" ", "", type.equals(SentenceType.Question) ? "?" : "."));
    
    return temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
  }
  
  //-----------------------------------------------------------------------------------------------
}
