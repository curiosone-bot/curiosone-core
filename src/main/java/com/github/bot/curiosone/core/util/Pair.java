package com.github.bot.curiosone.core.util;

import java.util.Objects;

/**
 * Provides utility methods to easily create and manage an immutable tuple of Objects.
 */
public class Pair<F, S> {

  /**
   * Stores the first element of this Pair.
   */
  private final F first;

  /**
   * Stores the second element of this Pair.
   */
  private final S second;

  /**
   * Constructs this Pair.
   * @param  first
   *         the first object in this Pair
   * @param  second
   *         the second object in this Pair
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first element of this Pair.
   * @return  the first element of this Pair
   */
  public F getFirst() {
    return first;
  }

  /**
   * Gets the second element of this Pair.
   * @return  the second element of this Pair
   */
  public S getSecond() {
    return second;
  }

  /**
   * Checks whether this Pair equals to the given Object or not.
   * The equality comparison is performed on the two Objects contained in the Pairs, so their
   * respective {@link Object#equals(Object)} methods are called.
   * @param  other
   *         the {@link Pair} to be compared against
   * @return  {@code true} if the Objects of the Pair are both equal;
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
   * Calculates the HashCode for this Pair.
   * The HashCode is based on the HashCode of each element.
   * @return  the HashCode of this Pair
   */
  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  /**
   * Returns a String representation of this Pair.
   * @return  a String representation of this Pair in the form (first, second)
   */
  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  /**
   * Utility method to create a new immutable Pair.
   * @param  <A>
   *         the type of the first Object in this Pair
   * @param  a
   *         the first Object of the new immutable Pair
   * @param  <B>
   *         the type of the second Object in this Pair
   * @param  b
   *         the second Object of the new immutable Pair
   * @return  the new immutable Pair
   */
  public static <A, B> Pair<A, B> create(A a, B b) {
    return new Pair<A, B>(a, b);
  }
}
