package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Represents an Edge of a Graph.
 */
public interface Edge {

  /**
   * Returns the source of the Edge.
   */
  Vertex getSource();

  /**
   * Returns the Target of the Edge.
   */
  Vertex getTarget();

  /**
   * Returns the type of the Edge.
   */
  SemanticRelationType getType();

  /**
   * Returns the weight of the Edge.
   */
  Integer getWeight();

  /**
   * Sets the weight of the Edge.
   * @param i weight integer value to be set
   */
  void setWeight(Integer i);
}
