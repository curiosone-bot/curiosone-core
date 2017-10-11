package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Sentence;

public class SentenceMain implements Sentence {

  private ClauseMain main;
  private SentenceType type;
  
  /**
   * Sentence constructor.
   * @param type type of sentence
   * @param main main clause
   */
  public SentenceMain(SentenceType type, ClauseMain main) {
    this.type = type;
    this.main = main;
  }
  
  /**
   * Returns the main clause.
   * @return clause
   */
  public ClauseMain getMainClause() {
    return main;
  }
  
  /**
   * Refinement entry point.
   */
  @Override
  public String toString() {
    String temp = main.toString() + type.getMark();
    return temp.substring(0, 1).toUpperCase() + temp.substring(1);
  }
  
}
