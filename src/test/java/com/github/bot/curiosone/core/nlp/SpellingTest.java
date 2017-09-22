package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class SpellingTest {
  @Test
  public void testGetInstance() {
    Spelling s = Spelling.getInstance();
    assertTrue(s instanceof Spelling);
  }

  @Test
  public void testCorrect() {
    Spelling s = Spelling.getInstance();
    assertEquals("hello", s.correct("hello"));
    assertEquals("hello", s.correct("helllo"));
    assertEquals("can", s.correct("cann"));
    assertEquals("helping", s.correct("heelping"));
    assertEquals("grow", s.correct("trow"));
    assertEquals("engineer", s.correct("enginer"));
    assertEquals("february", s.correct("febraury"));
    assertEquals("CORRECTME!", s.correct("CORRECTME!"));
    assertEquals("42", s.correct("42"));
    assertEquals("accommodate", s.correct("accomodate"));
    assertEquals("forty", s.correct("fourty"));
  }
}
