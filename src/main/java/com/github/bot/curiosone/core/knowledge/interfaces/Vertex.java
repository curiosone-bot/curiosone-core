package com.github.bot.curiosone.core.knowledge.interfaces;

/**
 * Intherface for SemanticNetwork Concept.
 * @author Sordi
 */
public interface Vertex {
  /**
   * Method that returns Vertex ID's.
   * @return Vertex ID.
   */
  String getId();
  
  /**
   * Method that returns Vertex weight.
   * @return Weight.
   */
  Integer getWeight();
  
  /**
   * Method that sets Vertex weight.
   * @param weight Integer set.
   */
  void setWeight(Integer weight);
}