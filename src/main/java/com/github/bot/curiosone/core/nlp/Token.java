package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.nlp.raw.RawDict;
import com.github.bot.curiosone.core.nlp.raw.RawToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Token is a list of words with a grammatical meaning.
 */
public class Token {
  /** The text rappresentation of this token. */
  String text;

  /** List of words that forms token. */
  List<Word> words;

  /** Meanings of the token. */
  Set<Meaning> means;

  /** Whether this token is known or not. */
  boolean known;

  /**
   * Constructs a Token starting from a text.
   * @param text the original text to start from
   */
  public Token(String text) {
    this.text = text;
    RawToken rt = RawDict.getInstance().getRawToken(text);
    known = rt.isKnown();
    means = new HashSet<>();
    rt.getWords().forEach(rw -> {
      Meaning meaning = new Meaning(rw.getPos(), rw.getLexType());
      meaning.setFrequency(rw.getNum());
      means.add(meaning);
    });
  }

  /**
   * Checks if this token as at least a meaning.
   *
   * @return {@code true} if this interval equals the other interval;
   *         {@code false} otherwise
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * Returns the concatenation of the words of this token.
   *
   * @return the concatenation of the words of this token.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the list of the words of this token.
   *
   * @return the list of the words of this token.
   */
  public List<Word> getWords() {
    return words;
  }

  /**
   * Returns a string representation of this token.
   *
   * @return a string representation of this token in the form [text, word, meanings]
   */
  @Override
  public String toString() {
    return "[" + text + ", " + words + ", " + means + "]";
  }

  /**
   * Compares this token to the specified object.
   *
   * @param  other the other token
   * @return {@code true} if this token equals the other token;
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (other == null) return false;
    if (other.getClass() != this.getClass()) return false;
    Token that = (Token) other;
    return this.text.equals(that.text);
  }

  /**
   * Returns an integer hash code for this token.
   *
   * @return an integer hash code for this token
   */
  @Override
  public int hashCode() {
    return text.hashCode();
  }

  /**
   * Splits a string in a list of tokens checking groups of words
   *
   * @param str the string to be tokenized
   * @return the list of tokens of the string
   */
  public static List<Token> tokenize(String str) {
    List<Token> tokens = new ArrayList<>();
    String[] splitted = str.split(" ");
    int pos = splitted.length;
    int len = 4;
    while (pos > 0) {
      int start = Math.max(0, pos - len);
      int end = pos;

      StringBuffer buff = new StringBuffer();
      for (int i = start; i < end; i++) {
        buff.append(splitted[i]);
        if (i + 1 < end) {
          buff.append(' ');
        }
      }
      String word = buff.toString();

      Token token = new Token(word);
      if (!token.isKnown()) {
        word = Spelling.getInstance().correct(word);
        token = new Token(word);
      }
      if (token.isKnown()) {
        tokens.add(0, token);
        pos -= len;
        len = 4;
      }
      if (len == 1) {
        tokens.add(0, token);
        len = 4;
      } else {
        len--;
      }
    }
    return tokens;
  }
}
