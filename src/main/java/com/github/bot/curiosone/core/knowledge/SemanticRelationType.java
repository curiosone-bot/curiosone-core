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

  @Override
  public String toString() {
    return this.name();
  }
}
