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
   * Sentence type.
   */
  public enum Type {
    Question,
    Answer;
  }
    
  //-----------------------------------------------------------------------------------------------
  
  /**
   * Construct a sentence.
   * @param type sentence type
   * @param words list of words
   */
  public Sentence(Type type, List<Word> words) {
    this.type = type;
    this.words = new ArrayList<>();
    words.forEach(this.words::add);

    this.words.stream().map(Word::toString).forEach(System.out::println);
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
      
      /*
       * words without a part are treated as named entity
       *  and simply added to the refined string
       */
      if (!word.getPart().isPresent()) {
        temp.add(word);
        continue;
      }
      
      /*
       * differentiate processing depending on pos
       */
      switch (word.getPart().get()) {

        case Adjective:
          break;

        case Noun:
          break;

        case Adverb:
          break;

        case Verb:
          break;
          
        default://it must never get there but checkstyle wants it, so let it be.
      }
    }
    
    //finalize
    
    String temp2 = temp.stream()
        .map(x -> x.toString())
        .collect(Collectors.joining(" ", "", type.equals(Type.Question) ? "?" : "."));
    
    return temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
  }
  
  //-----------------------------------------------------------------------------------------------
}
