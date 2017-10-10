package com.github.bot.curiosone.core.refinement;

/**
 * Type of sentence.
 */
public enum SentenceType {
  Question("?"),
  Answer(".");
  
  private String mark;
  
  private SentenceType(String mark) {
    this.mark = mark;
  }
  
  /**
   * Returns the ending mark.
   * @return mark
   */
  public String getMark() {
    return mark;
  }
}

