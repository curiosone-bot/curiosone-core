package com.github.bot.curiosone.core.knowledge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ConceptTest {

  @Test
  public void testGetId() {
    Concept c = new Concept("Concept");
    assertThat(c.getId()).isEqualTo("Concept");

    c = new Concept("42");
    assertThat(c.getId()).isEqualTo("42");

    c = new Concept("the answer!");
    assertThat(c.getId()).isEqualTo("the answer!");

    c = new Concept("");
    assertThat(c.getId()).isEqualTo("");

    c = new Concept(" ");
    assertThat(c.getId()).isEqualTo(" ");
  }

  @Test
  public void testToString() {
    Concept c = new Concept("Concept");
    assertThat(c.toString()).isEqualTo("Concept");

    c = new Concept("this is a Concept");
    assertThat(c.toString()).isEqualTo("this is a Concept");

    c = new Concept("4 2 ");
    assertThat(c.toString()).isEqualTo("4 2 ");

    c = new Concept(" ");
    assertThat(c.toString()).isEqualTo(" ");

    c = new Concept("");
    assertThat(c.toString()).isEmpty();
  }

  @Test
  public void testEqualsReflexive() {
    Concept c = new Concept("hot water");
    assertThat(c).isEqualTo(c);

    c = new Concept(" ");
    assertThat(c).isEqualTo(c);

    c = new Concept("");
    assertThat(c).isEqualTo(c);

    c = new Concept("42");
    assertThat(c).isEqualTo(c);
  }

  @Test
  public void testEqualsSymmetric() {
    Concept c = new Concept("Material design");
    Concept cc = new Concept("Material design");
    assertThat(cc).isEqualTo(c);
    assertThat(c).isEqualTo(cc);

    c = new Concept("");
    cc = new Concept("");
    assertThat(cc).isEqualTo(c);
    assertThat(c).isEqualTo(cc);

    c = new Concept("   ");
    cc = new Concept("   ");
    assertThat(cc).isEqualTo(c);
    assertThat(c).isEqualTo(cc);

    c = new Concept("+-.");
    cc = new Concept("+-.");
    assertThat(cc).isEqualTo(c);
    assertThat(c).isEqualTo(cc);
  }

  @Test
  public void testEqualsTransitive() {
    Concept c = new Concept("Material design");
    Concept cc = new Concept("Material design");
    Concept ccc = new Concept("Material design");
    assertThat(c).isEqualTo(cc);
    assertThat(cc).isEqualTo(ccc);
    assertThat(ccc).isEqualTo(c);

    c = new Concept("transitive property");
    cc = new Concept("transitive property");
    ccc = new Concept("transitive property");
    assertThat(c).isEqualTo(cc);
    assertThat(cc).isEqualTo(ccc);
    assertThat(ccc).isEqualTo(c);

    c = new Concept(" ");
    cc = new Concept(" ");
    ccc = new Concept(" ");
    assertThat(c).isEqualTo(cc);
    assertThat(cc).isEqualTo(ccc);
    assertThat(ccc).isEqualTo(c);

    c = new Concept("");
    cc = new Concept("");
    ccc = new Concept("");
    assertThat(c).isEqualTo(cc);
    assertThat(cc).isEqualTo(ccc);
    assertThat(ccc).isEqualTo(c);
  }

  @Test
  public void testEqualsNullComparison() {
    Concept c = new Concept("null comparison");
    assertThat(c).isNotEqualTo(null);

    c = new Concept("");
    assertThat(c).isNotEqualTo(null);

    c = new Concept(" ");
    assertThat(c).isNotEqualTo(null);

    c = new Concept("42");
    assertThat(c).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Concept c = new Concept("object");
    assertThat(c).isNotEqualTo(new Object());

    c = new Concept("object");
    assertThat(c).isNotEqualTo(new StringBuffer());

    c = new Concept("object");
    assertThat(c).isNotEqualTo(new Double(42.24));
  }

  @Test
  public void testHashCodeReflexive() {
    Concept c = new Concept("hi, hola!");
    assertThat(c.hashCode()).isEqualTo(c.hashCode());

    c = new Concept("");
    assertThat(c.hashCode()).isEqualTo(c.hashCode());

    c = new Concept(" ");
    assertThat(c.hashCode()).isEqualTo(c.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    Concept c = new Concept("42 is the answer!");
    Concept cc = new Concept("42 is the answer!");
    assertThat(c).isEqualTo(cc);
    assertThat(c.hashCode()).isEqualTo(cc.hashCode());

    c = new Concept("");
    cc = new Concept("");
    assertThat(c).isEqualTo(cc);
    assertThat(c.hashCode()).isEqualTo(cc.hashCode());

    c = new Concept(" ");
    cc = new Concept(" ");
    assertThat(c).isEqualTo(cc);
    assertThat(c.hashCode()).isEqualTo(cc.hashCode());
  }
}
