package com.github.bot.curiosone.core.knowledge;

/**
 * Semantic Relation Type that can be used between two Concepts.
 * @author Christian Sordi
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