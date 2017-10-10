package com.github.bot.curiosone.core.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Phrase.
 * A Phrase is a text that can be splitted in different Tokens.
 * A Phrase can be interpreted as a question or an answer.
 * Provides methods to create the Phrase and get its information.
 * @see  com.github.bot.curiosone.core.nlp.Token The Token Class
 */
public class Phrase {

  /**
   * Stores the text content of this Phrase.
   */
  private String text;

  /**
   * Wether the text ends with a question mark.
   */
  private boolean question;

  /**
   * Lists the tokens extracted from this Phrase.
   * @see  com.github.bot.curiosone.core.nlp.Token The Token Class
   */
  private List<Token> tokens = new ArrayList<>();

  /**
   * Constructs a Phrase, using the String provided by the user.
   * @param  str
   *         The phrase to be created
   */
  public Phrase(String str) {
    text = str;
    tokens = Token.tokenize(str);
    question = str.charAt(str.length() - 1) == '?';
  }

  /**
   * Returns whether this Phrase is a question or not.
   * @return  {@code true} if the content is a question, {@code false} otherwise
   */
  public boolean isQuestion() {
    return question;
  }

  /**
   * Gets the text of this Phrase.
   * @return  the text content of this Phrase
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the Tokens of this Phrase.
   * @return  the list of tokens extracted from this Phrase
   */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /**
   * Returns a String representation of this Phrase.
   * @return  a String representation of this Phrase in the form [text, tokens]
   */
  @Override
  public String toString() {
    return "[" + text + ", " + tokens + "]";
  }

  /**
   * Checks whether this Phrase equals to the given Object.
   * @param  other
   *         the other Phrase to be compared against
   * @return  {@code true} if this Phrase equals the other Phrase;
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
    Phrase that = (Phrase) other;
    return this.text.equals(that.text) && this.tokens.equals(that.tokens);
  }

  /**
   * Calculates the HashCode for this Phrase.
   * The HashCode depends on the HashCode of the content and its tokens.
   * @return  the HashCode of this Phrase
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, tokens);
  }

  /**
   * Extracts phrases from an input text, splitting it by punctuation.
   * @param  input
   *         the text to be splitted in phrases
   * @return  a List containing all the phrases of the given text.
   */
  public static List<Phrase> extract(String input) {
    List<String> splitted = LangUtils.splitByPuntaction(input);
    List<Phrase> phrases = new ArrayList<>(splitted.size());
    splitted.forEach(s -> phrases.add(new Phrase(s)));
    return phrases;
  }
}
