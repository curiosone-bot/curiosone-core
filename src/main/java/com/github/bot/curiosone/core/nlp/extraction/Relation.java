package com.github.bot.curiosone.core.nlp.extraction;

/**
 * Semantic relation.
 *
 */
public enum Relation {
  
  /**
   * Hypernymy
   */
  IS_A,
  
  /**
   * Location.
   */
  PLACE,
  
  /**
   * Circumstance.
   */
  TIME,
  
  /**
   * Role.
   */
  IS_PERSON,
  
  /**
   * Unknown.
   */
  UNKNOWN;

}
