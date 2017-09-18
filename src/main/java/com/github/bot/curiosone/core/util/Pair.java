package com.github.bot.curiosone.core.util;

import java.util.Objects;

/**
 * Container to ease passing around a tuple of two objects.
 */
public class Pair<F, S> {
  /** The first element of the pair. */
  private final F first;

  /** The second element of the pair. */
  private final S second;

  /**
   * Constructor for a Pair.
   *
   * @param first the first object in the pair
   * @param second the second object in the pair
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first element of the pair.
   *
   * @return the first element of the pair.
   */
  public F getFirst() {
    return first;
  }

  /**
   * Gets the second element of the pair.
   *
   * @return the second element of the pair.
   */
  public S getSecond() {
    return second;
  }

  /**
   * Checks the two objects for equality by delegating to their respective
   * {@link Object#equals(Object)} methods.
   *
   * @param other the {@link Pair} to which this one is to be checked for equality
   * @return true if the underlying objects of the Pair are both considered
   *         equal
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Pair<?, ?> that = (Pair<?, ?>) other;
    return Objects.equals(that.first, this.first) && Objects.equals(that.second, this.second);
  }

  /**
   * Compute a hash code using the hash codes of the underlying objects.
   *
   * @return a hashcode of the pair
   */
  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  /**
   * Returns a string representation of this pair.
   *
   * @return a string representation of this interval in the form (first, second)
   */
  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  /**
   * Convenience method for creating an appropriately typed pair.
   *
   * @param a the first object in the pair
   * @param b the second object in the pair
   * @return a pair that is templatized with the types of a and b
   */
  public static <A, B> Pair<A, B> create(A a, B b) {
    return new Pair<A, B>(a, b);
  }
}
