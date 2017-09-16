package com.github.bot.curiosone.core.nlp.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a phrase.
 * A phrase has a type (question, answer...) and can be interpreted as simple
 * String or as a token list.
 */
public class Phrase {

  /**
   * Original string representation of the phrase, provided in input by the user.
   */
  private String text;

  /**
   * Tokens extracted from the original phrase.
   * @see Token
   */
  private List<Token> tokens = new ArrayList<>();

  /**
   * Sentence typology.
   * @see PhraseType
   */
  private PhraseType type;

  /**
   * Constructs a phrase, using the sentence provided by the user.
   * @param str The phrase to be created.
   */
  public Phrase(String str) {
    text = str;
    type = PhraseType.get(str);
    tokens = Token.tokenize(str);
  }

  /** Returns the original phrase. */
  public String getText() {
    return text;
  }

  /** Returns a list of token for this Phrase. */
  public List<Token> getTokens() {
    return new ArrayList<Token>(tokens);
  }

  /** Returns the phrase type for this Phrase. */
  public PhraseType getType() {
    return type;
  }

  /**
   * Extracts phrases from an input text, splitting it by punctuation.
   * @param input the text to be splitted in phrases.
   * @return the phrases of the given text.
   */
  public static List<Phrase> extract(String input) {
    List<String> phr = LangUtils.splitByPuntaction(input);
    for (int i = 0; i < phr.size(); i++) {
      phr.set(i, LangUtils.removeDuplicatedSpaces(phr.get(i)));
      phr.set(i, LangUtils.expandVerbs(phr.get(i)));
    }

    List<Phrase> phrases = new ArrayList<>(phr.size());
    for (int i = 0; i < phr.size(); i++) {
      Phrase p = new Phrase(phr.get(i));
      phrases.add(p);
    }
    return phrases;
  }

  /**
   * Compares this Phrase to the given object. The comparison is case-sensitive.
   * @param object The object to compare this Phrase against.
   * @return <code>true</code> if the given object represents the same Phrase of
            this instance;
            <code>false</code> otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Phrase) || object == null) {
      return false;
    }
    Phrase t = (Phrase)object;
    return t.text.equals(this.text);
  }

  /** Returns a String representation of this Phrase. */
  @Override
  public String toString() {
    return "{text:" + text + ", type:" + type + ", tokens:" + tokens + "}";
  }
}
