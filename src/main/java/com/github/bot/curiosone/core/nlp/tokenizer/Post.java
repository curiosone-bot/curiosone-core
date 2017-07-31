package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Part Of Speech (POS) type.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://en.oxforddictionaries.com/grammar/word-classes-or-parts-of-speech
 */

public enum Post {
  /**
   * Nominal part.
   */
  NP,
  /**
   * Verbal part.
   */
  VP,
  /**
   * Adjective.
   */
  ADJ,
  /**
   * Adverb.
   */
  ADV,
  /**
   * Pronoun.
   */
  PRON,
  /**
   * Preposition.
   */
  PREP,
  /**
   * Comjunction.
   */
  CONJ,
  /**
   * Determiner.
   */
  DET,
  /**
   * Verb preposition phrase.
   */
  VPP,
  /**
   * Noun.
   */
  N,
  /**
   * Noun preposition phrase.
   */
  NPP,
  /**
   * Negation.
   */
  NEG,
  /**
   * Verb.
   */
  V,
  /**
   * Number.
   */
  NUMB,
  /**
   * Adjective Phrase.
   */
  AP,
  /**
   * Sentence.
   */
  S,
  /**
   * Adjective preposition phrase.
   */
  APP
}
