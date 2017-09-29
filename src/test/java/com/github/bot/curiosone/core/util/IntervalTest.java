package com.github.bot.curiosone.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

  @Test
  public void testInstantiation() {
    Interval interval = new Interval(0, 42);
    assertThat(interval instanceof Interval).isTrue();
    assertThat(interval instanceof Comparable).isTrue();
  }

  @Test
  public void testIllegalArgumentExceptionInstantiation() {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> new Interval(42, 24))
      .withMessageContaining("Illegal interval");

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> new Interval(-1, -42))
      .withMessageContaining("Illegal interval");
  }

  @Test
  public void testMin() {
    assertThat(new Interval(0, 42).min()).isZero();

    assertThat(new Interval(0, 0).min()).isZero();

    assertThat(new Interval(-42, 84).min()).isEqualTo(-42);

    assertThat(new Interval(-42, -21).min()).isEqualTo(-42);
  }

  @Test
  public void testMax() {
    assertThat(new Interval(0, 0).max()).isZero();

    assertThat(new Interval(0, 42).max()).isEqualTo(42);

    assertThat(new Interval(-4, -2).max()).isEqualTo(-2);

    assertThat(new Interval(-4, 2).max()).isEqualTo(2);
  }

  @Test
  public void testIntersects() {
    Interval i = new Interval(10,42);
    Interval ii = new Interval(24,45);
    assertThat(i.intersects(ii)).isTrue();

    ii = new Interval(0,24);
    assertThat(i.intersects(ii)).isTrue();

    ii = new Interval(10,42);
    assertThat(i.intersects(ii)).isTrue();

    i = new Interval(-42, -23);
    ii = new Interval(-42, -23);
    assertThat(i.intersects(ii)).isTrue();

    ii = new Interval(-42, -30);
    assertThat(i.intersects(ii)).isTrue();

    ii = new Interval(-33, 30);
    assertThat(i.intersects(ii)).isTrue();
  }

  @Test
  public void testDoesNotIntersect() {
    Interval i = new Interval(10,42);
    Interval ii = new Interval(45,47);
    assertThat(i.intersects(ii)).isFalse();

    ii = new Interval(0,8);
    assertThat(i.intersects(ii)).isFalse();

    i = new Interval(-20, -10);
    ii = new Interval(-80, -40);
    assertThat(i.intersects(ii)).isFalse();

    ii = new Interval(-5, -1);
    assertThat(i.intersects(ii)).isFalse();
  }

  @Test
  public void testContains() {
    Interval i = new Interval(0, 42);
    int ii = 24;
    assertThat(i.contains(ii)).isTrue();

    ii = 0;
    assertThat(i.contains(ii)).isTrue();

    ii = 42;
    assertThat(i.contains(ii)).isTrue();

    i = new Interval(-7, -2);
    ii = -5;
    assertThat(i.contains(ii)).isTrue();

    ii = -7;
    assertThat(i.contains(ii)).isTrue();

    ii = -2;
    assertThat(i.contains(ii)).isTrue();
  }

  @Test
  public void testDoesNotContain() {
    Interval i = new Interval(0, 42);
    int ii = 98;
    assertThat(i.contains(ii)).isFalse();

    ii = -98;
    assertThat(i.contains(ii)).isFalse();

    i = new Interval(-46, -42);
    assertThat(i.contains(ii)).isFalse();

    ii = -40;
    assertThat(i.contains(ii)).isFalse();
  }

  @Test
  public void testLength() {
    Interval i = new Interval(10, 42);
    assertThat(i.length()).isEqualTo(32);

    i = new Interval(10, 10);
    assertThat(i.length()).isEqualTo(0);

    i = new Interval(0, 0);
    assertThat(i.length()).isEqualTo(0);

    i = new Interval(-5, 0);
    assertThat(i.length()).isEqualTo(5);

    i = new Interval(-5, -1);
    assertThat(i.length()).isEqualTo(4);
  }

  @Test
  public void testToString() {
    Interval i = new Interval(0, 42);
    assertThat(i.toString()).isEqualTo("[0, 42]");

    i = new Interval(-30, -15);
    assertThat(i.toString()).isEqualTo("[-30, -15]");

    i = new Interval(-30, 7);
    assertThat(i.toString()).isEqualTo("[-30, 7]");
  }

  @Test
  public void testEqualsReflexive() {
    Interval i = new Interval(1, 2);
    assertThat(i).isEqualTo(i);

    i = new Interval(-1, 2);
    assertThat(i).isEqualTo(i);

    i = new Interval(-2, 1);
    assertThat(i).isEqualTo(i);

    i = new Interval(-2, -1);
    assertThat(i).isEqualTo(i);
  }

  @Test
  public void testEqualsSymmetric() {
    Interval i = new Interval(1, 2);
    Interval ii = new Interval(1, 2);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(i);

    i = new Interval(-1, 2);
    ii = new Interval(-1, 2);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(i);

    i = new Interval(-2, 1);
    ii = new Interval(-2, 1);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(i);

    i = new Interval(-2, -1);
    ii = new Interval(-2, -1);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(i);
  }

  @Test
  public void testEqualsTransitive() {
    Interval i = new Interval(1, 2);
    Interval ii = new Interval(1, 2);
    Interval iii = new Interval(1, 2);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(iii);
    assertThat(iii).isEqualTo(i);

    i = new Interval(-1, 2);
    ii = new Interval(-1, 2);
    iii = new Interval(-1, 2);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(iii);
    assertThat(iii).isEqualTo(i);

    i = new Interval(-2, 1);
    ii = new Interval(-2, 1);
    iii = new Interval(-2, 1);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(iii);
    assertThat(iii).isEqualTo(i);

    i = new Interval(-2, -1);
    ii = new Interval(-2, -1);
    iii = new Interval(-2, -1);
    assertThat(i).isEqualTo(ii);
    assertThat(ii).isEqualTo(iii);
    assertThat(iii).isEqualTo(i);
  }

  @Test
  public void testEqualsNullComparison() {
    Interval i = new Interval(77, 88);
    assertThat(i).isNotEqualTo(null);

    i = new Interval(-77, 88);
    assertThat(i).isNotEqualTo(null);

    i = new Interval(-88, 77);
    assertThat(i).isNotEqualTo(null);

    i = new Interval(-88, -77);
    assertThat(i).isNotEqualTo(null);

    i = new Interval(78, 78);
    assertThat(i).isNotEqualTo(null);
  }

  @Test
  public void testEqualsOtherObj() {
    Interval i = new Interval(77, 88);
    assertThat(i).isNotEqualTo("77, 88");

    i = new Interval(-7, 1);
    assertThat(i).isNotEqualTo(new Integer(10));
  }

  @Test
  public void testNotEqual() {
    Interval i = new Interval(0, 10);
    Interval ii = new Interval(-10, 0);
    assertThat(i).isNotEqualTo(ii);

    i = new Interval(0, 10);
    ii = new Interval(10, 10);
    assertThat(i).isNotEqualTo(ii);
  }

  @Test
  public void testHashCodeCoherent() {
    Interval i = new Interval(0, 42);
    Interval ii = new Interval(0, 42);
    assertThat(i.hashCode()).isEqualTo(ii.hashCode());
    ii = new Interval(0, 20);
    assertNotEquals(i.hashCode(), ii.hashCode());
  }

  @Test
  public void testHashCodeReflexive() {
    Interval i = new Interval(0, 42);
    assertThat(i.hashCode()).isEqualTo(i.hashCode());

    i = new Interval(-42, 0);
    assertThat(i.hashCode()).isEqualTo(i.hashCode());

    i = new Interval(-42, -5);
    assertThat(i.hashCode()).isEqualTo(i.hashCode());

    i = new Interval(-42, -4);
    assertThat(i.hashCode()).isEqualTo(i.hashCode());
  }

  @Test
  public void testHashCodeEqualsContract() {
    Interval i = new Interval(0, 42);
    Interval ii = new Interval(0, 42);
    assertThat(i.hashCode()).isEqualTo(ii.hashCode());
    assertThat(i).isEqualTo(ii);

    i = new Interval(-40, 42);
    ii = new Interval(-40, 42);
    assertThat(i.hashCode()).isEqualTo(ii.hashCode());
    assertThat(i).isEqualTo(ii);
  }

  @Test
  public void testCompareTo() {
    Interval i = new Interval(0, 42);
    Interval ii = new Interval(1, 41);
    assertThat(i.compareTo(ii)).isLessThan(0);

    ii = new Interval(0, 42);
    assertThat(i.compareTo(ii)).isZero();

    i = new Interval(1, 41);
    ii = new Interval(0, 42);
    assertThat(i.compareTo(ii)).isGreaterThan(0);
  }
}
