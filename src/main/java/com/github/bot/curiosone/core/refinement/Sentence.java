package com.github.bot.curiosone.core.refinement;

public class Sentence {

  private MainClause main;
  private Type type;
  
  /**
   * Type of sentence.
   */
  public enum Type {
    Question,
    Answer;
  }
  
  /**
   * Sentence constructor.
   * @param type type of sentence
   * @param main main clause
   */
  public Sentence(Type type, MainClause main) {
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
    String temp = main.toString();
    temp += type.equals(Type.Question) ? "?" : ".";
    return temp.substring(0, 1).toUpperCase() + temp.substring(1);
  }
  
}
