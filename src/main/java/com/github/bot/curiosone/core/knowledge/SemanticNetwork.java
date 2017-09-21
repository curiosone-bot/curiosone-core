package com.github.bot.curiosone.core.knowledge;

import static java.util.stream.Collectors.toList;

import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * This class is used to implement a Semantic Network.
 * @author Christian Sordi
 */
public class SemanticNetwork implements Graph {
  
  private static final Set<SemanticRelationType> nsr = new HashSet<>(Arrays.asList
      (SemanticRelationType.TIME,SemanticRelationType.REGION,SemanticRelationType.IS_PERSON));
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
    List<String> linee_file = new ArrayList<>();
    linee_file = Files.readAllLines(this.percorso);
    for(String linea : linee_file) {
      String[] linee = linea.split(",");
      Vertex source = new Concept(linee[0]); 
      Vertex target = new Concept(linee[2]);
      SemanticRelationType type = SemanticRelationType.valueOf(linee[1].trim());
      SemanticRelation Arco = 
          new SemanticRelation(source,target,type,Integer.parseInt(linee[3]));
      this.add(Arco);
    }
  }
  
  public void addWeights() throws IOException {
    List<String> linee_file = new ArrayList<>();
    StringBuffer exporterSn = new StringBuffer();
    linee_file = Files.readAllLines(this.percorso);
    for(String linea : linee_file) {
      String[] linee = linea.split(",");
      Vertex target = new Concept(linee[2]);
      Integer xx = grafo.get(target).size();
      exporterSn.append(linea);
      exporterSn.append("," + xx + "\n");
    }
    
    int lastNewLine = exporterSn.lastIndexOf("\n");
    if (lastNewLine >= 0) {
      exporterSn.delete(lastNewLine, exporterSn.length());
    }
    PrintWriter writer = new PrintWriter("src/main/res/knowledge/CuriosoneSemanticNetwork.txt", "UTF-8");
    writer.print(exporterSn.toString());
    writer.close();
    System.out.println("Rete Semantica di WordNet creata con successo");
  }

  public Map<Vertex,Set<Edge>> getGrafo() {
    return grafo;
  }

  @Override
  public void add(Edge e) {
    addEdge(e.getSource(), e.getTarget(), e.getType(), e.getWeight());
  }

  @Override
  public void add(Vertex v) {
    if (!grafo.containsKey(v)) {
      Set<Edge> lista = new HashSet<>();
      grafo.put(v,lista);
    }
  }

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
   * Prendo tutti gli archi che hanno come destinazione il vertice v
   * controllando il getTarget di ogni arco.
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

  @Override
  public void addEdges(Collection<? extends Edge> edgeSet) {
    for (Edge arco : edgeSet) {
      add(arco);
    }
  }
  
  @Override
  public void learn(String vSource, String relation, String vTarget) {
    Vertex source = new Concept(vSource.replace(" ", "_")); 
    Vertex target = new Concept(vTarget.replace(" ", "_"));
    SemanticRelationType type = SemanticRelationType.valueOf(relation);
    addEdge(source,target,type,1);
    Writer output;
    File sn = new File("src/main/res/knowledge/CuriosoneSemanticNetwork.txt");
    try {
      output = new BufferedWriter(new FileWriter(sn,true));
      output.append(source + ",");
      output.append(type + ",");
      output.append(target + "," + 1);
      output.append("\n");
      output.close();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public Optional<Edge> getAnswer(String source,SemanticRelationType type) {
    Vertex vSource = new Concept(source.replaceAll(" ", "_"));
    if ( containsVertex(vSource)) {
      try {
        update(vSource);
      } 
      catch (Exception e) {
        e.printStackTrace();
      }
      if (type.equals(SemanticRelationType.IS_A)) {
        List<Edge> edges = outgoingEdges(vSource).stream()
            .filter(x -> !nsr.contains(x.getType())).collect(toList());
        switch(edges.size()) {
        case 0 : return Optional.empty();
        case 1 : return Optional.of(edges.get(0));
        default : return getAnswer(edges);
        }
      }
      
      if (type.equals(SemanticRelationType.REGION)) {
        List<Edge> edges = outgoingEdges(vSource).stream()
            .filter(x -> x.getType().equals(SemanticRelationType.REGION)).collect(toList());
        switch(edges.size()) {
        case 0 : return Optional.empty();
        case 1 : return Optional.of(edges.get(0));
        default : return getAnswer(edges);
        }
      }
      for (Edge e : outgoingEdges(vSource)) {
        if (e.getType().equals(type)) {
          return Optional.of(e);
        }
      }
    }
    return Optional.empty();
  }
  
  @Override
  public Optional<Edge> getAnswer(List<Edge> edges) {
    edges.sort((a,b) -> b.getWeight() - a.getWeight());
    return Optional.of(edges.get(0));
  }
  
  @Override
  public void update(Vertex v) throws IOException {
    for ( Edge e : outgoingEdges(v)) {
      e.setWeight(e.getWeight()+50);
    }
    StringBuffer exporter = new StringBuffer();
    List<String> linee_file = new ArrayList<>();
    linee_file = Files.readAllLines(this.percorso);
    for(String linea : linee_file) {
      String[] linee = linea.split(",");
      if ( linee[0].equals(v.getId())) {
        int weight = Integer.parseInt(linee[3]) + 20;
        exporter.append(linee[0]+"," + linee[1] + "," + linee[2] + "," + weight);
        exporter.append("\n");
      }
      else {
        exporter.append(linea);
        exporter.append("\n");
      }
    }
    
    int lastNewLine = exporter.lastIndexOf("\n");
    if (lastNewLine >= 0) {
      exporter.delete(lastNewLine, exporter.length());
    }
    PrintWriter writer = new PrintWriter("src/main/res/knowledge/CuriosoneSemanticNetwork.txt", "UTF-8");
    writer.print(exporter.toString());
    writer.close();
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
  public String toString() {
    return grafo.toString(); // metodo toString utilizzato per prove di debug
  }
}