package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

/**
 * Represents a Semantic Relation of a Semantic Network.
 * Provides methods to create a Semantic Relation and get its information.
 * @see  Vertex The Vertex Interface
 * @see  SemanticRelationType The SemanticRelationType Enum
 */
public class SemanticRelation implements Edge {

  /**
   * Stores the source Vertex of this SemanticRelation.
   * @see  Vertex The Vertex Interface
   */
  private Vertex sorgente;

  /**
   * Stores the target Vertex of this SemanticRelation.
   * @see  Vertex The Vertex Interface
   */
  private Vertex destinazione;

  /**
   * Stores the SemanticRelationType of this SemanticRelation.
   * @see  SemanticRelationType The SemanticRelationType Enum
   */
  private SemanticRelationType collegamento;

  /**
   * Stores the weight of this SemanticRelation.
   */
  private Integer weight;

  /**
   * Constructs a SemanticRelation.
   * @param  v1
   *         source Vertex of this SemanticRelation
   * @param  v2
   *         target Vertex of this SemanticRelation
   * @param  type
   *         SemanticRelationType of this SemanticRelation
   * @param  weight
   *         weight of this SemanticRelation
   */
  public SemanticRelation(Vertex v1,Vertex v2, SemanticRelationType type, Integer weight) {
    this.sorgente = v1;
    this.destinazione = v2;
    this.collegamento = type;
    this.weight = weight;
  }

  /**
   * Constructs a SemanticRelation.
   * @param  v1
   *         source Vertex of this SemanticRelation
   * @param  v2
   *         target Vertex of this SemanticRelation
   * @param  type
   *         SemanticRelationType of this SemanticRelation
   */
  public SemanticRelation(Vertex v1, Vertex v2, SemanticRelationType type) {
    this(v1, v2, type, 0);
  }

  /**
   * Gets the source Vertex of this SemanticRelation Relation.
   * @return  the source Vertex of this SemanticRelation
   */
  @Override
  public Vertex getSource() {
    return sorgente;
  }

  /**
   * Gets the target Vertex of this SemanticRelation.
   * @return  the target Vertex of this SemanticRelation
   */
  @Override
  public Vertex getTarget() {
    return destinazione;
  }

  /**
   * Gets the SemanticRelationType of this SemanticRelation.
   * @return  the SemanticRelationType of this SemanticRelation
   */
  @Override
  public SemanticRelationType getType() {
    return collegamento;
  }

  /**
   * Gets the weight of this SemanticRelation.
   * @return  the weight of this SemanticRelation
   */
  @Override
  public Integer getWeight() {
    return weight;
  }

  /**
   * Sets the weight of this SemanticRelation.
   */
  @Override
  public void setWeight(Integer i) {
    this.weight = i;
  }

  /**
   * Returns a String representation of this SemanticRelation.
   * @return  a String representation of this SemanticRelation
   */
  @Override
  public String toString() {
    return sorgente.getId() + " - > " + collegamento + " - > "
        + destinazione.getId() + "(" + weight + ")";
  }

  /**
   * Checks whether this SemanticRelation equals to the given Object.
   * @param  o
   *         the object to be compared against
   * @return  {@code true} if this SemanticRelation equals to the given object;
   *          {@code false} othewise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    return this.hashCode() == ((SemanticRelation)o).hashCode();
  }

  /**
   * Calculates the HashCode of this SemanticRelation.
   * The HashCode depends on the Vertices and the SemanticRelationType.
   * @return  the HashCode of this SemanticRelation
   */
  @Override
  public int hashCode() {
    int result = 42;
    result = 31 * result + sorgente.hashCode();
    result = 31 * result + collegamento.hashCode();
    result = 31 * result + destinazione.hashCode();
    return result;
  }
}
