package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.LexType;
import com.github.bot.curiosone.core.nlp.tokenizer.PosType;
import com.github.bot.curiosone.core.nlp.tokenizer.Token;
import java.util.Collection;
import java.util.List;

/**
 * Token info from Tokenizer.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 */

public interface IToken {

  /**
   * Get value.
   *
   * @see Word#getValue()
   */

  String getValue();

  /**
   * Get oValue.
   *
   * @see Token#getOValue()
   */

  String getOValue();

  /**
   * Get lemma.
   *
   * @see Word#getLemma()
   */

  String getLemma();

  /**
   * Get known.
   *
   * @see Word#isKnown()
   */

  boolean isKnown();

  /**
   * Get Part Of Speech (POS).
   *
   * @see PosType()
   */

  PosType getPos();

  /**
   * Get LexType.
   *
   * @see LexType()
   */

  LexType getLexType();

  /**
   * Get synsets.
   *
   * @see Word#getSynsets()
   */

  List<IWord> getWords();

  /**
   * Get corrected.
   *
   * @see Token#isCorrected()
   */

  boolean isCorrected();

  /**
   * Set a new {@link #corrected} value that is provided in input.
   * 
   * @param corrected the corrected to set
   * @see #corrected
   */

  void setCorrected(boolean corrected);

  /**
   * Set a new {@link #known} value that is provided in input.
   *
   * @see #known
   */

  void setKnown(boolean known);

  /**
   * Set a new {@link #value} value that is provided in input.
   *
   * @see #value
   */

  void setValue(String value);

  /**
   * Add to {@link #word} a new instance of Word.
   *
   * @see #words
   */

  void addWord(IWord word);

  /**
   * Set to {@link #words} the list of Words.
   *
   * @see #words
   */

  void addAllWords(Collection<? extends IWord> words);

}
