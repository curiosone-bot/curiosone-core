package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

/**
 * This class represents a Concept of a Semantic Network.
 * @author Christian Sordi.
 */
public class Concept implements Vertex {
  /**
   * Name of the represented Concept.
   */
  private final String id;

  /**
   * Default class constructor.
   * @param id Concept name.
   */
  public Concept(String id) {
    this.id = id;
  }

  /**
   * Returns Concept name.
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Returns Concept String representation.
   */
  public String toString() {
    return getId();
  }

  /**
   * Method to confront the instance with another object.
   * @param o the other Concept to be confronted with.
   * @return true, if the concept are equal, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    return this.id.equals(((Concept)o).getId());
  }

  /**
   * Returns the HashCode of the Concept name.
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
