package com.github.bot.curiosone.core.knowledge;

import static java.util.stream.Collectors.toList;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a Semantic Network.
 * Provides methods to load the Semantic Network in memory and manage all of its components.
 * @see  com.github.bot.curiosone.core.knowledge.SemanticRelation The SemanticRelation Class
 * @see  com.github.bot.curiosone.core.knowledge.SemanticQuery The SemanticQuery Class
 */
public class SemanticNetwork implements Graph {

  /**
   * Stores all the SemanticRelationType different from IS_A.
   * @see  com.github.bot.curiosone.core.knowledge.SemanticRelationType The SemanticRelationType
   *       Class
   */
  private static final Set<SemanticRelationType> nsr = new HashSet<>(
      Arrays.asList(
          SemanticRelationType.TIME,
          SemanticRelationType.REGION,
          SemanticRelationType.IS_PERSON
      )
  );

  /**
   * Stores the Semantic Network.
   */
  private Map<Vertex,Set<Edge>> grafo;

  /**
   * String representation of the path to the Semantic Network database.
   */
  private Path percorso = Paths.get("src/main/resources/knowledge/CuriosoneSemanticNetwork.txt");

  /**
   * Singleton instance of this class.
   */
  private static SemanticNetwork curiosoneSemanticNetwork;

  /**
   * Gets the Singleton instance.
   * @return  the Semantic Network
   * @throws  IOException
   *          if something unexpected happens
   */
  public static SemanticNetwork getInstance() throws IOException {
    if (curiosoneSemanticNetwork == null) {
      curiosoneSemanticNetwork = new SemanticNetwork();
    }
    return curiosoneSemanticNetwork;
  }

  /**
   * Private constructor.
   * Loads the Semantic Network in memory.
   * @throws  IOException
   *          if there is a problem with the input file
   */
  private SemanticNetwork() throws IOException {
    this.grafo = new HashMap<>();
    List<String> lines = new ArrayList<>();
    lines = Files.readAllLines(this.percorso);
    for (String linea : lines) {
      String[] linee = linea.split(",");
      Vertex source = new Concept(linee[0]);
      Vertex target = new Concept(linee[2]);
      SemanticRelationType type = SemanticRelationType.valueOf(linee[1].trim());
      SemanticRelation arco =
          new SemanticRelation(source,target,type,Integer.parseInt(linee[3]));
      this.add(arco);
    }
  }

  /**
   * Gets the Map representation of this Semantic Network.
   * @return  the Map representation of this Semantic Network
   */
  @Override
  public Map<Vertex,Set<Edge>> getGrafo() {
    return grafo;
  }

  /**
   * Adds a SemanticRelation to this SemanticNetwork.
   * @param  e
   *         the SemanticRelation to be added
   */
  @Override
  public void add(Edge e) {
    addEdge(e.getSource(), e.getTarget(), e.getType(), e.getWeight());
  }

  /**
   * Adds the given Vertex to this SemanticNetwork, if is not already present.
   * @param  v
   *         the Vertex to be added
   */
  @Override
  public void add(Vertex v) {
    if (!grafo.containsKey(v)) {
      Set<Edge> lista = new HashSet<>();
      grafo.put(v,lista);
    }
  }

  /**
   * Adds a SemanticRelation to this SemanticNetwork.
   * @param  v1
   *         the source Vertex of the SemanticRelation to be added
   * @param  v2
   *         the target Vertex of the SemanticRelation to be added
   * @param  type
   *         the SemanticRelationType of the SemanticRelation to be added
   * @param  weight
   *         the weight of the SemanticRelation to be added
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Vertex The Vertex Interface
   * @see  com.github.bot.curiosone.core.knowledge.SemanticRelationType The SemanticRelationType
   *       Enum
   */
  @Override
  public void addEdge(Vertex v1, Vertex v2, SemanticRelationType type, Integer weight) {
    if (!grafo.containsKey(v1)) {
      add(v1);
    }
    if (!grafo.containsKey(v2)) {
      add(v2);
    }
    SemanticRelation arco = new SemanticRelation(v1,v2,type,weight);
    grafo.get(v1).add(arco);
    grafo.get(v2).add(arco);
  }

  /**
   * Checks whether this SemanticNetwork contains the given Edge or not.
   * @param  e
   *         the Edge to be searched.
   * @return  {@code true} if this SemanticNetwork contains the given Edge;
   *          {@code false} otherwise.
   * @see  com.github.bot.curiosone.core.knowledge.interfaces.Edge The Edge Interface
   */
  @Override
  public boolean containsEdge(Edge e) {
    for (Set<Edge> archiTemp : grafo.values()) {
      if (archiTemp.contains(e)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether this SemanticNetwork contains the given Vertex or not.
   * @param  v
   *         the Vertex to be searched.
   * @return  {@code true} if this SemanticNetwork contains the given Vertex;
   *          {@code false} otherwise.
   */
  @Override
  public boolean containsVertex(Vertex v) {
    return grafo.keySet().contains(v);
  }

  /**
   * Gets all the Edges of this SemanticNetwork.
   * @return  a Set containing all the Edges of this SemanticNetwork
   */
  @Override
  public Set<Edge> edgeSet() {
    Set<Edge> archi = new HashSet<>();
    for (Set<Edge> archiTemp : grafo.values()) {
      archi.addAll(archiTemp);
    }
    return archi;
  }

  /**
   * Gets all the Vertices of this SemanticNetwork.
   * @return  a Set containing all the Vertices of this SemanticNetwork
   */
  @Override
  public Set<Vertex> vertexSet() {
    return grafo.keySet();
  }

  /**
   * Gets all the outgoing Edges from the given Vertex.
   * @return  a Set containg all the Semantis Relations outgoing from the given Vertex
   */
  @Override
  public Set<Edge> outgoingEdges(Vertex v) {
    Set<Edge> outgoingEdges = new HashSet<>();
    if (containsVertex(v)) {
      for (Edge arco : grafo.get(v)) {
        if (arco.getSource().equals(v)) {
          outgoingEdges.add(arco);
        }
      }
    }
    return outgoingEdges;
  }

  /**
   * Gets all the incoming Edges to the given Vertex.
   * @return  a Set containg all the Semantis Relations coming to the given Vertex
   */
  @Override
  public Set<Edge> incomingEdges(Vertex v) {
    Set<Edge> incomingEdges = new HashSet<>();
    if (containsVertex(v)) {
      for (Edge arco : grafo.get(v)) {
        if (arco.getTarget().equals(v)) {
          incomingEdges.add(arco);
        }
      }
    }
    return incomingEdges;
  }

  /**
   * Adds all the Semantic Relations in the given Collection to this SemanticNetwork.
   * @param  edgeSet
   *         the Collection to be added
   */
  @Override
  public void addEdges(Collection<? extends Edge> edgeSet) {
    for (Edge arco : edgeSet) {
      add(arco);
    }
  }

  /**
   * Checks whether this SemanticNetwork contains the given SemanticRelation.
   * @param  v1
   *         the source Vertex for the SemanticRelation to be checked
   * @param  relation
   *         the SemanticRelationType of the SemanticRelation to be checked
   * @param  v2
   *         the target Vertex for the SemanticRelation to be checked
   * @return  {@code true} if this SemanticNetwork contains the given SemanticRelation;
   *          {@code false} otherwise
   */
  @Override
  public boolean exist(String v1, SemanticRelationType relation, String v2) {
    Vertex source = new Concept(v1.replaceAll(" ", "_"));
    Vertex target = new Concept(v2.replaceAll(" ", "_"));
    SemanticRelation sr = new SemanticRelation(source, target, relation);
    if (containsVertex(source)) {
      return outgoingEdges(source).contains(sr);
    }
    return false;
  }

  /**
   * Learns the given SemanticRelation.
   * @param  v1
   *         the source Vertex of the SemanticRelation to be learnt
   * @param  relation
   *         the SemanticRelationType of the SemanticRelation to be learnt
   * @param  v2
   *         the target Vertex of the SemanticRelation to be learnt
   */
  @Override
  public void learn(String v1, SemanticRelationType relation, String v2) {
    Vertex source = new Concept(v1.replace(" ", "_"));
    Vertex target = new Concept(v2.replace(" ", "_"));
    addEdge(source, target, relation, 1);
    Writer output;
    File sn = this.percorso.toFile();
    try {
      output = new BufferedWriter(new FileWriter(sn,true));
      output.append("\n");
      output.append(source + ",");
      output.append(relation + ",");
      output.append(target + "," + 1);
      output.append("\n");
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println("learned");
    increase(target, 1);
  }

  /**
   * Returns the strongest Edge of the given SemanticRelationType.
   * @param  v1
   *         String representation of the Vertex source
   * @param  type
   *         SemanticRelationType of the strongest Edge to be searched
   * @return  an Optional instance. The instance is empty, if no Edge is found. Otherwise, it
   *         contains the found Edge.
   */
  @Override
  public Optional<Edge> getAnswer(String v1, SemanticRelationType type) {
    Vertex source = new Concept(v1.replaceAll(" ", "_"));
    if (containsVertex(source)) {
      try {
        increase(source,30);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (type.equals(SemanticRelationType.IS_A)) {
        List<Edge> edges = outgoingEdges(source).stream()
            .filter(x -> !nsr.contains(x.getType())).collect(toList());
        return edges.size() == 0 ? Optional.empty() : getAnswer(edges);
      }

      if (type.equals(SemanticRelationType.REGION)) {
        List<Edge> edges = outgoingEdges(source).stream()
            .filter(x -> x.getType().equals(SemanticRelationType.REGION)).collect(toList());
        return edges.size() == 0 ? Optional.empty() : getAnswer(edges);
      }
      for (Edge e : outgoingEdges(source)) {
        if (e.getType().equals(type)) {
          return getAnswer(Arrays.asList(e));
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Returns the strongest Edge of the given Concept.
   * @param  v1
   *         String representation of the Vertex source
   * @return  An Optional instance. The instance is empty, if no Edge has been found, otherwise it
   *         contains the found Edge.
   */
  @Override
  public Optional<Edge> getAnswer(String v1) {
    Vertex source = new Concept(v1.replaceAll(" ", "_"));
    if (containsVertex(source)) {
      List<Edge> edges = new ArrayList<>(outgoingEdges(source));
      return getAnswer(edges);
    }
    return Optional.empty();
  }

  /**
   * Finds the strongest Edge in the given Edge List.
   * @param  edges
   *         Edge List to be searched in
   * @return an Optional instance. The instance contains an Edge, if the strongest Edge is found.
   *         It is empty, otherwise
   */
  @Override
  public Optional<Edge> getAnswer(List<Edge> edges) {
    edges.sort((a,b) -> b.getWeight() - a.getWeight());
    Vertex source = new Concept(edges.get(0).getSource().getId().replaceAll("_", " "));
    Vertex target = new Concept(edges.get(0).getTarget().getId().replaceAll("_", " "));
    SemanticRelationType type = edges.get(0).getType();
    Edge answer = new SemanticRelation(source, target, type, edges.get(0).getWeight());
    return Optional.of(answer);

  }

  /**
   * Increases the usage score for all the Edges with the given Vertex as target.
   * @param  v
   *         the target Vertex
   */
  @Override
  public void increase(Vertex v, Integer score) {
    int nodo = 2;
    for (Edge e : incomingEdges(v)) {
      e.setWeight(e.getWeight() + score);
    }
    StringBuffer exporter = new StringBuffer();
    List<String> lines = new ArrayList<>();
    try {
      lines = Files.readAllLines(this.percorso);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    for (String linea : lines) {
      String[] linee = linea.split(",");
      if (linee[nodo].equals(v.getId())) {
        int weight = Integer.parseInt(linee[3]) + score;
        exporter.append(linee[0] + "," + linee[1] + "," + linee[2] + "," + weight);
        exporter.append("\n");
      } else {
        exporter.append(linea);
        exporter.append("\n");
      }
    }

    int lastNewLine = exporter.lastIndexOf("\n");
    if (lastNewLine >= 0) {
      exporter.delete(lastNewLine, exporter.length());
    }
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(this.percorso.toString(), "UTF-8");
    } catch (FileNotFoundException | UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    writer.print(exporter.toString());
    writer.close();
  }

  /**
   * Executes a SemanticQuery.
   * @param  sq
   *         the SemanticQuery to be exectued
   * @return  Returns an Optional instance.
   *          The instance is empty, if no result is found.
   *          Otherwise, it contains the query response
   */
  @Override
  public Optional<Edge> query(SemanticQuery sq) {
    if (sq.getSubject() == null && sq.getRelation() == null) {
      return getAnswer(sq.getObject());
    }
    if (sq.getSubject() == null) {
      return getAnswer(sq.getObject(), sq.getRelation());
    }
    learn(sq.getObject(), sq.getRelation(), sq.getSubject());
    return Optional.empty();
  }

  /**
   * Checks whether this SemanticNetwork equals to the given Object.
   * @param  o
   *         the Object to be compared against.
   * @return  {@code true} if this SemanticNetwork equals to the given Object;
   *          {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    return this.hashCode() == ((SemanticNetwork)o).hashCode();
  }

  /**
   * Calculates the HashCode of this SemanticNetwork.
   * The HashCode depends on the Vertices and Edges of this SemanticNetwork.
   * @return  the HashCode of this SemanticNetwork
   */
  @Override
  public int hashCode() {
    int result = 42;
    result = 31 * result + edgeSet().hashCode();
    result = 31 * result + vertexSet().hashCode();
    return result;
  }

  /**
   * Returns a String representation of this SemanticNetwork.
   * @return  a String representation of this SemanticNetwork
   */
  @Override
  public String toString() {
    return grafo.toString();
  }
}
