package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Type of String in Dictionary call. It is used for organize the calling to the Dictionary.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see Token
 */
public enum PositionT {

  /**
   * The previous string of ELE.
   * @see #ELE
   */

  PRE,

  /**
   * String to analyze.
   */

  ELE,

  /**
   * The following string of ELE.
   * @see #ELE
   */

  POST
}