package com.github.bot.curiosone.core.nlp;

import java.util.Objects;

/**
 * Handles a grammatical Meaning.
 * Provides methods to create, change and retrieve information of the Meaning.
 * @see  <a href="https://goo.gl/SmeEDX">The Comparable Interface</a>
 */
public class Meaning implements Comparable<Meaning> {

  /**
   * The Part of the Speech type of this Meaning.
   */
  POS pos;

  /**
   * The Lexical Type of this Meaning.
   */
  LEX lex;

  /**
   * The frequency of this Meaning.
   */
  int freq;

  /**
   * Constructs this Meaning instance with a given POS, LEX and a default frequency.
   * @param  pos
   *         the part of speech type for this new Meaning
   * @param  lex
   *         the lexical category for this new Meaning
   */
  public Meaning(POS pos, LEX lex) {
    this.pos = pos;
    this.lex = lex;
    this.freq = 0;
  }

  /**
   * Gets the Part of Speech type for this Meaning.
   * @return  the Part of Speech type for this Meaning
   */
  public POS getPOS() {
    return pos;
  }

  /**
   * Gets the Lexical category for this Meaning.
   * @return  the Lexical category for this Meaning
   */
  public LEX getLEX() {
    return lex;
  }

  /**
   * Gets the frequency for this Meaning.
   * @return  frequency for this Meaning
   */
  public int getFrequency() {
    return freq;
  }

  /**
   * Sets the frequency for this Meaning.
   * @param frequency
   *        the frequency value to be set. Must be a positive integer
   * @throws IllegalArgumentException if negative frequency is passed
   */
  public void setFrequency(int frequency) {
    if (frequency < 0) {
      throw new IllegalArgumentException("Frequency must be positive");
    }
    freq = frequency;
  }

  /**
   * @return  a String representation of this Meaning in the form [pos, lex, frequency].
   */
  @Override
  public String toString() {
    return "[" + pos + ", " + lex + ", " + freq + "]";
  }

  /**
   * Checks whether this Meaning equals to the specified object.
   * @param  other
   *         the other Meaning to be compared against
   * @return  {@code true} if this Meaning equals the other Meaning;
   *          {@code false} otherwise
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
   * Calculates the HashCode for this Meaning.
   * The HashCode is based on the POS and LEX values.
   * @return the HashCode of this Meaning
   */
  @Override
  public int hashCode() {
    return Objects.hash(pos, lex);
  }

  /**
   * Compares this Meaning to the other Meaning.
   * @param  other
   *         the other Meaning to be compared against
   * @return a negative value if this is ordered before other;
   *         a positive value if this is ordered after other;
   *         zero if this and other are ordered together.
   */
  @Override
  public int compareTo(Meaning other) {
    return this.freq - other.freq;
  }
}
