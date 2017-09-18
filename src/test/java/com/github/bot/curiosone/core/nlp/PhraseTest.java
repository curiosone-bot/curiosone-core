package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class PhraseTest {
  @Test
  public void testInstantiation() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    assertTrue(classUnderTest instanceof Phrase);
  }

  @Test
  public void testGetText() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    assertEquals("The cat is on the table!", classUnderTest.getText());

    classUnderTest = new Phrase("AND THE DOG TOO!");
    assertEquals("AND THE DOG TOO!", classUnderTest.getText());

    classUnderTest = new Phrase("42 is the answer, don't you think?");
    assertEquals("42 is the answer, don't you think?",
        classUnderTest.getText());

    classUnderTest = new Phrase("4224");
    assertEquals("4224", classUnderTest.getText());

    classUnderTest = new Phrase("!!!______!!!");
    assertNotEquals("!!!!!", classUnderTest.getText());

    classUnderTest = new Phrase("abcdefg");
    assertNotEquals("ABCDEFG", classUnderTest.getText());

    classUnderTest = new Phrase("The cat is on the table");
    assertNotEquals("The dragon is flying up in the sky",
        classUnderTest.getText());

    classUnderTest = new Phrase("I think it's enough");
    assertNotEquals("I think it's NOT enough!", classUnderTest.getText());
  }

  @Test
  public void testEquals() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    Phrase classUnderTestClone = new Phrase("The cat is on the table!");
    assertEquals(classUnderTest, classUnderTestClone);
  }

  @Test
  public void testExtract() {
    List<Phrase> phrases = Phrase.extract("The cat is on the table! But I love dogs.");
    assertEquals(2, phrases.size());
  }
}
