package com.github.bot.curiosone.core.nlp;

import java.util.List;
import java.util.Set;

/**
 * A Word is like a Token but with a restricted set of meanings.
 */
public class Word {
  /** The text rappresentation of this word. */
  String text;

  /** Base form of the world. */
  String lemma;

  /** Meanings of the word. */
  Set<Meaning> means;

  /**
   * Constructs a Word starting from a text.
   * @param text the original text to start from
   */
  public Word(String text, String lemma, Set<Meaning> means) {
    this.text = text;
    this.lemma = lemma;
    this.means = means;
  }

  /**
   * Returns the word.
   *
   * @return the word.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the lemma.
   *
   * @return the lemma.
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * Returns the set of the meanings of this word.
   *
   * @return the set of the meanings of this word.
   */
  public Set<Meaning> getMeanings() {
    return means;
  }

  /**
   * Returns a string representation of this word.
   *
   * @return a string representation of this word in the form [text, word, meanings]
   */
  @Override
  public String toString() {
    return "[" + text + " (" + lemma + ")"+ ", " + means + "]";
  }

  /**
   * Compares this word to the specified object.
   *
   * @param  other the other word
   * @return {@code true} if this word equals the other word;
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
    Word that = (Word) other;
    return this.text.equals(that.text);
  }

  /**
   * Returns an integer hash code for this word.
   *
   * @return an integer hash code for this word
   */
  @Override
  public int hashCode() {
    return text.hashCode();
  }
}
