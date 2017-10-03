package com.github.bot.curiosone.core.nlp;

import java.util.Objects;

/**
 * Handles a grammatical meaning.
 * Provides methods to create, change and retrieve information of the Meaning.
 */
public class Meaning implements Comparable<Meaning> {

  /**
   * The part of speech type of the meaning.
   */
  POS pos;

  /**
   * The lexical category of the meaning.
   */
  LEX lex;

  /**
   * The frequency of the meaning.
   */
  int freq;

  /**
   * Constructs this Meaning instance.
   * @param pos the part of speech type for this new meaning
   * @param lex the lexical category for this new meaning
   */
  public Meaning(POS pos, LEX lex) {
    this.pos = pos;
    this.lex = lex;
    this.freq = 0;
  }

  /**
   * Gets the part of speech type for this meaning.
   */
  public POS getPOS() {
    return pos;
  }

  /**
   * Gets the lexical category for this meaning.
   */
  public LEX getLEX() {
    return lex;
  }

  /**
   * Gets frequency for this meaning.
   */
  public int getFrequency() {
    return freq;
  }

  /**
   * Sets the frequency for this meaning.
   * @param frequency the frequency value to be set. Must be a positive integer
   * @throws IllegalArgumentException if negative frequency is passed
   */
  public void setFrequency(int frequency) {
    if (frequency < 0) {
      throw new IllegalArgumentException("Frequency must be positive");
    }
    freq = frequency;
  }

  /**
   * Returns a string representation of this meaning in the form [pos, lex, frequency].
   */
  @Override
  public String toString() {
    return "[" + pos + ", " + lex + ", " + freq + "]";
  }

  /**
   * Checks whether this Meaning equals to the specified object.
   * @param  other the other Meaning to be compared against
   * @return {@code true} if this Meaning equals the other Meaning;
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
    Meaning that = (Meaning) other;
    return this.pos == that.pos && this.lex == that.lex;
  }

  /**
   * Returns the HashCode for this Meaning.
   * The HashCode is based on the POS and LEX values.
   */
  @Override
  public int hashCode() {
    return Objects.hash(pos, lex);
  }

  /**
   * Compares this Meaning to the other meaning.
   * @param other the other Meaning to be compared against
   * @return a negative value if this is ordered before other;
   *         a positive value if this is ordered after other;
   *         zero if this and other are ordered together.
   */
  @Override
  public int compareTo(Meaning other) {
    return this.freq - other.freq;
  }
}
