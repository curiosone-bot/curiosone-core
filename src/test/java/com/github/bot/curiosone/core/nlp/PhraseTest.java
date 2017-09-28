package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.nlp.Phrase.extract;
import static org.assertj.core.api.Assertions.assertThat;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PhraseTest {
  @Test
  public void testInstantiation() {
    Phrase p = new Phrase("The cat is on the table!");
    assertThat(p instanceof Phrase).isTrue();
  }

  @Test
  public void testEqualsReflexive() {
    Phrase p = new Phrase("Fortytwo is a beautiful number.");
    assertThat(p).isEqualTo(p);

    p = new Phrase("#42 is the answer!");
    assertThat(p).isEqualTo(p);
  }

  @Test
  public void testEqualsSymmetric() {
    Phrase p = new Phrase("The cat is on the table.");
    Phrase pp = new Phrase("The cat is on the table.");
    assertThat(p).isEqualTo(pp);
    assertThat(pp).isEqualTo(p);

    p = new Phrase("The dog is sleeping on the couch.");
    pp = new Phrase("The dog is sleeping on the couch.");
    assertThat(p).isEqualTo(pp);
    assertThat(pp).isEqualTo(p);
  }

  @Test
  public void testEqualsTransitive() {
    Phrase p = new Phrase("The transitive property is a useful property in math.");
    Phrase pp = new Phrase("The transitive property is a useful property in math.");
    Phrase ppp = new Phrase("The transitive property is a useful property in math.");
    assertThat(p.equals(pp) && pp.equals(ppp) && ppp.equals(p)).isTrue();
  }

  @Test
  public void testEqualsNullComparison() {
    Phrase p = new Phrase("A null value indicates an unset reference.");
    assertThat(p.equals(null)).isFalse();

    p = new Phrase("130");
    assertThat(p.equals(null)).isFalse();
  }

  @Test
  public void testEqualsOtherObj() {
    Phrase p = new Phrase("Objects have states and behaviors");
    StringBuffer sb = new StringBuffer("Objects have states and behaviors");
    assertThat(p.equals(sb)).isFalse();

    p = new Phrase(
      "An array is a container object that holds a fixed number of values of a single type"
    );
    String[] as = {"This", "is", "an", "array"};
    assertThat(p.equals(as)).isFalse();
  }

  @Test
  public void testEqualsContract() {
    Phrase p = new Phrase("Sign here.");
    Phrase pp = new Phrase("Sign here.");
    assertThat(p.equals(pp) && p.hashCode() == pp.hashCode()).isTrue();

    pp = new Phrase("Do not sign here!");
    assertThat(p.equals(pp)).isFalse();
    assertThat(p.hashCode()).isNotEqualTo(pp.hashCode());
  }

  @Test
  public void testExtract() {
    assertThat(extract("All by myself")).hasSize(1);

    assertThat(extract("The cat is on the table! But I love dogs.")).hasSize(2);

    assertThat(
        extract("This is the first sentence. Time for the second sentence! What about the third?"))
        .hasSize(3);

    assertThat(extract("")).hasSize(0);

    assertThat(extract("This, should be a single sentence.")).hasSize(1);

    assertThat(extract("42")).hasSize(1);

    assertThat(extract("...")).hasSize(0);

    assertThat(extract("a. bcd")).hasSize(2);

    assertThat(extract("a.b. ,c!")).hasSize(3);

    /*TODO: fix NullPointerException;

    assertThat(extract("###")).hasSize(1);

    assertThat(extract("abc.   !")).hasSize(2);

    assertThat(extract(". I live in Rome")).hasSize(2);

    assertThat(extract(" . I live in Rome")).hasSize(2);

    assertThat(extract("@.#.")).hasSize(0);
    */
  }

  @Test
  public void testGetText() {
    Phrase p = new Phrase("Why is the cat always on the table?");
    assertThat(p.getText()).isEqualTo("Why is the cat always on the table?");

    p = new Phrase("I l0v3 numb3rs111");
    assertThat(p.getText()).isEqualTo("I l0v3 numb3rs111");

    //TODO: fix NullPointerException
    //p = new Phrase("");
    //assertThat(p.getText()).isEqualTo("");
  }

  @Test
  public void testHashCodeReflexive() {
    Phrase p = new Phrase("Is my hashCode reflexive?");
    assertThat(p.hashCode()).isEqualTo(p.hashCode());

    p = new Phrase("I have a reflexive hashCode too!");
    assertThat(p.hashCode()).isEqualTo(p.hashCode());
  }

  @Test
  public void testHashCodeTransitive() {
    Phrase p = new Phrase("Insert a sentence here...");
    Phrase pp = new Phrase("Insert a sentence here...");
    Phrase ppp = new Phrase("Insert a sentence here...");
    assertThat(p.hashCode()).isEqualTo(pp.hashCode());
    assertThat(pp.hashCode()).isEqualTo(ppp.hashCode());
    assertThat(ppp.hashCode()).isEqualTo(p.hashCode());
  }
}
