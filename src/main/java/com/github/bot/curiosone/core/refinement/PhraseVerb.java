package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

import java.util.Optional;

/**
 * Represents a Phrase with a Verb and an object.
 * Provides methods to create a verb only Phrase or a verb + object Phrase.
 */
public class PhraseVerb implements Phrase {

  /**
   * Stores the verb of this Phrase.
   */
  private WordVerb main;

  /**
   * Stores the object of this Phrase.
   * If the object is absent, then this instance will be empty.
   */
  private Optional<PhraseNoun> object;

  /**
   * Constructs a Phrase with a given verb and its object.
   * @param  main
   *         the verb of this Phrase
   * @param  dependents
   *         the object of the given verb
   * @see  WordVerb The WordVerb Class
   * @see  PhraseNoun The PhraseNoun Class
   */
  public PhraseVerb(WordVerb main, PhraseNoun dependents) {
    this.main = main;
    this.object = Optional.of(dependents);
  }

  /**
   * Constructs a Phrase with a given verb.
   * @param  main
   *         the verb of this Phrase
   * @see  WordVerb The WordVerb Class
   * @see  PhraseNoun The PhraseNoun Class
   */
  public PhraseVerb(WordVerb main) {
    this.main = main;
    this.object = Optional.empty();
  }

  /**
   * Returns a String representation of this Phrase, in the form: [verb object].
   * If the object is not present, the String will be formatted as: [verb ].
   * @return  a String representation of this Phrase
   */
  @Override
  public String toString() {
    return main.toString() + (object.isPresent() ? " " + object.get().toString() : "");
  }

}
