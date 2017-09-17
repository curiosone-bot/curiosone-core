package com.github.bot.curiosone.core.knowledge.interfaces;

/**
 * Graph vertex
 * @author Sordi
 */
public interface Vertex {
  /**
   * Vertex ID Getter
   * @return Vertex ID
   */
  String getId();
  
  /**
   * Weight Getter
   * @return Weight
   */
  Integer getWeight();
  
  /**
   * Weight Setter
   * @param weight
   */
  void setWeight(Integer weight);
}