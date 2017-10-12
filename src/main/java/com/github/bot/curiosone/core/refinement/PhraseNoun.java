package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

/**
 * Represents a Noun in a Phrase.
 * Provides a method to create the Noun and to get its String representation.
 */
public class PhraseNoun implements Phrase {

  /**
   * Stores the Noun of the Phrase.
   */
  private WordNoun main;

  /**
   * Constructs this PhraseNoun with a given Noun.
   * @param  WordNoun
   *         the  given Noun
   * @see  WordNoun The WordNoun Class
   */
  public PhraseNoun(WordNoun main) {
    this.main = main;
  }

  /**
   * Returns a String representation of this PhraseNoun.
   * @return  a String representation of this PhraseNoun
   */
  @Override
  public String toString() {
    return main.toString();
  }

}
