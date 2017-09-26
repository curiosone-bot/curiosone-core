package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

/**
 * This class implements a Semantic Relation between two Concepts.
 * @author Christian Sordi
 */
public class SemanticRelation implements Edge {
  private Vertex sorgente;
  private Vertex destinazione;
  private SemanticRelationType collegamento;
  private Integer weight;

  /**
   * Default class constructor.
   * @param v1 vertice Sorgente
   * @param v2 Vertice Destinazione
   * @param type Tipo dell'arco
   */
  public SemanticRelation(Vertex v1,Vertex v2, SemanticRelationType type, Integer weight) {
    this.sorgente = v1;
    this.destinazione = v2;
    this.collegamento = type;
    this.weight = weight;
  }

  /**
   * Class costructor witouth specified weight.
   * @param v1 Source vertex
   * @param v2 Target vertex
   * @param type SemanticRelationtype
   */
  public SemanticRelation(Vertex v1, Vertex v2, SemanticRelationType type) {
    this.sorgente = v1;
    this.destinazione = v2;
    this.collegamento = type;
    this.weight = 0;
  }

  @Override
  public Vertex getSource() {
    return sorgente;
  }

  @Override
  public Vertex getTarget() {
    return destinazione;
  }

  @Override
  public SemanticRelationType getType() {
    return collegamento;
  }

  @Override
  public Integer getWeight() {
    return weight;
  }

  @Override
  public void setWeight(Integer i) {
    this.weight = i;
  }

  @Override
  public String toString() {
    return sorgente.getId() + " - > " + collegamento + " - > "
        + destinazione.getId() + "(" + weight + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    return this.hashCode() == ((SemanticRelation)o).hashCode();
  }

  @Override
  public int hashCode() {
    int result = 42;
    result = 31 * result + sorgente.hashCode();
    result = 31 * result + collegamento.hashCode();
    result = 31 * result + destinazione.hashCode();
    return result;
  }
}
