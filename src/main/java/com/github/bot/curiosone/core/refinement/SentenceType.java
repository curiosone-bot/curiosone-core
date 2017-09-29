package com.github.bot.curiosone.core.refinement;

public enum SentenceType {
  
  Question("?"),
  Answer(".");

  //===============================================================================================
  
  private String mod_end;
  
  private SentenceType(String mod_end) {
    this.mod_end = mod_end;
  }
  
  //===============================================================================================
  
  /**
   * Get the sentence ending.
   * @return ending string
   */
  public String getEnd() {
    return mod_end;
  }
  
  //-----------------------------------------------------------------------------------------------
}
