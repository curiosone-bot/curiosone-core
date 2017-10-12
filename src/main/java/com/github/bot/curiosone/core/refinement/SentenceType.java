package com.github.bot.curiosone.core.refinement;

/**
 * Stores the possible types for a Sentence.
 * Provides a method to retrieve the previously set type.
 */
public enum SentenceType {
  Question("?"),
  Answer(".");

  /**
   * Stores the String representation of the character used to identify the type of a Sentence.
   */
  private String mark;

  /**
   * Sets the mark.
   * @param  String
   *         the String representation of the mark for this SentenceType
   */
  private SentenceType(String mark) {
    this.mark = mark;
  }

  /**
   * Gets the ending mark.
   * @return  a String representation of the ending mark
   */
  public String getMark() {
    return mark;
  }
}
