package com.github.bot.curiosone.core.util;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

  @Test
  public void testInstantiation() {
    assertTrue(new Pair(10, "10") instanceof Pair);
  }

  @Test
  public void testGetFirst() {
    assertEquals("First", new Pair("First", "Second").getFirst());
    assertEquals(10, new Pair(10, "Second").getFirst());
  }

  @Test
  public void testGetSecond() {
    assertEquals("Second", new Pair("First", "Second").getSecond());
    assertEquals(20, new Pair(10, 20).getSecond());
  }

  @Test
  public void testEquals() {
    Pair p = new Pair("First", "Second");
    assertTrue(p.equals(p));
    assertFalse(p.equals(null));
    assertFalse(p.equals(new Double(42.0)));
    Pair pp = new Pair("First", "Second");
    assertTrue(p.equals(pp));
  }

  @Test
  public void testHashCode() {
    assertEquals(new Pair(10, 20).hashCode(), new Pair(10, 20).hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("(Test, toString)", new Pair("Test", "toString").toString());
  }

  @Test
  public void testCreate() {
    assertEquals(
        new Pair("I want", "a new pair"), Pair.create("I want", "a new pair"));
  }
}
