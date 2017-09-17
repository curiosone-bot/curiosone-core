package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

public class SpellingTest {
  @Test
  public void testGetInstance() {
    Spelling classUnderTest = Spelling.getInstance();
    assertTrue(classUnderTest instanceof Spelling);
  }

  @Test
  public void testCorrect() {
    Spelling classUnderTest = Spelling.getInstance();
    assertEquals("hello", classUnderTest.correct("hello"));
    assertEquals("hello", classUnderTest.correct("helllo"));
    assertEquals("can", classUnderTest.correct("cann"));
  }
}
