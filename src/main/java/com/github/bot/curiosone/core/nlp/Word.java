package com.github.bot.curiosone.core.nlp;

/**
 * A simple Word.
 */
public class Word {
  /** The word to be represented. */
  String text;

  /**
   * Class constructor.
   *
   * @param text The string rappresentation of this word
   */
  public Word(String text) {
    this.text = text;
  }

  /**
   * Gets the string rappresentation of this word.
   *
   * @return the string rappresenting this word
   */
  public String get() {
    return text;
  }

  /**
   * Returns a string representation of this word.
   *
   * @return a string representation of this word in the form [word, meanings]
   */
  @Override
  public String toString() {
    return text;
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
    if (other == this) return true;
    if (other == null) return false;
    if (other.getClass() != this.getClass()) return false;
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
