package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import java.util.Collection;
import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.Token;

import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PosT;


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

	public String getOValue();

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
	 * @see PosT()
	 */

	PosT getPos();

	/**
	 * Get LexT.
	 *
	 * @see LexT()
	 */

	LexT getLexT();

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

	public boolean isCorrected();

	  /**
	   * Set a new {@link #corrected} value that is provided in input.
	   * @param corrected the corrected to set
	   * @see #corrected
	   */

	  public void setCorrected(boolean corrected);

	  /**
	   * Set a new {@link #known} value that is provided in input.
	   *
	   * @see #known
	   */

	  public void setKnown(boolean known);

	  /**
	   * Set a new {@link #value} value that is provided in input.
	   *
	   * @see #value
	   */

	  public void setValue(String value);

	  /**
	   * Add to {@link #word} a new instance of Word.
	   *
	   * @see #words
	   */

	  public void addWord(IWord word);

	  /**
	   * Set to {@link #words} the list of Words.
	   *
	   * @see #words
	   */

	  public void setWords(Collection<? extends IWord> words);

}