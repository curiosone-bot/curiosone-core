package com.github.bot.curiosone.core.refinement.inflections;
/**
 * @author Claudio Venanzi
 */

public interface Inflection {
  
  /**
   * Checks the word.
   * @param word form
   * @return true if the word falls into the category
   *
  boolean is(String word);
  
  /**
   * Inflect the word.
   * @param word lemma
   * @return optional inflected word
   *
  Optional<String> of(String word);
  */
  @Override
  String toString();
}
