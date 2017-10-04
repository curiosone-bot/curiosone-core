package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

/**
 * represents a Concepts of a SemanticNetwork.
 */
public class Concept implements Vertex {

  /**
   * Represents the ID of the Concept.
   */
  private final String id;

  /**
   * Constructs this Concept with the given ID.
   * @param id ID of this Concept.
   */
  public Concept(String id) {
    this.id = id;
  }

  /**
   * Returns the ID of this Concept.
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Returns a String representation of this Concept.
   */
  public String toString() {
    return getId();
  }

  /**
   * Checks whether this Concept equals to the given Object.
   * @param o the other Concept to be compared against.
   * @return {@code true} if this Concept equals to the other Concept;
   *         {@code false} otherwise
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
