package com.github.bot.curiosone.core.refinement;
/**
 * Type of sentence.
 * @author Claudio Venanzi
 */

public enum SentenceType {
  
  Question("?"),
  Answer(".");

  //===============================================================================================
  
  private String end;
  
  private SentenceType(String end) {
    this.end = end;
  }
  
  //===============================================================================================
  
  /**
   * Get the sentence ending.
   * @return ending string
   */
  public String getEnd() {
    return end;
  }
  
  //-----------------------------------------------------------------------------------------------
}
