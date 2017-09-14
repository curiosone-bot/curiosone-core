package com.github.bot.curiosone.core.nlp.tokenizer;

 /**
  * Part Of Speech (POS) type.
  * <li>{@link #AP}</li>
  * <li>{@link #APP}</li>
  * <li>{@link #ADJ}</li>
  * <li>{@link #CONJ}</li>
  * <li>{@link #DET}</li>
  * <li>{@link #INTERJ}</li>
  * <li>{@link #N}</li>
  * <li>{@link #NEG}</li>
  * <li>{@link #NP}</li>
  * <li>{@link #NPP}</li>
  * <li>{@link #NUMB}</li>
  * <li>{@link #PREP}</li>
  * <li>{@link #PRON}</li>
  * <li>{@link #S}</li>
  * <li>{@link #UNKN}</li>
  * <li>{@link #V}</li>
  * <li>{@link #VP}</li>
  * <li>{@link #VPP}</li>
  * @see https://en.oxforddictionaries.com/grammar/word-classes-or-parts-of-speech
  */
public enum PosT {
  /**
   * Adjective Phrase.
   */
  AP,

  /**
   * Adjective preposition phrase.
   */
  APP,

  /**
   * Adjective.
   */
  ADJ,

  /**
   * Adverb.
   */
  ADV,

  /**
   * Conjunction.
   */
  CONJ,

  /**
   * Determiner.
   */
  DET,

  /**
   * Interjections.
   * (bye, goodbye, hello, farewell, hi, so long excuse me, thanks, thank you,
   *  thanks a lot, sorry, pardon)
   */
  INTERJ,

  /**
   * Noun.
   */
  N,

  /**
   * Negation.
   */
  NEG,

  /**
   * Nominal part.
   */
  NP,

  /**
   * Noun preposition phrase.
   */
  NPP,

  /**
   * Number.
   */
  NUMB,

  /**
   * Preposition.
   */
  PREP,

  /**
   * Pronoun.
   */
  PRON,

  /**
   * Sentence.
   */
  S,

  /**
   * Unknown.
   */
  UNKN

  /**
   * Verb.
   */
  V,

  /**
   * Verbal part.
   */
  VP,

  /**
   * Verb preposition phrase.
   */
  VPP,
}
