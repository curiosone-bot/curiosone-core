package com.github.bot.curiosone.core.util;

import java.util.Objects;

/**
 *  The {@code Interval} class represents a one-dimensional interval.
 *  The interval is <em>closed</em>—it contains both endpoints.
 *  Intervals are immutable: their values cannot be changed after they are created.
 */
public class Interval implements Comparable<Interval> {

  /**
   * Stores the start of this Interval.
   */
  private final int min;

  /**
   * Stores the end of this Interval.
   */
  private final int max;

  /**
   * Constructs an initialized closed interval [min, max].
   * @param  min the smaller endpoint
   * @param  max the larger endpoint
   * @throws IllegalArgumentException if the min endpoint is greater than the max endpoint
   */
  public Interval(int min, int max) {
    if (min > max) {
      throw new IllegalArgumentException("Illegal interval");
    }
    this.min = min;
    this.max = max;
  }

  /**
   * Returns the min endpoint of this Interval.
   */
  public int min() {
    return min;
  }

  /**
   * Returns the max endpoint of this Interval.
   */
  public int max() {
    return max;
  }

  /**
   * Checks Whether this Interval intersects the given Interval.
   * @param that the other Interval
   * @return {@code true} if this Interval intersects the provided Interval;
   *         {@code false} otherwise
   */
  public boolean intersects(Interval that) {
    return !(this.max < that.min || that.max < this.min);
  }

  /**
   * Checks whether this Interval contains the specified value.
   * @param x the value to be checked
   * @return {@code true} if this interval contains the value {@code x};
   *         {@code false} otherwise
   */
  public boolean contains(int x) {
    return (min <= x) && (x <= max);
  }

  /**
   * Returns the length of this interval.
   */
  public double length() {
    return max - min;
  }

  /**
   * Returns a string representation of this interval in the form [min, max].
   */
  @Override
  public String toString() {
    return "[" + min + ", " + max + "]";
  }

  /**
   * Checks whether this Interval is equal to the given Object.
   * @param  other the other Interval to be compared against
   * @return {@code true} if this Interval equals the other Interval;
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Interval that = (Interval) other;
    return this.min == that.min && this.max == that.max;
  }

  /**
   * Returns the HashCode for this Interval.
   * The HashCode depends on both the endpoints of this Interval.
   */
  @Override
  public int hashCode() {
    return Objects.hash(min, max);
  }

  /**
   * Compares this Interval with another Interval.
   * @param other the other Interval to be compared against
   * @return a negative value if this is ordered before other;
   *         a positive value if this is ordered after other;
   *         zero if this and other are ordered together.
   */
  @Override
  public int compareTo(Interval other) {
    return this.min != other.min ? this.min - other.min : this.max - other.max;
  }
}
