package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.Sentence;
import com.github.bot.curiosone.core.nlp.tokenizer.SentenceType;
import com.github.bot.curiosone.core.nlp.tokenizer.Token;
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

  SentenceType getType();

  /**
   * Get original.
   *
   * @see Sentence#getOriginal()
   */

  String getOriginal();

  /**
   * Get tokens.
   *
   * @see Sentence#getTokens()
   */

  List<IToken> getTokens();

  /**
   * Add a new token to {@link #tokens} .
   * 
   * @see #tokens
   * @see Token
   */

  void addToken(IToken token);

  /**
   * Set a new {@link #type} value provided in input.
   * 
   * @see #type
   */

  void setType(SentenceType type);
}
