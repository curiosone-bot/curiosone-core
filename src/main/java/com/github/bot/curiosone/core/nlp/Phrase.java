package com.github.bot.curiosone.core.nlp;

import static com.github.bot.curiosone.core.util.TextConstants.OPEN_SQ_BRACKET;
import static com.github.bot.curiosone.core.util.TextConstants.CLOSE_SQ_BRACKET;
import static com.github.bot.curiosone.core.util.TextConstants.COMMA_SEP;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a phrase.
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
   */
  private List<Token> tokens = new ArrayList<>();

  /**
   * Constructs a Phrase, using the String provided by the user.
   * @param str The phrase to be created
   */
  public Phrase(String str) {
    text = str;
    tokens = Token.tokenize(str);
    question = str.charAt(str.length() - 1) == '?';
  }

  /**
   * Returns {@code true} if this original string contains a question mark at the end.
   * {@code false} otherwise
   */
  public boolean isQuestion() {
    return question;
  }

  /**
   * Gets the text content of this Phrase.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the list of tokens extracted from this Phrase.
   */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /**
   * Returns a string representation of this phrase in the form [text, tokens].
   */
  @Override
  public String toString() {
    return OPEN_SQ_BRACKET + text + COMMA_SEP + tokens + CLOSE_SQ_BRACKET;
  }

  /**
   * Compares this phrase to the specified object.
   * @param other the other phrase
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
   * Returns the HashCode for this Phrase.
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, tokens);
  }

  /**
   * Extracts phrases from an input text, splitting it by punctuation.
   * @param input the text to be splitted in phrases
   * @return a List containing all the phrases of the given text.
   */
  public static List<Phrase> extract(String input) {
    List<String> splitted = LangUtils.splitByPuntaction(input);
    List<Phrase> phrases = new ArrayList<>(splitted.size());
    splitted.forEach(s -> phrases.add(new Phrase(s)));
    return phrases;
  }
}
