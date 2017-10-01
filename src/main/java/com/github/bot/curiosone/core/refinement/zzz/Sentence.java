package com.github.bot.curiosone.core.refinement.zzz;
/**
 * @author Claudio Venanzi
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {
  
  private List<Token> tokens = new ArrayList<>();
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
  public void add(Token word) {
    tokens.add(word);
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Refinement entry point.
   */
  @Override
  public String toString() {
    
    String temp = tokens.stream()
        .flatMap(Token::refine)
        .map(Token::toString)
        .collect(Collectors.joining(" ", "", type.equals(SentenceType.Question) ? "?" : "."));
    
    return temp.substring(0, 1).toUpperCase() + temp.substring(1);
  }
  
  //-----------------------------------------------------------------------------------------------
}
