package com.github.bot.curiosone.core.util;

import java.util.Objects;

/**
 *  The {@code Interval} class represents a one-dimensional interval.
 *  The interval is <em>closed</em>—it contains both endpoints.
 *  Intervals are immutable: their values cannot be changed after they are created.
 */
public class Interval implements Comparable<Interval> {
  /** The start of the interval. */
  private final int min;

  /** The end of the interval. */
  private final int max;

  /**
   * Initializes a closed interval [min, max].
   *
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
   * Returns the min endpoint of this interval.
   *
   * @return the min endpoint of this interval
   */
  public int min() {
    return min;
  }

  /**
   * Returns the max endpoint of this interval.
   *
   * @return the max endpoint of this interval
   */
  public int max() {
    return max;
  }

  /**
   * Returns true if this interval intersects the specified interval.
   *
   * @param  that the other interval
   * @return {@code true} if this interval intersects the argument interval;
   *         {@code false} otherwise
   */
  public boolean intersects(Interval that) {
    if (this.max < that.min || that.max < this.min) {
      return false;
    }
    return true;
  }

  /**
   * Returns true if this interval contains the specified value.
   *
   * @param x the value
   * @return {@code true} if this interval contains the value {@code x};
   *         {@code false} otherwise
   */
  public boolean contains(int x) {
    return (min <= x) && (x <= max);
  }

  /**
   * Returns the length of this interval.
   *
   * @return the length of this interval (max - min)
   */
  public double length() {
    return max - min;
  }

  /**
   * Returns a string representation of this interval.
   *
   * @return a string representation of this interval in the form [min, max]
   */
  @Override
  public String toString() {
    return "[" + min + ", " + max + "]";
  }

  /**
   * Compares this interval to the specified object.
   *
   * @param  other the other interval
   * @return {@code true} if this interval equals the other interval;
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
   * Returns an integer hash code for this interval.
   *
   * @return an integer hash code for this interval
   */
  @Override
  public int hashCode() {
    return Objects.hash(min, max);
  }

  /**
   * Compares this interval to the other interval.
   *
   * @param other the other interval
   * @return a negative value if this is ordered before other;
   *         a positive value if this is ordered after other;
   *         zero if this and other are ordered together.
   */
  @Override
  public int compareTo(Interval other) {
    return this.min != other.min ? this.min - other.min : this.max - other.max;
  }
}
