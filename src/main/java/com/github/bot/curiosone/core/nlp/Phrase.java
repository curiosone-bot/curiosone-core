package com.github.bot.curiosone.core.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a phrase.
 */
public class Phrase {
  /** The string representation of the phrase. */
  private String text;

  /** The list of tokens extracted from the phrase. */
  private List<Token> tokens = new ArrayList<>();

  /**
   * Constructs a phrase, using the string provided by the user.
   *
   * @param str The phrase to be created
   */
  public Phrase(String str) {
    text = str;
    tokens = Token.tokenize(str);
  }

  /**
   * Gets the string representation of the phrase.
   *
   * @return the string representation of the phrase
   */
  public String getText() {
    return text;
  }

  /**
   * The list of tokens extracted from the phrase.
   *
   * @return the list of tokens extracted from the phrase
   */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /**
   * Returns a string representation of this phrase.
   *
   * @return a string representation of this phrase in the form [text, tokens]
   */
  @Override
  public String toString() {
    return "[" + text + ", " + tokens + "]";
  }

  /**
   * Compares this phrase to the specified object.
   *
   * @param  other the other phrase
   * @return {@code true} if this phrase equals the other phrase;
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
    Phrase that = (Phrase) other;
    return this.text.equals(that.text) && this.tokens.equals(that.tokens);
  }

  /**
   * Returns an integer hash code for this phrase.
   *
   * @return an integer hash code for this phrase
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, tokens);
  }

  /**
   * Extracts phrases from an input text, splitting it by punctuation.
   *
   * @param input the text to be splitted in phrases
   * @return the phrases of the given text
   */
  public static List<Phrase> extract(String input) {
    List<String> splitted = LangUtils.splitByPuntaction(input);
    for (int i = 0; i < splitted.size(); i++) {
      splitted.set(i, LangUtils.removeDuplicatedSpaces(splitted.get(i)));
      splitted.set(i, LangUtils.expandVerbs(splitted.get(i)));
    }

    List<Phrase> phrases = new ArrayList<>(splitted.size());
    splitted.forEach(s -> phrases.add(new Phrase(s)));
    return phrases;
  }
}
