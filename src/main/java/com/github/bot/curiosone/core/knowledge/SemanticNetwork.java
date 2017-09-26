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
 * This class is used to implement a Semantic Network.
 * @author Christian Sordi
 */
public class SemanticNetwork implements Graph {

  private static final Set<SemanticRelationType> nsr = new HashSet<>(
      Arrays.asList(
          SemanticRelationType.TIME,
          SemanticRelationType.REGION,
          SemanticRelationType.IS_PERSON
      )
  );
  private Map<Vertex,Set<Edge>> grafo;
  private Path percorso = Paths.get("src/main/resources/knowledge/CuriosoneSemanticNetwork.txt");
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

  @Override
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
  public boolean exist(String v1, SemanticRelationType relation, String v2) {
    Vertex source = new Concept(v1.replaceAll(" ", "_"));
    Vertex target = new Concept(v2.replaceAll(" ", "_"));
    SemanticRelation sr = new SemanticRelation(source, target, relation);
    if (containsVertex(source)) {
      return outgoingEdges(source).contains(sr);
    }
    return false;
  }

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
    System.out.println("learned");
    increase(target, 1);
  }

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
        switch (edges.size()) {
          case 0: return Optional.empty();
          case 1: return Optional.of(edges.get(0));
          default: return getAnswer(edges);
        }
      }

      if (type.equals(SemanticRelationType.REGION)) {
        List<Edge> edges = outgoingEdges(source).stream()
            .filter(x -> x.getType().equals(SemanticRelationType.REGION)).collect(toList());
        switch (edges.size()) {
          case 0: return Optional.empty();
          case 1: return Optional.of(edges.get(0));
          default: return getAnswer(edges);
        }
      }
      for (Edge e : outgoingEdges(source)) {
        if (e.getType().equals(type)) {
          return Optional.of(e);
        }
      }
    }
    return Optional.empty();
  }

  @Override
  public Optional<Edge> getAnswer(String v1) {
    Vertex source = new Concept(v1.replaceAll(" ", "_"));
    if (containsVertex(source)) {
      List<Edge> edges = new ArrayList<>(outgoingEdges(source));
      return getAnswer(edges);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Edge> getAnswer(List<Edge> edges) {
    edges.sort((a,b) -> b.getWeight() - a.getWeight());
    return Optional.of(edges.get(0));
  }

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

  public static void main(String[] args) throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    //FIND NODO RELAZIONE -> DAMMI UN ALTRO NODO ( RESTITUISCE OPTIONAL VUOTO SE NON  PRESENTE ALCUNA RELAZIONE DI QUEL TIPO)
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A,"setter",new ArrayList<>(),"BE");
    System.out.println(sn.query(sq).get());

    //FIND NODO -> DAMMI RELAZIONE E ALTRO NODO ( NON ESISTE UN COSTRUTTORE SENZA SEMANTICRELATION, CREADO ANDREA MARINO ANCORA LO DEVE FARE)
    SemanticQuery sq1 = new SemanticQuery(null,"beer",new ArrayList<>(),"BE");
    System.out.println(sn.query(sq1));

    //EXIST NODO RELAZIONE NODO ( NON SONO PAZZO ANDREA MARINO HA INTESO SOGGETTO COME TARGET E OGGETTO COME SOURCE)
    SemanticQuery sq2 = new SemanticQuery(SemanticRelationType.IS_A,"person","christian",new ArrayList<>(),"BE");
    System.out.println(sn.exist(sq2.getObject(), SemanticRelationType.IS_A, sq2.getSubject()));

    //INSERT NODO RELAZIONE NODO ( DATO CHE NON HO L'INFORMAZIONE DELLA SEMANTIC QUERY SQ2 LA IMPARO E RICONTROLLO SE ESISTE)
    sn.query(sq2);

    //RICONTROLLO
    System.out.println(sn.exist(sq2.getObject(), SemanticRelationType.IS_A, sq2.getSubject()));

  }
}
