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
 * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
 * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
 */
public interface Graph {

  /**
   * Adds an Edge to the Graph.
   * @param  e
   *         the Edge to be added
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  void add(Edge e);

  /**
   * Adds a Vertex to the Graph.
   * @param  v
   *         the Vertex to be added
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   */
  void add(Vertex v);

  /**
   * Adds an Edge between two Vertices.
   * @param  v1
   *         the source of the Edge to be added
   * @param  v2
   *         the destination of the Edge to be added
   * @param  type
   *         the type of the Edge to be added
   * @param  weight
   *         the weight of the Edge to be added
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   * @see  com.github.bot.curiosone.core.knowledge.SemanticRelationType The SemanticRelationType
   *       Enum
   */
  void addEdge(Vertex v1, Vertex v2, SemanticRelationType type, Integer weight);

  /**
   * Checks whether the Graph contains the given Edge or not.
   * @param  e
   *         the Edge to be searched
   * @return  {@code true} if the Graph contains the given Edge,
              {@code false} otherwise
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  boolean containsEdge(Edge e);

  /**
   * Checks whether the Graph contains the given Vertex or not.
   * @param  v
   *         the Vertex to be searched
   * @return  {@code true} if the Graph contains the given Vertex,
              {@code false} otherwise
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   */
  boolean containsVertex(Vertex v);

  /**
   * Gets all the Edges of this Graph.
   * @return  a Set containing all the Edges of the Graph
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  Set<Edge> edgeSet();

  /**
   * Gets all the Vertices of this Graph.
   * @return  a Set containing all the Vertices of the Graph
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   */
  Set<Vertex> vertexSet();

  /**
   * Gets all the outgoing Edges from the given Vertices.
   * @param  v
   *         the interested Vertex
   * @return  a Set containing all the outgoing Edges from the given Vertex
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  Set<Edge> outgoingEdges(Vertex v);

  /**
   * Gets all the incoming Edges to the given Vertex.
   * @param  v
   *         the interested Vertex
   * @return  a Set containing all the coming Edges to the given Vertex
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  Set<Edge> incomingEdges(Vertex v);

  /**
   * Adds all the given Edges to the Edges of the Graph.
   * @param  edgeSet
   *         the Edges to be added
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   * @see  java.util.Collection The Collection Interface
   */
  void addEdges(Collection<? extends Edge> edgeSet);

  /**
   * Gets the Graph.
   * @return  a Map representation of the Graph
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   * @see  java.util.Map The Map Interface
   */
  Map<Vertex, Set<Edge>> getGrafo();

  /**
   * Returns the strongest Edge of the given Concept.
   * @param  v1
   *         String representation of the Vertex source
   * @return  An Optional instance. The instance is empty, if no Edge has been found, otherwise it
   *          contains the found Edge.
   * @see  java.util.Optional The Optional Class
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  Optional<Edge> getAnswer(String v1);

  /**
   * Returns the strongest Edge of the given SemanticRelationType.
   * @param  source
   *         String representation of the Vertex source
   * @param  type
   *         SemanticRelationType of the strongest Edge to be searched
   * @return  an Optional instance. The instance is empty, if no Edge is found. Otherwise, it
   *          contains the found Edge.
   * @see  java.util.Optional The Optional Class
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   * @see  com.github.bot.curiosone.core.knowledge.SemanticRelationType The SemanticRelationType
   *       Enum
   */
  Optional<Edge> getAnswer(String source,SemanticRelationType type);

  /**
   * Finds the strongest Edge in the given Edge List.
   * @param  edges
   *         Edge List to be searched in
   * @return  an Optional instance. The instance contains an Edge, if the strongest Edge is found.
   *          It is empty, otherwise
   * @see  java.util.Optional The Optional Class
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  Optional<Edge> getAnswer(List<Edge> edges);

  /**
   * Learns the given Edge.
   * @param  v1
   *         String representation of the source for the Edge to be learned
   * @param  relation
   *         SemanticRelationType for the Edge to be learned
   * @param  v2
   *         String representation of the target for the Edge to be learned
   * @see  com.github.bot.curiosone.core.knowledge.SemanticRelationType The SemanticRelationType
   *       Enum
   */
  void learn(String v1, SemanticRelationType relation, String v2);

  /**
   * Increases the usage score for all the Edges with the given Vertex as target.
   * @param  v
   *         the target Vertex
   * @param  score
   *         the score of the Edge to be learnt
   */
  void increase(Vertex v, Integer score);

  /**
   * Executes a SemanticQuery.
   * @param  sq
   *         the SemanticQuery to be exectued
   * @return  Returns an Optional instance.
   *          The instance is empty, if no result is found.
   *          Otherwise, it contains the query response
   * @see  com.github.bot.curiosone.core.knowledge.SemanticQuery The SemanticQuery Class
   */
  Optional<Edge> query(SemanticQuery sq);

  /**
   * Checks whether the given Edge is in the Graph.
   * @param  v1
   *         String representation of the source Vertex of the Edge to be searched
   * @param  relation
   *         SemanticRelationType of the Edge to be searched
   * @param  v2
   *         String representation of the target Vertex of the Edge to be searched
   * @return  {@code true} if the Edge exists,
   *          {@code false} otherwise
   * @see  com.github.bot.curiosone.core.knowledge.SemanticQuery The SemanticQuery Class
   *       Enum
   */
  boolean exist(String v1, SemanticRelationType relation, String v2);
}
