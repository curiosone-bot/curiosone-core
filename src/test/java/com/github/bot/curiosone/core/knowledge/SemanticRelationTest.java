package com.github.bot.curiosone.core.knowledge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SemanticRelationTest {

  @Test
  public void testGetSource() {
    Concept c = new Concept("hi");
    Concept cc = new Concept("there!");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.TIME);
    assertThat(sr.getSource()).isEqualTo(new Concept("hi"));

    sr = new SemanticRelation(cc, c, SemanticRelationType.SIMILAR_TO);
    assertThat(sr.getSource()).isEqualTo(new Concept("there!"));

    cc = new Concept("");
    sr = new SemanticRelation(cc, c, SemanticRelationType.SIMILAR_TO);
    assertThat(sr.getSource()).isEqualTo(new Concept(""));
  }

  @Test
  public void testGetTarget() {
    Concept c = new Concept("progressive");
    Concept cc = new Concept("house");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr.getTarget()).isEqualTo(new Concept("house"));

    sr = new SemanticRelation(cc, c, SemanticRelationType.SIMILAR_TO);
    assertThat(sr.getTarget()).isEqualTo(new Concept("progressive"));

    c = new Concept("");
    sr = new SemanticRelation(cc, c, SemanticRelationType.SIMILAR_TO);
    assertThat(sr.getTarget()).isEqualTo(new Concept(""));
  }

  @Test
  public void testGetType() {
    Concept c = new Concept("gold");
    Concept cc = new Concept("digger");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    assertThat(sr.getType()).isEqualTo(SemanticRelationType.HYPERNYM);

    sr = new SemanticRelation(cc, c, SemanticRelationType.IS_PERSON);
    assertThat(sr.getType()).isEqualTo(SemanticRelationType.IS_PERSON);

    sr = new SemanticRelation(c, cc, SemanticRelationType.ENTAILMENT);
    assertThat(sr.getType()).isEqualTo(SemanticRelationType.ENTAILMENT);
  }

  @Test
  public void testGetWeight() {
    Concept c = new Concept("JAVA");
    Concept cc = new Concept("LOVE");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM, 42);
    assertThat(sr.getWeight()).isEqualTo(42);

    sr = new SemanticRelation(cc, c, SemanticRelationType.HYPERNYM, 1518);
    assertThat(sr.getWeight()).isEqualTo(1518);

    sr = new SemanticRelation(cc, c, SemanticRelationType.HYPERNYM, 0);
    assertThat(sr.getWeight()).isZero();
  }

  @Test
  public void testSetWeight() {
    Concept c = new Concept("JAVA");
    Concept cc = new Concept("LOVE");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM, 42);
    sr.setWeight(84);
    assertThat(sr.getWeight()).isEqualTo(84);

    sr = new SemanticRelation(cc, c, SemanticRelationType.HYPERNYM);
    sr.setWeight(20);
    assertThat(sr.getWeight()).isEqualTo(20);

    sr = new SemanticRelation(cc, c, SemanticRelationType.HYPERNYM);
    sr.setWeight(67);
    assertThat(sr.getWeight()).isEqualTo(67);
  }

  @Test
  public void testToString() {
    Concept c = new Concept("to");
    Concept cc = new Concept("String");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.TIME);
    assertThat(sr.toString()).isNotNull().isNotEmpty();

    c = new Concept("  ");
    cc = new Concept("");
    sr = new SemanticRelation(cc, c, SemanticRelationType.IS_PERSON);
    assertThat(sr.toString()).isNotNull().isNotEmpty();
  }

  @Test
  public void testEqualsReflexive() {
    Concept c = new Concept("reflexivity");
    Concept cc = new Concept("property");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr).isEqualTo(sr);

    c = new Concept("hug");
    cc = new Concept("affect");
    sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    assertThat(sr).isEqualTo(sr);

    c = new Concept("watch");
    cc = new Concept("display");
    sr = new SemanticRelation(c, cc, SemanticRelationType.TIME);
    assertThat(sr).isEqualTo(sr);
  }

  @Test
  public void testEqualsSymmetric() {
    Concept c = new Concept("reflexivity");
    Concept cc = new Concept("property");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    SemanticRelation srr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(sr);

    c = new Concept("cola");
    cc = new Concept("drink");
    sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    srr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(sr);

    c = new Concept("pizza");
    cc = new Concept("food");
    sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    srr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(sr);
  }

  @Test
  public void testEqualsTransitive() {
    Concept c = new Concept("transitivity");
    Concept cc = new Concept("property");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    SemanticRelation srr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    SemanticRelation srrr = new SemanticRelation(c, cc, SemanticRelationType.IS_A);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(srrr);
    assertThat(srrr).isEqualTo(sr);

    c = new Concept("air");
    cc = new Concept("invisible");
    sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    srr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    srrr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(srrr);
    assertThat(srrr).isEqualTo(sr);

    c = new Concept("here");
    cc = new Concept("right now!");
    sr = new SemanticRelation(c, cc, SemanticRelationType.ENTAILMENT);
    srr = new SemanticRelation(c, cc, SemanticRelationType.ENTAILMENT);
    srrr = new SemanticRelation(c, cc, SemanticRelationType.ENTAILMENT);
    assertThat(sr).isEqualTo(srr);
    assertThat(srr).isEqualTo(srrr);
    assertThat(srrr).isEqualTo(sr);
  }

  @Test
  public void testEqualsNotNull() {
    Concept c = new Concept("null");
    Concept cc = new Concept("comparison");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.IS_PERSON);
    assertThat(sr).isNotEqualTo(null);

    c = new Concept("key");
    cc = new Concept("door");
    sr = new SemanticRelation(c, cc, SemanticRelationType.REGION);
    assertThat(sr).isNotEqualTo(null);

    c = new Concept("");
    cc = new Concept("      ");
    sr = new SemanticRelation(c, cc, SemanticRelationType.ENTAILMENT);
    assertThat(sr).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Concept c = new Concept("other");
    Concept cc = new Concept("obj");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.HYPERNYM);
    assertThat(sr).isNotEqualTo(new Object());

    sr = new SemanticRelation(cc, c, SemanticRelationType.HYPERNYM);
    assertThat(sr).isNotEqualTo(new String());

    sr = new SemanticRelation(cc, cc, SemanticRelationType.TIME);
    assertThat(sr).isNotEqualTo(42);
  }

  @Test
  public void testHashCodeReflexive() {
    Concept c = new Concept("yet again");
    Concept cc = new Concept("reflexivity");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.REGION);
    assertThat(sr.hashCode()).isEqualTo(sr.hashCode());

    sr = new SemanticRelation(c, c, SemanticRelationType.REGION);
    assertThat(sr.hashCode()).isEqualTo(sr.hashCode());
  }

  @Test
  public void testHashCodeCoherent() {
    Concept c = new Concept("coherency");
    Concept cc = new Concept("test");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.REGION, 42);
    SemanticRelation srr = new SemanticRelation(c, cc, SemanticRelationType.REGION);
    assertThat(sr.hashCode()).isEqualTo(srr.hashCode());

    srr = new SemanticRelation(cc, c, SemanticRelationType.REGION, 42);
    assertThat(sr.hashCode()).isNotEqualTo(srr.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    Concept c = new Concept("coherency");
    Concept cc = new Concept("test");
    SemanticRelation sr = new SemanticRelation(c, cc, SemanticRelationType.REGION, 42);
    SemanticRelation srr = new SemanticRelation(c, cc, SemanticRelationType.REGION);
    assertThat(sr).isEqualTo(srr);
    assertThat(sr.hashCode()).isEqualTo(srr.hashCode());

    c = new Concept("AbCd");
    cc = new Concept("eFgH");
    sr = new SemanticRelation(c, cc, SemanticRelationType.REGION, 42);
    srr = new SemanticRelation(cc, c, SemanticRelationType.REGION);
    assertThat(sr).isNotEqualTo(srr);
    assertThat(sr.hashCode()).isNotEqualTo(srr.hashCode());
  }
}
