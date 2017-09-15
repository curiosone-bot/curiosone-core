package com.github.bot.curiosone.core.nlp.tokenizer;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class PhraseTest {
  @Test
  public void testInstantiation() {
    Phrase classUnderTest = new Phrase("The cat is on the table!");
    assertTrue(classUnderTest instanceof Phrase);

    assertEquals(PhraseType.EXCLAMATION, classUnderTest.getType());
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
