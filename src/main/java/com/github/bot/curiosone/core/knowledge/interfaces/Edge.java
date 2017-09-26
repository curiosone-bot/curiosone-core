package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Interface for SemanticRelation.
 * @author Christian
 *
 */
public interface Edge {

  /**
   *  Method that returns SemanticRelation source Vertex.
   * @return Object Vertex
   */
  Vertex getSource();

  /**
   *  Method that returns SemanticRelation target Vertex.
   * @return Object Vertex
   */
  Vertex getTarget();

  /**
   *  Method that returns SemanticRelation Type.
   * @return Enum SemanticRelationType
   */
  SemanticRelationType getType();
  
  /**
   * Method that returns SemanticRelation weight.
   * @return Integer weight
   */
  Integer getWeight();
  
  /**
   * Method that sets SemanticRelation weight.
   * @param i weight
   */
  void setWeight(Integer i);
}