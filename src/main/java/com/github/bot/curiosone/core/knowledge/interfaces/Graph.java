package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Interfaccia che rappresenta un grafo.
 * @author navigli
 *
 */
public interface Graph {
  /**
   * Aggiunge un arco.
   * @param e l'arco da aggiungere
   */
  void add(Edge e);

  /**
   * Aggiunge un vertice.
   * @param v il vertice da aggiungere
   */
  void add(Vertex v);

  /**
   * Aggiunge un arco da v1 a v2 del tipo specificato.
   * @param v1 sorgente
   * @param v2 destinazione
   * @param type tipo dell'arco
   */
  void addEdge(Vertex v1, Vertex v2, SemanticRelationType type);

  /**
   * Restituisce true se l'arco e' contenuto nel grafo.
   * @param e l'arco da verificare
   * @return true se l'arco e' contenuto, false altrimenti
   */
  boolean containsEdge(Edge e);

  /**
   * Restituisce true se il vertice e' contenuto nel grafo.
   * @param v il vertice da verificare
   * @return true se il vertice e' contenuto, false altrimenti
   */
  boolean containsVertex(Vertex v);

  /**
   * Restituisce una copia dell'insieme degli archi.
   * @return l'insieme degli archi del grafo
   */
  Set<Edge> edgeSet();

  /**
   * Restituisce una copia dell'insieme dei vertici.
   * @return l'insieme dei vertici
   */
  Set<Vertex> vertexSet();

  /**
   * Restituisce l'insieme di archi uscenti.
   * @param v vertice su cui calcolare l'insieme di archi
   * @return insieme degli archi uscenti
   */
  Set<Edge> outgoingEdges(Vertex v);
  
  /**
   * Restituisce l'insieme di archi entranti.
   * @param v vertice su cui calcolare l'insieme di archi
   * @return insieme degli archi uscenti
   */
  Set<Edge> incomingEdges(Vertex v);

  /**
   * Aggiunge tutti gli archi forniti in input.
   * @param edgeSet gli archi da aggiungere
   */
  void addEdges(Collection<? extends Edge> edgeSet);

  /**
   * Checks if a SemanticRelation is present.
   * @param tipo del collegamento dell'arco da cercare.
   * @param token vertice del quale cercare il significato collegato.
   * @return a Boolean that Checks if a SemanticRelation is present.
   */
  Boolean isPresent(String source, SemanticRelationType tipo);
  
  /**
   * Returns the Edge related to Concept token, if present.
   * @param tipo del collegamento dell'arco da cercare.
   * @param token vertice del quale cercare il significato collegato.
   * @return Optional of Set of Edge.
   */
  
  /**
   * Returns the Edge related to Concept token, if present.
   * @param source Vertex ID's.
   * @param daDefinire SemanticRelationType.
   * @return Object Edge.
   */
  Optional<Edge> getAnswer(String source,Object daDefinire);
  
  /**
   * Returns true of false if this SemanticRelation is present.
   * @param source Vertex ID's.
   * @param daDefinire SemanticRelationType.
   * @param target Vertex ID's.
   * @return Object Edge.
   */
  boolean getAnswer(String source,Object daDefinire,String target);
  
  Optional<Edge> getAnswer(String token,String target);

  void learn(String vSource, String relation, String vTarget) throws IOException;
}