package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This class is used to implement a Semantic Network.
 * @author Christian Sordi
 */
public class SemanticNetwork implements Graph {

  private Map<Vertex,Set<Edge>> grafo;
  private Path percorso = Paths.get("src/main/res/knowledge/CuriosoneSemanticNetwork.txt");
  private static SemanticNetwork curiosoneSemanticNetwork;

  /**
   * Returns the Semantic Network.
   * @throws IOException if there is any problem.
   */
  public static SemanticNetwork getInstance() throws IOException {
    if (curiosoneSemanticNetwork == null) {
      curiosoneSemanticNetwork = new SemanticNetwork();
    }
    return curiosoneSemanticNetwork;
  }

  private SemanticNetwork() throws IOException {
    this.grafo = new HashMap<>();
    List<String> lineefile = new ArrayList<>();
    lineefile = Files.readAllLines(percorso);
    lineefile.remove(lineefile.size() - 1);
    for (String linea : lineefile) {
      String[] linee = linea.split(",");
      Vertex source = new Concept(linee[0],Integer.parseInt(linee[1])); 
      Vertex target = new Concept(linee[3],Integer.parseInt(linee[4]));
      SemanticRelationType type = SemanticRelationType.valueOf(linee[2].trim());
      SemanticRelation arco = new SemanticRelation(source,target,type);
      this.add(arco);
    }
  }

  public Map<Vertex,Set<Edge>> getGrafo() {
    return grafo;
  }

  @Override
  public void add(Edge e) {
    addEdge(e.getSource(), e.getTarget(), e.getType());
  }

  @Override
  public void add(Vertex v) {
    if (!grafo.containsKey(v)) {
      Set<Edge> lista = new HashSet<>();
      grafo.put(v,lista);
    }
  }

  @Override
  public void addEdge(Vertex v1, Vertex v2, SemanticRelationType type) {
    if (!grafo.containsKey(v1)) {
      add(v1);
    }
    if (!grafo.containsKey(v2)) {
      add(v2);
    }
    SemanticRelation arco = new SemanticRelation(v1,v2,type);
    grafo.get(v1).remove(arco);
    grafo.get(v2).remove(arco);
    grafo.get(v1).add(arco);
    grafo.get(v2).add(arco);
  }
  
  @Override
  public void learn(String vSource, String relation, String vTarget) throws IOException {
    Vertex source = new Concept(vSource.replace(" ", "_"),0); 
    Vertex target = new Concept(vTarget.replace(" ", "_"),0);
    SemanticRelationType type = SemanticRelationType.valueOf(relation);
    addEdge(source,target,type);
    Writer output;
    File sn = new File("src/main/res/knowledge/CuriosoneSemanticNetwork.txt");
    output = new BufferedWriter(new FileWriter(sn,true));
    output.append(source + "," + "0" + ",");
    output.append(SemanticRelationType.valueOf(relation) + ",");
    output.append(target + "," + "0");
    output.append("\n");
    output.close();
  }

  @Override
  public boolean containsEdge(Edge e) {
    for (Set<Edge> archiTemp : grafo.values()) {
      if (archiTemp.contains(e)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsVertex(Vertex v) {
    return grafo.keySet().contains(v);
  }

  /**
   * Ritorno tutti gli edge del grafo.
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
   * Ritorno tutti i vertici.
   */
  @Override
  public Set<Vertex> vertexSet() {
    return grafo.keySet();
  }

  /**
   * Prendo tutti gli archi che hanno come sorgente il vertice v
   * controllando il getSource di ogni arco.
   */
  @Override
  public Set<Edge> outgoingEdges(Vertex v) {
    if (containsVertex(v)) {
      Set<Edge> outgoingEdges = new HashSet<>();
      for (Edge arco : grafo.get(v)) {
        if (arco.getSource().equals(v)) {
          outgoingEdges.add(arco);
        }
      }
      return outgoingEdges;
    }
    return new HashSet<Edge>();
  }

  /**
   * Prendo tutti gli archi che hanno come destinazione il vertice v
   * controllando il getTarget di ogni arco.
   */
  @Override
  public Set<Edge> incomingEdges(Vertex v) {
    if (containsVertex(v)) {
      Set<Edge> incomingEdges = new HashSet<>();
      for (Edge arco : grafo.get(v)) {
        if (arco.getTarget().equals(v)) {
          incomingEdges.add(arco);
        }  
      }
      return incomingEdges;
    }
    return new HashSet<Edge>();
  }

  @Override
  public void addEdges(Collection<? extends Edge> edgeSet) {
    for (Edge arco : edgeSet) {
      add(arco);
    }
  }
  
  @Override
  public Boolean isPresent(String source, SemanticRelationType type) {
    Vertex vtoken = new Concept(source);
    if (this.containsVertex(vtoken)) {
      for (Edge e : grafo.get(vtoken)) {
        if (e.getType().equals(type)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return grafo.toString(); // metodo toString utilizzato per prove di debug
  }

  @Override
  public boolean equals(Object o) {
    return this.hashCode() == ((SemanticNetwork)o).hashCode();
  }

  /**
   * Hashcode basato su gli hascode di tutti i grafi e tutti i vertici.
   */
  @Override
  public int hashCode() {
    int result = 42;
    result = 31 * result + edgeSet().hashCode();
    result = 31 * result + vertexSet().hashCode();
    return result;
  }

  @Override
  public Optional<Edge> getAnswer(String source, Object daDefinire) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getAnswer(String source, Object daDefinire, String target) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Optional<Edge> getAnswer(String token, String target) {
    // TODO Auto-generated method stub
    return null;
  }
}