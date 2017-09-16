package com.github.bot.curiosone.core.nlp.base;

/**
* Different typology of sentence.
*/

public enum PhraseType {

  /**
   * Sentence in input is a question.
   */
  QUESTION,

  /**
   * Sentence in input is an affirmation.
   */
  AFFIRMATION,

  /**
   * Sentence in input is an exclamation.
   */
  EXCLAMATION;

  /**
   * Gets the phrase type for the given phrase.
   * @param str a String containing the Phrase to be examinated.
   * @return a String containing the phrase type for the given phrase.
   * @see PhraseType
   */
  public static PhraseType get(String str) {
    char c = str.charAt(str.length() - 1);
    switch (c) {
      case '?': return QUESTION;
      case '!': return EXCLAMATION;
      default: return AFFIRMATION;
    }
  }
}
