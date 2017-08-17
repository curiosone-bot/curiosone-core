package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;
/**
 *
 * @author Christian
 *
 */
public class SemanticRelation implements Edge
{
  private Vertex sorgente;
  private Vertex destinazione;
  private SemanticRelationType collegamento;
  /**
   *
   * @param v1 vertice Sorgente
   * @param v2 Vertice Destinazione
   * @param type Tipo dell'arco
   */
  public SemanticRelation(Vertex v1,Vertex v2, SemanticRelationType type)
  {
    this.sorgente = v1;
    this.destinazione = v2;
    this.collegamento = type;
  }

  @Override
  public Vertex getSource() {return sorgente;}             		//
  @Override										         		//
  public Vertex getTarget() {return destinazione;}         		//    Getter
  @Override										         		//
  public SemanticRelationType getType() {return collegamento;} 	//

  /**
   * metodo toString utilizzato per varie prove di debug
   */
  @Override
  public String toString()
  {
    return sorgente.getId()+" - > "+ collegamento + " - > " + destinazione.getId();
  }

  @Override
  public boolean equals(Object o)
  {
    return this.hashCode() == ((SemanticRelation)o).hashCode();
  }

  /**
   *  HashCode basato sull'hashCode del vertice di destinazione e vertice sorgente,
   */
  @Override
  public int hashCode()
  {
    int result = 42;
        result = 31 * result + sorgente.hashCode();
        result = 31 * result + destinazione.hashCode();
        return result;
  }


}
