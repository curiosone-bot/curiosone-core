package com.github.bot.curiosone.core.nlp;

import com.github.bot.curiosone.core.nlp.raw.RawDict;
import com.github.bot.curiosone.core.nlp.raw.RawToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles a Token.
 * A Token is a list of words with a grammatical meaning.
 * Provides method to create and manage a Token.
 */
public class Token {

  /**
   * Represents the text of this token.
   */
  String text;

  /**
   * Stores the base form of the word.
   */
  String lemma;

  /**
   * Stores all the possible meanings for this token.
   */
  Set<Meaning> means;

  /**
   * Whether this token is known or not.
   */
  boolean known;

  /**
   * Constructs a Token starting from a text.
   * @param text the original text to start from
   */
  private Token(String text) {
    this.text = text;
    RawToken rt = RawDict.getInstance().getRawToken(text);
    known = rt.isKnown();
    means = new HashSet<>();
    lemma = rt.getLemma();
    rt.getWords().forEach(rw -> {
      Meaning meaning = new Meaning(rw.getPos(), rw.getLexType());
      meaning.setFrequency(rw.getNum());
      means.add(meaning);
    });
  }

  /**
   * Returns {@code true} if this Token has at leas a meaning, {@code false} otherwise.
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * Returns the concatenation of the words of this token.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the normalized concatenation of the words of this token.
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * Returns a Set containing all the meanings of this Token.
   */
  public Set<Meaning> getMeanings() {
    return means;
  }

  /**
   * Returns a string representation of this tokenin the form [text, word, meanings].
   */
  @Override
  public String toString() {
    return "[" + text + ", " + means + "]";
  }

  /**
   * Compares this token to the specified object.
   * @param  other the other Token to be compared against
   * @return {@code true} if this token equals the other Token;
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
    Token that = (Token) other;
    return this.text.equals(that.text);
  }

  /**
   * Returns the HashCode for this Token.
   * The HashCode is based on the HashCode of the original text.
   */
  @Override
  public int hashCode() {
    return text.hashCode();
  }

  /**
   * Splits a string in a list of Tokens, checking groups of words.
   * @param str the String to be tokenized
   * @return A List of tokens for the given the String
   */
  public static List<Token> tokenize(String str) {
    List<Token> tokens = new ArrayList<>();
    str = str.toLowerCase();
    str = LangUtils.removeDuplicatedSpaces(str);
    str = LangUtils.expandVerbs(str);
    str = LangUtils.removeNonAlphaNumeric(str);
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
        continue;
      }
      if (len == 1) {
        // If we don't know this token we treat it as a Noun.
        token.means = new HashSet<>();
        token.means.add(new Meaning(POS.N, LEX.OBJECT));
        tokens.add(0, token);
        pos -= len;
        len = 4;
      } else {
        len--;
      }
    }
    return tokens;
  }
}
