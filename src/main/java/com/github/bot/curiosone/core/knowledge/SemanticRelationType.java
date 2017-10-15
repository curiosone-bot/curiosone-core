package com.github.bot.curiosone.core.knowledge;

/**
 * Represents a SemanticRelationType.
 */
public enum SemanticRelationType {

  /**
   * Entailment.
   */
  ENTAILMENT,

  /**
   * Hyperonym.
   */
  HYPERNYM,

  /**
   *  What is.
   */
  IS_A,

  /*
   * who
   */
  IS_PERSON,

  /**
   * Where.
   */
  REGION,

  /**
   * Similar.
   */
  SIMILAR_TO,

  /*
   *  Date
   */
  TIME;

  /**
   * Returns a String representation of the SemanticRelationType.
   * @return  a String representation of the SemanticRelationType
   */
  @Override
  public String toString() {
    return this.name();
  }
}
