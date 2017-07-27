package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.Sentence;
import com.github.bot.curiosone.core.nlp.tokenizer.SentenceT;

import java.util.List;

/**
 * Sentence info.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 */

public interface ISentence {

  /**
   * Get type.
   *
   * @see Sentence#getType()
   */

  public SentenceT getType();

  /**
   * Get original.
   *
   * @see Sentence#getOriginal()
   */

  public String getOriginal();

  /**
   * Get tokens.
   *
   * @see Sentence#getTokens()
   */

  public List<IToken> getTokens();
}
