package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Graph Edge
 * @author Sordi
 */
public interface Edge {

  /**
   * Returns Source Vertex
   * @return Source Vertex
   */
  Vertex getSource();

  /**
   * Returns Target Vertex
   * @return Target Vertex
   */
  Vertex getTarget();

  /**
   * Return Edge type
   * @return Edge type
   */
  SemanticRelationType getType();
}