package com.github.bot.curiosone.core.refinement;

/**
 * Represents a grammatically refined Sentence.
 * Provides methods to create the grammatically refined Sentence and retrieve its parts.
 */
public class Sentence {

  /**
   * Stores the main Clause of this Sentence.
   */
  private ClauseMain main;

  /**
   * Stores the type of this Sentence.
   */
  private SentenceType type;

  /**
   * Constructs a Sentence with a given type.
   * @param  type
   *         the type of the Sentence
   * @param  main
   *         the main Clause of the Sentence
   */
  public Sentence(SentenceType type, ClauseMain main) {
    this.type = type;
    this.main = main;
  }

  /**
   * Gets the main Clause.
   * @return  the main Clause of this Sentence
   */
  public ClauseMain getMainClause() {
    return main;
  }

  /**
   * Returns a String representation of this Sentence.
   * @return  the String of this Sentence
   */
  @Override
  public String toString() {
    String temp = main.toString() + type.getMark();
    return temp.substring(0, 1).toUpperCase() + temp.substring(1);
  }

}
