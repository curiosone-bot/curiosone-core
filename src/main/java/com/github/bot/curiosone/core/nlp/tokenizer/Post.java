package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Part Of Speech (POS) type.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://en.oxforddictionaries.com/grammar/word-classes-or-parts-of-speech
 */

public enum Post {
  /**
   * Noun.
   */
  NOUN,
  /**
   * Verb.
   */
  VERB,
  /**
   * Adjective.
   */
  ADJECTIVE,
  /**
   * Adverb.
   */
  ADVERB,
  /**
   * Pronoun.
   */
  PRONOUM,
  /**
   * Preposition.
   */
  PREPOSITION,
  /**
   * Comjunction.
   */
  CONJUNCTION,
  /**
   * Determiner.
   */
  DETERMINER,
  /**
   * Interjection.
   */
  INTERJECTION
}
