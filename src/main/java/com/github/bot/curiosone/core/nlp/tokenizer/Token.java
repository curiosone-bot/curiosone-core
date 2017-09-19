package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class used to point to grammar and semantic word information.
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IToken
 */
public class Token implements IToken {

  /**
   * Value of the original string unmodified.
   */

  private String originalValue;

  /**
   * Value of the string modified.
   */

  private String value;

  /**
   * Verify if a word is correct or not.
   */

  private boolean corrected;

  /**
   * Flag used to verify if we have found a corrispondence in dictonary.
   */
  private boolean known;

  /**
   * Various semantic informations' list.
   *
   * @see Word
   * @see IWord
   */
  private List<IWord> words;

  /**
   * Constructor.
   */

  public Token(String originalValue) {
    this.originalValue = originalValue;
    this.corrected = false;
    this.value = originalValue;
    this.known = false;
    this.words = new ArrayList<IWord>();
  }

  /**
   * Get originalValue.
   * @return originalValue
   * @see #originalValue
   */

  @Override
  public String getOValue() {
    return originalValue;
  }

  /**
   * Set a new {@link #originalValue} value that is provided in input.
   * @param originalValue the oValue to set
   * @see #originalValue
   */

  public void setOValue(String originalValue) {
    this.originalValue = originalValue;
  }

  /**
   * Get corrected.
   * @return the corrected
   * @see #corrected
   */

  @Override public boolean isCorrected() {
    return corrected;
  }

  /**
   * Set a new {@link #corrected} value that is provided in input.
   * @param corrected the corrected to set
   * @see #corrected
   */

  @Override public void setCorrected(boolean corrected) {
    this.corrected = corrected;
  }

  /**
   * Get value.
   *
   * @return the value
   * @see #value
   */

  @Override public String getValue() {
    return value;
  }

  /**
   * Get known.
   *
   * @return the known
   * @see #known
   */

  @Override public boolean isKnown() {
    return known;
  }

  /**
   * Get lemma.
   *
   * @return the lemma
   * @see #lemma
   */

  @Override public String getLemma() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLemma();
  }

  /**
   * Get Part of Speech (POS).
   *
   * @return the PosT
   * @see #PosT
   */

  @Override public PosT getPos() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getPos();
  }

  /**
   * Get LexT.
   *
   * @return the lexT
   * @see #LexT
   */

  @Override public LexT getLexT() {
    if (!isKnown()) {
      return null;
    }
    return words.get(0).getLexType();
  }

  /**
   * Set a new {@link #known} value that is provided in input.
   *
   * @see #known
   */

  @Override public void setKnown(boolean known) {
    this.known = known;
  }

  /**
   * Set a new {@link #value} value that is provided in input.
   *
   * @see #value
   */

  @Override public void setValue(String value) {
    this.value = value;
  }

  /**
   * Get words.
   *
   * @return the List of Word
   * @see #words
   */

  @Override public List<IWord> getWords() {
    return words;
  }

  /**
   * Add to {@link #word} a new instance of Word.
   *
   * @see #words
   */

  @Override public void addWord(IWord word) {
    this.words.add(word);
  }

  /**
   * Set to {@link #words} the list of Words.
   *
   * @see #words
   */

  @Override public void addAllWords(Collection<? extends IWord> words) {
    this.words.addAll(words);
  }

  /**
   * toString.
   *
   */

  @Override public String toString() {

    String out =  "Token - OValue = " + getOValue()
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
        + " \n  All words:";
    for (IWord w: this.words) {
      out += "\n  ->" + w;
    }
    return out;
  }
}