package com.github.bot.curiosone.core.knowledge;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class SemanticNetworkTest {

  @Test
  public void testGetInstance() throws IOException {
    assertThat(SemanticNetwork.getInstance()).isNotNull();
  }

  @Test
  public void testAddEdgeConcept() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    int prevSize = sn.edgeSet().size();

    Concept s = new Concept("Source");
    Concept t = new Concept("Destination");
    SemanticRelation sr = new SemanticRelation(s, t, SemanticRelationType.IS_A);
    sn.add(sr);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    s = new Concept("AAAA");
    t = new Concept("BBBB");
    sr = new SemanticRelation(s, t, SemanticRelationType.SIMILAR_TO);
    sn.add(sr);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    s = new Concept("dependable");
    t = new Concept("certain");
    sr = new SemanticRelation(s, t, SemanticRelationType.SIMILAR_TO);
    sn.add(sr);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize);
  }

  @Test
  public void testAddEdges() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    int prevSize = sn.edgeSet().size();

    Concept s = new Concept("Sourceee");
    Concept t = new Concept("Destinationnn");
    SemanticRelation sr = new SemanticRelation(s, t, SemanticRelationType.IS_A);
    Concept ss = new Concept("yet another");
    Concept tt = new Concept("destination Vertex");
    SemanticRelation srr = new SemanticRelation(ss, tt, SemanticRelationType.TIME);
    sn.addEdges(asList(sr, srr));
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 2);
    prevSize += 2;

    s = new Concept("1234");
    t = new Concept("5678");
    sr = new SemanticRelation(s, t, SemanticRelationType.REGION);
    ss = new Concept("42");
    tt = new Concept("24");
    srr = new SemanticRelation(ss, tt, SemanticRelationType.IS_PERSON);
    Concept sss = new Concept("Animal");
    Concept ttt = new Concept("Kingdom");
    SemanticRelation srrr = new SemanticRelation(ss, tt, SemanticRelationType.ENTAILMENT);
    sn.addEdges(asList(sr, srr, srrr));
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 3);
    prevSize += 3;

    sn.addEdges(asList(sr, srr, srrr, sr, srr));
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize);
  }

  @Test
  public void testAddVertex() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    int prevSize = sn.vertexSet().size();

    Concept c = new Concept("thisIsAConcept");
    sn.add(c);
    assertThat(sn.vertexSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    c = new Concept("clubLife");
    sn.add(c);
    assertThat(sn.vertexSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    c = new Concept("apple");
    sn.add(c);
    assertThat(sn.vertexSet().size()).isEqualTo(prevSize);

    c = new Concept("dog");
    sn.add(c);
    assertThat(sn.vertexSet().size()).isEqualTo(prevSize);
  }

  @Test
  public void testAddEdgeConstruct() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    int prevSize = sn.edgeSet().size();

    Concept s = new Concept("craftme");
    Concept t = new Concept("please!");
    sn.addEdge(s, t, SemanticRelationType.TIME, 42);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    s = new Concept("happynewyear");
    t = new Concept("bestwishes!");
    sn.addEdge(s, t, SemanticRelationType.TIME, 84);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize + 1);
    prevSize++;

    /*
    s = new Concept("apple");
    t = new Concept("fruit");
    sn.addEdge(s, t, SemanticRelationType.IS_A, 84);
    assertThat(sn.edgeSet().size()).isEqualTo(prevSize);
    */
  }

  @Test
  public void testContainsEdge() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();

    Concept s = new Concept("dependable");
    Concept t = new Concept("certain");
    SemanticRelation sr = new SemanticRelation(s, t, SemanticRelationType.SIMILAR_TO);
    assertThat(sn.containsEdge(sr)).isTrue();

    s = new Concept("gouache");
    t = new Concept("paint");
    sr = new SemanticRelation(s, t, SemanticRelationType.HYPERNYM, 34);
    assertThat(sn.containsEdge(sr)).isTrue();

    s = new Concept("metonymy");
    t = new Concept("trope");
    sr = new SemanticRelation(s, t, SemanticRelationType.HYPERNYM, 18);
    assertThat(sn.containsEdge(sr)).isTrue();

    s = new Concept("jdkfjcsklj");
    t = new Concept("ejlfdsclkjsd");
    sr = new SemanticRelation(s, t, SemanticRelationType.IS_A, 130);
    assertThat(sn.containsEdge(sr)).isFalse();

    s = new Concept(" ");
    t = new Concept(" ");
    sr = new SemanticRelation(s, t, SemanticRelationType.TIME, 118);
    assertThat(sn.containsEdge(sr)).isFalse();
  }

  @Test
  public void testContainsVertex() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();

    Concept c = new Concept("apple");
    assertThat(sn.containsVertex(c)).isTrue();

    c = new Concept("water");
    assertThat(sn.containsVertex(c)).isTrue();

    c = new Concept("dog");
    assertThat(sn.containsVertex(c)).isTrue();

    c = new Concept("cat");
    assertThat(sn.containsVertex(c)).isTrue();

    c = new Concept("totallyRandommm");
    assertThat(sn.containsVertex(c)).isFalse();

    c = new Concept("APELLEFIGLIODAPOLLO");
    assertThat(sn.containsVertex(c)).isFalse();
  }

  @Test
  public void testOutgoingEdges() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();

    Concept c = new Concept("sky");
    assertThat(sn.outgoingEdges(c)).isNotEmpty();

    c = new Concept("tie");
    assertThat(sn.outgoingEdges(c)).isNotEmpty();

    c = new Concept("time");
    assertThat(sn.outgoingEdges(c)).isNotEmpty();

    c = new Concept("djhfvkjhskd,jvckdsbzcnsdbcmndbfjh");
    assertThat(sn.outgoingEdges(c)).isEmpty();

    c = new Concept("34wedfdjhfvkjhskdjvckdsbzcnsdbcmndbfjh343454");
    assertThat(sn.outgoingEdges(c)).isEmpty();
  }

  @Test
  public void testIncomingEdges() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();

    Concept c = new Concept("run");
    assertThat(sn.incomingEdges(c)).isNotEmpty();

    c = new Concept("drink");
    assertThat(sn.incomingEdges(c)).isNotEmpty();

    c = new Concept("eat");
    assertThat(sn.incomingEdges(c)).isNotEmpty();

    c = new Concept("flag");
    assertThat(sn.incomingEdges(c)).isNotEmpty();

    c = new Concept("YoullNotFindMe:)");
    assertThat(sn.incomingEdges(c)).isEmpty();

    c = new Concept("jskhc");
    assertThat(sn.incomingEdges(c)).isEmpty();

    c = new Concept("    ");
    assertThat(sn.incomingEdges(c)).isEmpty();
  }

  @Test
  public void testExist() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();

    assertThat(sn.exist("derived", SemanticRelationType.SIMILAR_TO, "derivative")).isTrue();

    assertThat(sn.exist("puppy", SemanticRelationType.HYPERNYM, "dog")).isTrue();

    assertThat(sn.exist("putout", SemanticRelationType.HYPERNYM, "statistic")).isTrue();

    assertThat(sn.exist("rfdsc", SemanticRelationType.TIME, "vfdvfd")).isFalse();

    assertThat(sn.exist("SSSiiimmmiilar", SemanticRelationType.SIMILAR_TO, "to")).isFalse();
  }

  @Test
  public void testLearn() throws IOException {
    SemanticNetwork sn = SemanticNetwork.getInstance();
    int prevEdgesSize = sn.edgeSet().size();

    sn.learn("vefdsf", SemanticRelationType.TIME, "vefdscfds");
    assertThat(sn.edgeSet().size()).isEqualTo(prevEdgesSize + 1);
    prevEdgesSize++;

    sn.learn("123455678900", SemanticRelationType.TIME, "00987654321%");
    assertThat(sn.edgeSet().size()).isEqualTo(prevEdgesSize + 1);
    prevEdgesSize++;

    sn.learn("123455678900", SemanticRelationType.TIME, "00987654321%");
    assertThat(sn.edgeSet().size()).isEqualTo(prevEdgesSize);
  }
}
