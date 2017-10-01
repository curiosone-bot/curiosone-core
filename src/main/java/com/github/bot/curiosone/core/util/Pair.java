package com.github.bot.curiosone.core.util;

import java.util.Objects;

/**
 * Provides utility methods to easily create and manage a tuple of Objects.
 */
public class Pair<F, S> {
  /**
   * Stores the first element of the pair.
   */
  private final F first;

  /**
   * Stores the second element of the pair.
   */
  private final S second;

  /**
   * Constructs this Pair.
   * @param first the first object in the pair
   * @param second the second object in the pair
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first element of the pair.
   */
  public F getFirst() {
    return first;
  }

  /**
   * Gets the second element of the pair.
   */
  public S getSecond() {
    return second;
  }

  /**
   * Checks the two objects for equality by delegating to their respective
   * {@link Object#equals(Object)} methods.
   * @param other the {@link Pair} to be compared against
   * @return {@code true} if the underlying objects of the Pair are both equal;
             {@code false} otherwise.
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
   * Returns the HashCode for this Pair.
   * The HashCode is based on the HashCode of each element.
   */
  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  /**
   * Returns a String representation of this pair in the form (first, second).
   */
  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  /**
   * Utility method to create a new immutable Pair.
   * @param a the first Object of the new immutable Pair
   * @param b the second Object of the new immutable Pair
   * @return the new immutable Pair
   */
  public static <A, B> Pair<A, B> create(A a, B b) {
    return new Pair<A, B>(a, b);
  }
}
