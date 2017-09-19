package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PhraseTest {
  @Test
  public void testInstantiation() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    assertTrue(classUnderTest instanceof Phrase);
  }

  @Test
  public void testEqualsReflexive() {
    Phrase p = new Phrase("Fortytwo is a beautiful number.");
    assertTrue(p.equals(p));

    p = new Phrase("#42 is the answer!");
    assertTrue(p.equals(p));
  }

  @Test
  public void testEqualsSymmetric() {
    Phrase p = new Phrase("The cat is on the table.");
    Phrase pp = new Phrase("The cat is on the table.");
    assertTrue(p.equals(pp));
    assertTrue(pp.equals(p));

    p = new Phrase("The dog is sleeping on the couch.");
    pp = new Phrase("The dog is sleeping on the couch.");
    assertTrue(p.equals(pp));
    assertTrue(pp.equals(p));
  }

  @Test
  public void testEqualsTransitive() {
    Phrase p = new Phrase("The transitive property is a useful property in math.");
    Phrase pp = new Phrase("The transitive property is a useful property in math.");
    Phrase ppp = new Phrase("The transitive property is a useful property in math.");
    assertTrue(p.equals(pp));
    assertTrue(pp.equals(ppp));
    assertTrue(ppp.equals(p));
  }

  @Test
  public void testEqualsNullComparison() {
    Phrase p = new Phrase("A null value indicates an unset reference.");
    assertFalse(p.equals(null));

    p = new Phrase("130");
    assertFalse(p.equals(null));
  }

  @Test
  public void testEqualsOtherObj() {
    Phrase p = new Phrase("Objects have states and behaviors");
    StringBuffer sb = new StringBuffer("Objects have states and behaviors");
    assertFalse(p.equals(sb));

    p = new Phrase(
      "An array is a container object that holds a fixed number of values of a single type"
    );
    String[] as = {"This", "is", "an", "array"};
    assertFalse(p.equals(as));
  }

  @Test
  public void testEqualsContract() {
    Phrase p = new Phrase("Sign here.");
    Phrase pp = new Phrase("Sign here.");
    assertTrue(p.equals(pp));
    assertEquals(p.hashCode(), pp.hashCode());

    pp = new Phrase("Do not sign here!");
    assertFalse(p.equals(pp));
    assertNotEquals(p.hashCode(), pp.hashCode());
  }

  @Test
  public void testExtract() {
    List<Phrase> phrase = Phrase.extract("All by myself");
    assertEquals(1, phrase.size());

    phrase = Phrase.extract("The cat is on the table! But I love dogs.");
    assertEquals(2, phrase.size());

    phrase = Phrase.extract(
        "This the first sentence. Time for the second sentence! What about the third?");
    assertEquals(3,phrase.size());
  }

  @Test
  public void testGetText() {
    Phrase p = new Phrase("Why is the cat always on the table?");
    assertEquals("Why is the cat always on the table?", p.getText());

    p = new Phrase("I l0v3 numb3rs111");
    assertEquals("I l0v3 numb3rs111", p.getText());
  }

  @Test
  public void testHashCodeReflexive() {
    Phrase p = new Phrase("Is my hashCode reflexive?");
    assertEquals(p.hashCode(), p.hashCode());

    p = new Phrase("I have a reflexive hashCode too!");
    assertEquals(p.hashCode(), p.hashCode());
  }

  @Test
  public void testHashCodeTransitive() {
    Phrase p = new Phrase("Insert a sentence here...");
    Phrase pp = new Phrase("Insert a sentence here...");
    Phrase ppp = new Phrase("Insert a sentence here...");
    assertEquals(p.hashCode(),pp.hashCode());
    assertEquals(pp.hashCode(),ppp.hashCode());
    assertEquals(ppp.hashCode(),p.hashCode());
  }
}
