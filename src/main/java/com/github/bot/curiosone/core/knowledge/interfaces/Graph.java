package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
   * @param weight peso
   */
  void addEdge(Vertex v1, Vertex v2, SemanticRelationType type, Integer weight);

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
   * Method that returns semantic network graph.
   * @return graph
   */
  Map<Vertex, Set<Edge>> getGrafo();

  /**
   * Method that seeks a response if a question is asked to Curiosone.
   * @param source Concept
   * @param type SemanticRelationType
   * @return Optional of Edge if exists between source and relation.
   * @throws IOException exception
   */
  Optional<Edge> getAnswer(String source,SemanticRelationType type);

  /**
   * Method that seeks a response if a question is asked to Curiosone
   * and there are multiple possible response.
   * @param edges List
   * @return Optional of best Edge.
   */
  Optional<Edge> getAnswer(List<Edge> edges);

  /**
   * Method called if a question is asked to curiosone and
   * doesn't know response so, learn.
   * @param v1 Concept
   * @param relation SemanticRelationType
   * @param v2 Concept
   */
  void learn(String v1, String relation, String v2);

  /**
   * Method called when curiosone need to increase an
   * edge score after have learned or asked for a concept.
   * @param v vertex
   * @throws IOException exception
   */
  void increase(Vertex v, Integer score);
}
