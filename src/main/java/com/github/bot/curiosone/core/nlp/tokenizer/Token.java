package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

/**
 * Class used to point to grammar and semantic word information.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IToken
 */
public class Token implements IToken {

  /**
   * Word.
   *
   * @see Word
   * @see IWord
   */

  private IWord word;

  /**
   * Value of the original string unmodified.
   */

  private String originalValue;

  /**
   * Verify if a word is correct or not.
   */

  private boolean corrected;

  /**
   * Constructor.
   */

  public Token(IWord w, String originalValue, boolean corrected) {
    this.word = w;
    this.originalValue = originalValue;
    this.corrected = corrected;
  }

  /**
   * Get word.
   *
   * @return the word
   * @see #word
   */

  @Override public IWord getWord() {
    return this.word;
  }

  /**
   * Get corrected.
   *
   * @return the corrected
   * @see #corrected
   */

  @Override public boolean isCorrected() {
    return corrected;
  }

  /**
   * Get originalValue.
   *
   * @return originalValue
   * @see #originalValue
   */

  public String getOValue() {
    return originalValue;
  }

  /**
   * Set a new {@link #originalValue} value that is provided in input.
   *
   * @param originalValue the oValue to set
   * @see #originalValue
   */

  public void setOValue(String originalValue) {
    this.originalValue = originalValue;
  }

  /**
   * Set a new {@link #corrected} value that is provided in input.
   *
   * @param corrected the corrected to set
   * @see #corrected
   */

  public void setCorrected(boolean corrected) {
    this.corrected = corrected;
  }
}
