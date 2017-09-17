package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class WordTest {
  @Test
  public void testInstantiation() {
    Word classUnderTest = new Word("cane");
    assertTrue(classUnderTest instanceof Word);

    assertEquals("cane", classUnderTest.get());
  }

  @Test
  public void testEquals() {
    Word classUnderTest = new Word("cane");
    Word classUnderTestClone = new Word("cane");
    assertEquals(classUnderTest, classUnderTestClone);
  }
}
