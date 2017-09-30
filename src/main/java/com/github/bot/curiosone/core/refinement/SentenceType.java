package com.github.bot.curiosone.core.refinement;
/**
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
