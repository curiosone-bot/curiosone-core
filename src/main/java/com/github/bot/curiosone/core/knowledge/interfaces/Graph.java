package com.github.bot.curiosone.core.knowledge.interfaces;

import com.github.bot.curiosone.core.knowledge.SemanticQuery;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a Graph.
 */
public interface Graph {

  /**
   * Adds an Edge to the Graph.
   * @param e the Edge to be added
   */
  void add(Edge e);

  /**
   * Adds a Vertex to the Graph.
   * @param v the Vertex to be added
   */
  void add(Vertex v);

  /**
   * Adds an Edge between two Vertices.
   * @param v1 the source of the Edge to be added
   * @param v2 the destination of the Edge to be added
   * @param type the type of the Edge to be added
   * @param weight the weight of the Edge to be added
   */
  void addEdge(Vertex v1, Vertex v2, SemanticRelationType type, Integer weight);

  /**
   * Checks whether the Graph contains the given Edge or not.
   * @param e the Edge to be searched
   * @return {@code true} if the Graph contains the given Edge,
             {@code false} otherwise
   */
  boolean containsEdge(Edge e);

  /**
   * Checks whether the Graph contains the given Vertex or not.
   * @param v the Vertex to be searched
   * @return {@code true} if the Graph contains the given Vertex,
             {@code false} otherwise
   */
  boolean containsVertex(Vertex v);

  /**
   * Returns a Set containing all the Edges of this Graph.
   */
  Set<Edge> edgeSet();

  /**
   * Returns a Set containing all the Vertices of this Graph.
   */
  Set<Vertex> vertexSet();

  /**
   * Returns all the outgoing Edges from the given Vertices.
   * @param v the interested Vertex
   */
  Set<Edge> outgoingEdges(Vertex v);

  /**
   * Returns all the incoming Edges from the given Vertices.
   * @param v the interested Vertex
   */
  Set<Edge> incomingEdges(Vertex v);

  /**
   * Adds all the given Edges to the Edges of the Graph.
   * @param edgeSet the Edges to be added
   */
  void addEdges(Collection<? extends Edge> edgeSet);

  /**
   * Returns a Map representation of the Graph.
   */
  Map<Vertex, Set<Edge>> getGrafo();

  /**
   * Returns the strongest Edge of the given Concept.
   * @param v1 String representation of the Vertex source
   * @return An Optional instance. The instance is empty, if no Edge has been found, otherwise it
   *         contains the found Edge.
   */
  Optional<Edge> getAnswer(String v1);

  /**
   * Returns the strongest Edge of the given SemanticRelationType.
   * @param source String representation of the Vertex source
   * @param type SemanticRelationType of the strongest Edge to be searched
   * @return an Optional instance. The instance is empty, if no Edge is found. Otherwise, it
   *         contains the found Edge.
   * @throws IOException exception
   */
  Optional<Edge> getAnswer(String source,SemanticRelationType type);

  /**
   * Finds the strongest Edge in the given Edge List.
   *
   * @param edges Edge List to be searched in
   * @return an Optional instance. The instance contains an Edge, if the strongest Edge is found.
   *         It is empty, otherwise
   */
  Optional<Edge> getAnswer(List<Edge> edges);

  /**
   * Learns the given Edge.
   * @param v1 String representation of the source for the Edge to be learned
   * @param relation SemanticRelationType for the Edge to be learned
   * @param v2 String representation of the target for the Edge to be learned
   */
  void learn(String v1, SemanticRelationType relation, String v2);

  /**
   * Increases the usage score for all the Edges with the given Vertex as target.
   * @param v the target Vertex
   * @throws IOException if no input file is found.
   */
  void increase(Vertex v, Integer score);

  /**
   * Executes a SemanticQuery.
   * @param sq the SemanticQuery to be exectued
   * @return Returns an Optional instance.
   *         The instance is empty, if no result is found.
   *         Otherwise, it contains the query response
   */
  Optional<Edge> query(SemanticQuery sq);

  /**
   * Checks whether the given Edge is in the Graph.
   * @param v1 String representation of the source Vertex of the Edge to be searched
   * @param relation SemanticRelationType of the Edge to be searched
   * @param v2 String representation of the target Vertex of the Edge to be searched
   * @return {@code true} if the Edge exists,
   *         {@code false} otherwise
   */
  boolean exist(String v1, SemanticRelationType relation, String v2);
}
