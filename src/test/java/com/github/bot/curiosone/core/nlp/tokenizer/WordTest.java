package com.github.bot.curiosone.core.nlp.tokenizer;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class WordTest {
  @Test
  public void testInstantiation() {
    Word classUnderTest = new Word("cane", PartOfSpeechType.N, LexicalType.ANIMAL);
    assertTrue(classUnderTest instanceof Word);

    assertEquals("cane", classUnderTest.getLemma());
    assertEquals(PartOfSpeechType.N, classUnderTest.getPartOfSpeechType());
    assertEquals(LexicalType.ANIMAL, classUnderTest.getLexicalType());
  }

  @Test
  public void testEquals() {
    Word classUnderTest = new Word("cane", PartOfSpeechType.N, LexicalType.ANIMAL);
    Word classUnderTestClone = new Word("cane", PartOfSpeechType.N, LexicalType.ANIMAL);
    assertEquals(classUnderTest, classUnderTestClone);
  }
}
