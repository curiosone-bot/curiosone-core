package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Represents an Edge of a Graph.
 * @see Graph The Graph Interface
 */
public interface Edge {

  /**
   * Gets the source of the Edge.
   * @return  the source of the Edge
   * @see  Vertex The Vertex Interface
   */
  Vertex getSource();

  /**
   * Gets the Target of the Edge.
   * @return  the Target of the Edge
   * @see  Vertex The Vertex Interface
   */
  Vertex getTarget();

  /**
   * Gets the type of the Edge.
   * @return  the type of the Edge
   * @see  SemanticRelationType the SemanticRelationType Enum
   */
  SemanticRelationType getType();

  /**
   * Gets the weight of the Edge.
   * @return  the weight of the Edge
   */
  Integer getWeight();

  /**
   * Sets the weight of the Edge.
   * @param  i
   *         the weight of the Edge
   */
  void setWeight(Integer i);
}
