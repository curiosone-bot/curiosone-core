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
  public void testEquals() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    Phrase classUnderTestClone = new Phrase("The cat is on the table!");
    assertEquals(classUnderTest, classUnderTest);
    assertEquals(classUnderTest, classUnderTestClone);
    assertNotEquals(null, classUnderTest);
    assertNotEquals(new StringBuffer("something different"), classUnderTest);

    classUnderTest = new Phrase("This happens with the 42 number");
    classUnderTestClone = new Phrase("This happens with the 42 number");
    assertEquals(classUnderTest, classUnderTestClone);
  }

  @Test
  public void testExtract() {
    List<Phrase> phrases = Phrase.extract("The cat is on the table! But I love dogs.");
    assertEquals(2, phrases.size());
  }

  @Test
  public void testGetText() {
    Phrase p = new Phrase ("Why is the cat always on the table?");
    assertEquals("Why is the cat always on the table?", p.getText());
    assertNotEquals("Why is the dragon always on the table?", p.getText());
  }
}
