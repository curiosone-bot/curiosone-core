package com.github.bot.curiosone.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PairTest {

  @Test
  public void testGetFirst() {
    Pair p = new Pair("First", "Second");
    assertThat(p.getFirst()).isEqualTo("First");

    p = new Pair(42, "THE ANSWER!");
    assertThat(p.getFirst()).isEqualTo(42);
  }

  @Test
  public void testGetSecond() {
    Pair p = new Pair("First", "Second");
    assertThat(p.getSecond()).isEqualTo("Second");

    p = new Pair(42, "THE ANSWER!");
    assertThat(p.getSecond()).isEqualTo("THE ANSWER!");
  }

  @Test
  public void testEqualsReflexive() {
    Pair p = new Pair(10, 20);
    assertThat(p).isEqualTo(p);

    p = new Pair(10, "Ten");
    assertThat(p).isEqualTo(p);

    p = new Pair("Hey", "There");
    assertThat(p).isEqualTo(p);
  }

  @Test
  public void testEqualsSymmetric() {
    Pair p = new Pair(10, 20);
    Pair pp = new Pair(10, 20);
    assertThat(pp).isEqualTo(p);
    assertThat(p).isEqualTo(pp);

    p = new Pair(10, "Ten");
    pp = new Pair(10, "Ten");
    assertThat(pp).isEqualTo(p);
    assertThat(p).isEqualTo(pp);

    p = new Pair("Hey", "There");
    pp = new Pair("Hey", "There");
    assertThat(pp).isEqualTo(p);
    assertThat(p).isEqualTo(pp);
  }

  @Test
  public void testEqualsTransitive() {
    Pair p = new Pair(10, 20);
    Pair pp = new Pair(10, 20);
    Pair ppp = new Pair(10, 20);
    assertThat(p).isEqualTo(pp);
    assertThat(pp).isEqualTo(ppp);
    assertThat(ppp).isEqualTo(p);

    p = new Pair(10, "Ten");
    pp = new Pair(10, "Ten");
    ppp = new Pair(10, "Ten");
    assertThat(p).isEqualTo(pp);
    assertThat(pp).isEqualTo(ppp);
    assertThat(ppp).isEqualTo(p);

    p = new Pair("Hey", "There");
    pp = new Pair("Hey", "There");
    ppp = new Pair("Hey", "There");
    assertThat(p).isEqualTo(pp);
    assertThat(pp).isEqualTo(ppp);
    assertThat(ppp).isEqualTo(p);
  }

  @Test
  public void testEqualsNullComparison() {
    Pair p = new Pair(1, 2);
    assertThat(p).isNotEqualTo(null);

    p = new Pair(1, "two");
    assertThat(p).isNotEqualTo(null);

    p = new Pair(new StringBuilder(), 2);
    assertThat(p).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Pair p = new Pair(1, 2);
    assertThat(p).isNotEqualTo(new StringBuffer());

    p = new Pair(1, "two");
    assertThat(p).isNotEqualTo(1.2);

    p = new Pair(new StringBuilder(), 2);
    assertThat(p).isNotEqualTo(new StringBuilder());
  }

  @Test
  public void testNotEqual() {
    Pair p = new Pair(1, 2);
    Pair pp = new Pair(2, 1);
    assertThat(p).isNotEqualTo(pp);

    p = new Pair("abc", 2);
    pp = new Pair(2, 1);
    assertThat(p).isNotEqualTo(pp);

    p = new Pair("Hi", "Hello");
    pp = new Pair("hi", "HellO");
    assertThat(p).isNotEqualTo(pp);
  }

  @Test
  public void testEqualsHashCodeContract() {
    Pair p = new Pair("new", "pair");
    Pair pp = new Pair("new", "pair");
    assertThat(p).isEqualTo(pp);
    assertThat(p.hashCode()).isEqualTo(pp.hashCode());

    p = new Pair("new", "pair");
    pp = new Pair("new", "PAIR");
    assertThat(p).isNotEqualTo(pp);
    assertThat(p.hashCode()).isNotEqualTo(pp.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    Pair p = new Pair(44, 6);
    assertThat(p.hashCode()).isEqualTo(p.hashCode());

    p = new Pair("44", 6);
    assertThat(p.hashCode()).isEqualTo(p.hashCode());

    p = new Pair(new StringBuilder("Builder"), new StringBuffer("Buffer"));
    assertThat(p.hashCode()).isEqualTo(p.hashCode());
  }

  @Test
  public void testToString() {
    Pair p = new Pair("Test", "toString");
    assertThat(p.toString()).isEqualTo("(Test, toString)");
  }

  @Test
  public void testCreate() {
    Pair p = new Pair("a new", "pair");
    Pair pp = Pair.create("a new", "pair");
    assertThat(p).isEqualTo(pp);
  }
}
