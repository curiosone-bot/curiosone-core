package com.github.bot.curiosone.core.nlp.raw;

import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.POS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent a Token.
 * A Token is a Word with some additional information.
 * Provides methods to create a RawToken and manage all of its aspects.
 */
public class RawToken {

  /**
   * Value of the original string.
   */
  private String originalValue;

  /**
   * Value of the modified string.
   */
  private String value;

  /**
   * Wheter a word is correct or not.
   */
  private boolean corrected;

  /**
   * Wheter a corrispondence in the dictionary has been found or not.
   */
  private boolean known;

  /**
   * Lists various semantic information
   * @see  Word
   * @see  RawWord
   */
  private List<RawWord> words;

  /**
   * constructs a RawToken, using an original value.
   * @param  originalValue
   *         the original token value
   */
  public RawToken(String originalValue) {
    this.originalValue = originalValue;
    this.corrected = false;
    this.value = originalValue;
    this.known = false;
    this.words = new ArrayList<RawWord>();
  }

  /**
   * Gets the original value.
   * @return  the original value
   */
  public String getOValue() {
    return originalValue;
  }

  /**
   * Sets the original value.
   * @param  originalValue
   *         the value to set the original value to.
   */
  public void setOValue(String originalValue) {
    this.originalValue = originalValue;
  }

  /**
   * Returns wheter this Token is correct or not.
   * @return  {@code true} if this Token is correct;
   *          {@code false} otherwise
   */
  public boolean isCorrected() {
    return corrected;
  }

  /**
   * Sets wheter this Token is correct or not.
   * @param corrected wheter the token is correct or not.
   */
  public void setCorrected(boolean corrected) {
    this.corrected = corrected;
  }

  /**
   * Gets the modified String value.
   * @return  the value
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns whether this RawToken is known or not.
   * @return  {@code true} if this Token is known;
   *          {@code false} otherwise.
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * Gets the lemma for this Token.
   * Returns null, if this RawToken is unknown.
   * @return  the lemma of this Token
   * @see RawWord#getLemma
   */
  public String getLemma() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLemma();
  }

  /**
   * Gets the part of speech for this Token.
   * Returns null, if this Token is unknown.
   * @return  the POS value of this Token
   * @see RawWord#getPos
   */
  public POS getPos() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getPos();
  }

  /**
   * Gets the lexical type for this Token.
   * Returns null, if this Token is unknown.
   * @return  the LEX value of this Token
   * @see RawWord#getLexType
   */
  public LEX getLexT() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLexType();
  }

  /**
   * Sets wheter this Token is known or not.
   * @param  known
   *         wheter this Token is known or not
   */
  public void setKnown(boolean known) {
    this.known = known;
  }

  /**
   * Sets the modified String value for this Token.
   * @param  value
   *         the modified String value to be used to set value.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets all the words for this Token.
   * @return  a List containing all the RawWord of this Token
   */
  public List<RawWord> getWords() {
    return words;
  }

  /**
   * Adds a RawWord to the Words of this Token.
   * @param  word
   *         the word to be added
   */
  public void addWord(RawWord word) {
    this.words.add(word);
  }

  /**
   * Adds a Collection of RawWords to the Words of this Token.
   * @param  words
   *         a Collection of words to be added
   * @see  <a href="https://goo.gl/d3eV5L">The Collection Interface</a>
   */
  public void addAllWords(Collection<RawWord> words) {
    this.words.addAll(words);
  }

  /**
   * Returns a String representation of this RawToken.
   * @return  a String representation of this Token
   */
  @Override
  public String toString() {

    String out =  "RawToken - OValue = " + getOValue()
        + " Value = " + getValue()
        + " Corrected = " + isCorrected()
        + " Known = " + isKnown();
    if (!isKnown()) {
      return out;
    }
    out += "\n Main Word (with more occurrence):"
        + "WordId = " + this.words.get(0).getWordId()
        + " Lemma = " + this.words.get(0).getLemma()
        + " POS = " + this.words.get(0).getPos()
        + " LextT = " + this.words.get(0).getLexType()
        + " Gloss = " + this.words.get(0).getGloss()
        + " Occurrence = " + this.words.get(0).getNum()
        + " \n All words:";
    for (RawWord w: this.words) {
      out += "\n->" + w;
    }
    return out;
  }
}
