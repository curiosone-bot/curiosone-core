package com.github.bot.curiosone.core.knowledge;

import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SemanticQueryTest {

  @Test
  public void testGetRelation() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "dog", "BE");
    assertThat(sq.getRelation()).isEqualTo(SemanticRelationType.IS_A);

    sq = new SemanticQuery(SemanticRelationType.REGION, "dog", "BE");
    assertThat(sq.getRelation()).isEqualTo(SemanticRelationType.REGION);

    sq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "dog", "BE");
    assertThat(sq.getRelation()).isEqualTo(SemanticRelationType.ENTAILMENT);
  }

  @Test
  public void testGetSubject() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "dog", "animal", "BE");
    assertThat(sq.getSubject()).isEqualTo("dog");

    sq = new SemanticQuery(SemanticRelationType.IS_A, "", "animal", "BE");
    assertThat(sq.getSubject()).isEmpty();

    sq = new SemanticQuery(SemanticRelationType.IS_A, " ", "animal", "BE");
    assertThat(sq.getSubject()).isEqualTo(" ");
  }

  @Test
  public void testGetObject() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "cat", "BE");
    assertThat(sq.getObject()).isEqualTo("cat");

    sq = new SemanticQuery(SemanticRelationType.IS_A, " ", "BE");
    assertThat(sq.getObject()).isEqualTo(" ");

    sq = new SemanticQuery(SemanticRelationType.IS_A, "", "BE");
    assertThat(sq.getObject()).isEmpty();
  }

  @Test
  public void testGetAdjectives() {
    SemanticQuery sq =
        new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", new ArrayList<>(), "BE");
    assertThat(sq.getAdjectives()).isNotNull().isEmpty();

    List<String> la = new ArrayList(asList("Beautiful", "Gracious", "Puffy"));
    sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", la, "BE");
    assertThat(sq.getAdjectives()).containsOnly("Beautiful", "Gracious", "Puffy");
  }

  @Test
  public void testGetVerb() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.TIME, "time", "time", "VERB");
    assertThat(sq.getVerb()).isEqualTo("VERB");

    sq = new SemanticQuery(SemanticRelationType.TIME, "a", "b", "x");
    assertThat(sq.getVerb()).isEqualTo("x");

    sq = new SemanticQuery(SemanticRelationType.TIME, "time", "time", "");
    assertThat(sq.getVerb()).isEqualTo("");

    sq = new SemanticQuery(SemanticRelationType.TIME, "time", "time", "  ");
    assertThat(sq.getVerb()).isEqualTo("  ");
  }

  @Test
  public void testEquals() {
    SemanticQuery sq =
        new SemanticQuery(SemanticRelationType.IS_PERSON, "Navigli", "Professor", "BE");
    SemanticQuery sqq =
        new SemanticQuery(SemanticRelationType.IS_PERSON, "Navigli", "Professor", "BE");
    assertThat(sq).isEqualTo(sqq);
  }

  @Test
  public void testEqualsReflexive() {
    SemanticQuery sq =
        new SemanticQuery(SemanticRelationType.IS_PERSON, "Navigli", "Professor", "BE");
    assertThat(sq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.HYPERNYM, "car", "vehicle", "BE");
    assertThat(sq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.TIME, "totally", "random", "HAVE");
    assertThat(sq).isEqualTo(sq);
  }

  /*
  TODO: fix null arguments in SemanticQuery constructors.
  @Test
  public void testEqualsSymmetric() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.SIMILAR_TO, "a", "b");
    SemanticQuery sqq = new SemanticQuery(SemanticRelationType.SIMILAR_TO, "a", "b");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.TIME, "a", "b", "c");
    sqq = new SemanticQuery(SemanticRelationType.TIME, "a", "b", "c");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.IS_PERSON, "", "", "");
    sqq = new SemanticQuery(SemanticRelationType.IS_PERSON, "", "", "");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sq);
  }

  @Test
  public void testEqualsTransitive() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.SIMILAR_TO, "a", "b");
    SemanticQuery sqq = new SemanticQuery(SemanticRelationType.SIMILAR_TO, "a", "b");
    SemanticQuery sqqq = new SemanticQuery(SemanticRelationType.SIMILAR_TO, "a", "b");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sqqq);
    assertThat(sqqq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.REGION, "+-", "*:");
    sqq = new SemanticQuery(SemanticRelationType.REGION, "+-", "*:");
    sqqq = new SemanticQuery(SemanticRelationType.REGION, "+-", "*:");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sqqq);
    assertThat(sqqq).isEqualTo(sq);

    sq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "a", "b");
    sqq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "a", "b");
    sqqq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "a", "b");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sqq).isEqualTo(sqqq);
    assertThat(sqqq).isEqualTo(sq);
  }
  */

  @Test
  public void testEqualsCoherent() {
    SemanticQuery sq =
        new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", new ArrayList<>(), "BE");
    SemanticQuery sqq =
        new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq).isEqualTo(sqq);

    sqq = new SemanticQuery(SemanticRelationType.IS_A, "ANIMAL", "cat", new ArrayList<>(), "BE");
    assertThat(sq).isNotEqualTo(sqq);
  }

  @Test
  public void testEqualsNotNull() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq).isNotEqualTo(null);

    sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", new ArrayList<>(), "BE");
    assertThat(sq).isNotEqualTo(null);

    sq = new SemanticQuery(SemanticRelationType.IS_A, "home", "OWN");
    assertThat(sq).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq).isNotEqualTo(new Integer(42));

    sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", new ArrayList<>(), "BE");
    assertThat(sq).isNotEqualTo(new ArrayList<>());

    sq = new SemanticQuery(SemanticRelationType.IS_A, "home", "OWN");
    assertThat(sq).isNotEqualTo("home, OWN");
  }

  @Test
  public void testEqualsHashCodeContract() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    SemanticQuery sqq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sq.hashCode()).isEqualTo(sqq.hashCode());

    sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", new ArrayList<>(), "BE");
    sqq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq).isEqualTo(sqq);
    assertThat(sq.hashCode()).isEqualTo(sqq.hashCode());

    sq = new SemanticQuery(SemanticRelationType.IS_A, "ANIMAL", "cat", "BE");
    sqq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "CAT", "BE");
    assertThat(sq).isNotEqualTo(sqq);
    assertThat(sq.hashCode()).isNotEqualTo(sqq.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.IS_A, "animal", "cat", "BE");
    assertThat(sq.hashCode()).isEqualTo(sq.hashCode());

    sq = new SemanticQuery(SemanticRelationType.IS_A, " ", "", "");
    assertThat(sq.hashCode()).isEqualTo(sq.hashCode());
  }

  @Test
  public void testHashCodeCoherent() {
    SemanticQuery sq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "pen", "ink", "HAVE");
    SemanticQuery sqq = new SemanticQuery(SemanticRelationType.ENTAILMENT, "pen", "ink", "HAVE");
    assertThat(sq.hashCode()).isEqualTo(sqq.hashCode());

    sq =
        new SemanticQuery(SemanticRelationType.ENTAILMENT, "pen", "ink", new ArrayList<>(), "HAVE");
    assertThat(sq.hashCode()).isEqualTo(sqq.hashCode());

    sq = new SemanticQuery(SemanticRelationType.TIME, "tatto", "ink", new ArrayList<>(), "BE");
    assertThat(sq.hashCode()).isNotEqualTo(sqq.hashCode());
  }
}
