package com.github.bot.curiosone.core.nlp.base.raw;

import com.github.bot.curiosone.core.nlp.base.LexicalType;
import com.github.bot.curiosone.core.nlp.base.PartOfSpeechType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Grammar and semantic word information pointer class.
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IToken
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
   * Various semantic informations' list.
   * @see Word
   * @see RawWord
   */
  private List<RawWord> words;

  /**
   * constructs a RawToken, using an original value.
   * @param originalValue the original token value.
   */
  public RawToken(String originalValue) {
    this.originalValue = originalValue;
    this.corrected = false;
    this.value = originalValue;
    this.known = false;
    this.words = new ArrayList<RawWord>();
  }

  /**
   * Returns the original value.
   * @see #originalValue
   */
  public String getOValue() {
    return originalValue;
  }

  /**
   * Sets the original value.
   * @param originalValue the value to set the original value to.
   * @see #originalValue
   */
  public void setOValue(String originalValue) {
    this.originalValue = originalValue;
  }

  /**
   * Returns <code>true</code> if this Token is correct;
   *         <code>false</code> otherwise.
   * @see #corrected
   */
  public boolean isCorrected() {
    return corrected;
  }

  /**
   * Sets wheter this Token is correct or not.
   * @param corrected wheter the token is correct or not.
   * @see #corrected
   */
  public void setCorrected(boolean corrected) {
    this.corrected = corrected;
  }

  /**
   * Gets the modified String value.
   * @see #value
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns <code>true</code> if this Token is known;
   *         <code>false</code> otherwise.
   * @see #known
   */
  public boolean isKnown() {
    return known;
  }

  /**
   * Gets the lemma for this Token.
   * @see #lemma
   */
  public String getLemma() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLemma();
  }

  /**
   * Gets the part of speech for this Token.
   * @see #PartOfSpeechType
   */
  public PartOfSpeechType getPos() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getPos();
  }

  /**
   * Gets the lexical type for this Token.
   * @see #LexicalType
   */
  public LexicalType getLexT() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLexType();
  }

  /**
   * Sets wheter this Token is known or not.
   * @param known wheter this Token is known or not
   * @see #known
   */
  public void setKnown(boolean known) {
    this.known = known;
  }

  /**
   * Sets the modified String value for this Token.
   * @param value the modified String value to be used to set value.
   * @see #value
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets all the words for this Token.
   * @see #words
   */
  public List<RawWord> getWords() {
    return words;
  }

  /**
   * Adds a word to the already present words Collection.
   * @param word the word to be added
   * @see #words
   */
  public void addWord(RawWord word) {
    this.words.add(word);
  }

  /**
   * Adds a Collection of word to the already present words Collection.
   * @param words a Collection of words to be added
   * @see #words
   */
  public void addAllWords(Collection<RawWord> words) {
    this.words.addAll(words);
  }

  /**
   * Returns a String representation of this Token.
   */
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
