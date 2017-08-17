package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Arco del grafo.
 * @author navigli
 */
public interface Edge {

  /**
  * Restituisce il vertice di partenza.
  * @return vertice di partenza
  */
  Vertex getSource();

  /**
   * Restituisce il vertice di arrivo.
   * @return vertice di arrivo
   */
  Vertex getTarget();

  /**
   * Restituisce il tipo dell'arco.
   * @return tipo dell'arco
   */
  SemanticRelationType getType();
}
