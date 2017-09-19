package com.github.bot.curiosone.core.nlp;

import java.util.Objects;
import com.github.bot.curiosone.core.nlp.cyk.POS;

/**
 * A grammatical Meaning of a group of words.
 */
public class Meaning implements Comparable<Meaning> {
  /** The part of speech type of the meaning. */
  POS pos;

  /** The lexical category of the meaning. */
  LEX lex;

  /** The frequency of the meaning. */
  int freq;

  /**
   * Constructor of a Meaning.
   *
   * @param pos the part of speech type of the new meaning
   * @param lex the lexical category of the new meaning
   */
  public Meaning(POS pos, LEX lex) {
    this.pos = pos;
    this.lex = lex;
    this.freq = 0;
  }

  /**
   * Gets the part of speech type of this meaning.
   *
   * @return the part of speech type of this meaning
   */
  public POS getPOS() {
    return pos;
  }

  /**
   * Gets the lexical category of this meaning.
   *
   * @return the lexical category of this meaning
   */
  public LEX getLEX() {
    return lex;
  }

  /**
   * Gets how much this meaning is frequent.
   *
   * @return a positive frequency of this meaning.
   */
  public int getFrequency() {
    return freq;
  }

  /**
   * Sets how much this meaning is frequent.
   *
   * @param frequency a positive frequency to set
   * @throws IllegalArgumentException if negative frequency is passed
   */
  public void setFrequency(int frequency) {
    if (frequency < 0) {
      throw new IllegalArgumentException("Frequency must be positive");
    }
    freq = frequency;
  }

  /**
   * Returns a string representation of this meaning.
   *
   * @return a string representation of this meaning in the form [pos, lex, frequency]
   */
  @Override
  public String toString() {
    return "[" + pos + ", " + lex + ", " + freq + "]";
  }

  /**
   * Compares this meaning to the specified object.
   *
   * @param  other the other meaning
   * @return {@code true} if this meaning equals the other meaning;
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
   * Returns an integer hash code for this meaning.
   *
   * @return an integer hash code for this meaning
   */
  @Override
  public int hashCode() {
    return Objects.hash(pos, lex);
  }

  /**
   * Compares this meaning to the other meaning.
   *
   * @param other the other meaning
   * @return a negative value if this is ordered before other;
   *         a positive value if this is ordered after other;
   *         zero if this and other are ordered together.
   */
  @Override
  public int compareTo(Meaning other) {
    return this.freq - other.freq;
  }
}
