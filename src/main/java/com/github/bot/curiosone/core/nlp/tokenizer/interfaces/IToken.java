package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.Token;

/**
 * Token info from Tokenizer.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 */

public interface IToken {

  /**
   * Get word.
   *
   * @see Token#getWord()
   */

  public IWord getWord();

  /**
   * Get oValue.
   *
   * @see Token#getOValue()
   */

  public String getOValue();

  /**
   * Get corrected.
   *
   * @see Token#isCorrected()
   */

  public boolean isCorrected();
}
