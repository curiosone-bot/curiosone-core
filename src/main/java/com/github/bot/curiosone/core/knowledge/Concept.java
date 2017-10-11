package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

/**
 * Represents a Concepts of a SemanticNetwork.
 * @see  com.github.bot.curiosone.core.knowledge.SemanticNetwork The SemanticNetwork Class
 * @see  com.github.bot.curiosone.core.knowledge.SemanticRelation The SemanticRelation Class
 */
public class Concept implements Vertex {

  /**
   * Represents the ID of the Concept.
   */
  private final String id;

  /**
   * Constructs this Concept with the given ID.
   * @param  id
   *         ID of this Concept.
   * @see  #id
   */
  public Concept(String id) {
    this.id = id;
  }

  /**
   * Gets the ID of this Concept.
   * @return  the ID of this Concept
   * @see  #id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Returns the String representation of this Concept.
   * @return  the String representation of this Concept
   * @see  #id
   */
  public String toString() {
    return getId();
  }

  /**
   * Checks whether this Concept equals to the given Object.
   * @param  o
   *         the other Concept to be compared against.
   * @return  {@code true} if this Concept equals to the other Concept;
   *          {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    return this.id.equals(((Concept)o).getId());
  }

  /**
   * Returns the HashCode of this Concept.
   * The HashCode depends on the ID of this Concept.
   * @return  the HashCode of this Concept.
   * @see  #id
   */
  @Override
  public int hashCode() {
    int conta = 0;
    for (int x = 0; x < id.length(); x++) {
      conta += id.charAt(x) * 31;
    }
    return conta;
  }
}
