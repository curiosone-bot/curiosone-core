package com.github.bot.curiosone.core.nlp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
   *
   * @param text [description]
   * @param lemma [description]
   * @param means [description]
   */
  public Word(String text, String lemma, Set<Meaning> means) {
    this.text = text;
    this.lemma = lemma;
    this.means = means;
  }

  /**
   * Constructs a Word starting from a text.
   *
   * @param text [description]
   * @param lemma [description]
   * @param mean [description]
   */
  public Word(String text, String lemma, Meaning mean) {
    this.text = text;
    this.lemma = lemma;
    this.means = new HashSet<>();
    means.add(mean);
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
   * itMeans description.
   * @param  mean [description]
   * @return [description]
   */
  public boolean itMeans(Meaning mean) {
    return means.contains(mean);
  }

  /**
   * itMeans description.
   * @param  pos [description]
   * @return [description]
   */
  public boolean itMeans(POS pos) {
    return means.stream()
        .map(Meaning::getPOS)
        .collect(Collectors.toList())
        .contains(pos);
  }

  /**
   * itMeans description.
   * @param  lex [description]
   * @return [description]
   */
  public boolean itMeans(LEX lex) {
    return means.stream()
        .map(Meaning::getLEX)
        .collect(Collectors.toList())
        .contains(lex);
  }

  /**
   * Returns a string representation of this word.
   *
   * @return a string representation of this word in the form [text, word, meanings]
   */
  @Override
  public String toString() {
    return "[" + text + " (" + lemma + ")" + ", " + means + "]";
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
