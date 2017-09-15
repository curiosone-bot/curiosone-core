package com.github.bot.curiosone.core.nlp.tokenizer;

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

  public static PhraseType get(String str) {
    char c = str.charAt(str.length() - 1);
    switch (c) {
      case '?': return QUESTION;
      case '!': return EXCLAMATION;
    }
    return AFFIRMATION;
  }
}
