package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Sentence {
  
  private List<Word>  words;
  private SentenceType type;
  
  //===============================================================================================

  /**
   * Construct an empty sentence.
   * @param type sentence type
   */
  public Sentence(SentenceType type) {
    this.words = new LinkedList<>();
    this.type  = type;
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Construct a sentence.
   * @param type sentence type
   * @param words list of words
   */
  public Sentence(SentenceType type, List<Word> words) {
    this(type);
    words.forEach(word -> add(word));
  }
  
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Append a word.
   * @param w word
   */
  public void add(Word w) {
    words.add(w);
  }
  
  //-----------------------------------------------------------------------------------------------

  /**
   * Get the words.
   * @return words
   */
  public Stream<Word> getWords() {
    return words.stream();
  }
    
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Get the sentence type.
   * @return type
   */
  public SentenceType getType() {
    return type;
  }
    
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Refinement entry point.
   */
  public void refine() {
    
  }
}
