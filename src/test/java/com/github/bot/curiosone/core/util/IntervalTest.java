package com.github.bot.curiosone.core.util;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

  @Test
  public void testInstantiation() {
    Interval interval = new Interval(0, 42);
    assertTrue(interval instanceof Interval);
    assertTrue(interval instanceof Comparable);
  }

  @Test
  public void testIllegalArgumentExceptionInstantiation() {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> new Interval(42, 24))
      .withMessageContaining("Illegal interval");
  }

  @Test
  public void testMin() {
    assertEquals(0, new Interval(0, 42).min());
  }

  @Test
  public void testMax() {
    assertEquals(42, new Interval(0, 42).max());
  }

  @Test
  public void testIntersects() {
    assertTrue(new Interval(0,42).intersects(new Interval(10,24)));
    assertTrue(new Interval(0,42).intersects(new Interval(0,42)));
    assertFalse(new Interval(0,42).intersects(new Interval(43,100)));
  }

  @Test
  public void testContains() {
    assertTrue(new Interval(0,42).contains(24));
    assertFalse(new Interval(0,42).contains(99));
  }

  @Test
  public void testLength() {
    assertEquals(32, new Interval(10, 42).length(), 0);
  }

  @Test
  public void testToString() {
    assertEquals("[0, 42]", new Interval(0, 42).toString());
  }

  @Test
  public void testEquals() {
    Interval i = new Interval(0, 42);
    Interval ii = new Interval(0, 42);
    assertTrue(i.equals(i));
    assertTrue(i.equals(ii));
    assertFalse(i.equals(null));
    assertFalse(i.equals(new StringBuffer()));
    ii = new Interval(1, 41);
    assertFalse(i.equals(ii));
  }

  @Test
  public void testHashCode() {
    Interval i = new Interval(0, 42);
    Interval ii = new Interval(0, 42);
    assertEquals(i.hashCode(), ii.hashCode());
    ii = new Interval(1, 41);
    assertNotEquals(i.hashCode(), ii.hashCode());
  }

  @Test
  public void testCompareTo() {
    assertTrue(new Interval(0, 42).compareTo(new Interval(1, 41)) < 0);
    assertTrue(new Interval(1, 41).compareTo(new Interval(0, 42)) > 0);
    assertTrue(new Interval(0, 42).compareTo(new Interval(0, 42)) == 0);
  }
}
