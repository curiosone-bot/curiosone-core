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
}