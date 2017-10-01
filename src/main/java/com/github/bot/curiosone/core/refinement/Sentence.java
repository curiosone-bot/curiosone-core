package com.github.bot.curiosone.core.refinement;
/**
 * @author Claudio Venanzi
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {
  
  private List<Word> words;
  private Type type;
  
  //===============================================================================================
   
  /**
   * Construct a sentence.
   * @param type sentence type
   * @param words list of words
   */
  public Sentence(Type type, List<Word> words) {
    this.type = type;
    this.words = new ArrayList<>();
    words.forEach(this.words::add);
  }

  //-----------------------------------------------------------------------------------------------

  /**
   * Refinement entry point.
   */
  public String refine() {
    
    //initialize
    
    List<Word> temp = new ArrayList<>();
    
    //compose
    
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
    
    //finalize
    
    String temp2 = temp.stream()
        .map(Word::getForm)
        .collect(Collectors.joining(" ", "", type.equals(Type.Question) ? "?" : "."));
    
    return temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
  }
  
  //###############################################################################################

  /**
   * Sentence type.
   */
  public enum Type {
    Question,
    Answer;
  }
}
