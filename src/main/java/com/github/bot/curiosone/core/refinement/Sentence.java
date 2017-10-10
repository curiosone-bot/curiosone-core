package com.github.bot.curiosone.core.refinement;

public class Sentence {

  private MainClause main;
  private SentenceType type;
  
  /**
   * Sentence constructor.
   * @param type type of sentence
   * @param main main clause
   */
  public Sentence(SentenceType type, MainClause main) {
    this.type = type;
    this.main = main;
  }
  
  /**
   * Returns the main clause.
   * @return clause
   */
  public MainClause getMainClause() {
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
