package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;

/**
 * Class used to point to grammar and semantic word information
 * @see IToken
 * @author Andrea Rivitto && Eugenio Schintu
 *
 */
public class Token implements IToken {
	
	/**
	 * @see Word
	 * @see IWord
	 */
	private IWord word;
	
	/**
	 * Value of the original string unmodified
	 */
	private String oValue;
	
	/**
	 * Verify if a word is correct or not
	 */
	private boolean corrected;
	
	/**
	 * Constructor
	 */
	public Token(IWord w, String oValue, boolean corrected) {
		this.word = w;
		this.oValue = oValue;
		this.corrected = corrected;
	}
	
	/**
	 * @return the word
	 * @see #word
	 */
	@Override
	public IWord getWord() {
		return this.word;
	}
	
	/**
	 * @return the corrected
	 * @see #corrected
	 */
	@Override
	public boolean isCorrected() {
		return corrected;
	}
	
	/**
	 * @return the oValue
	 * @see #oValue
	 */
	public String getOValue() {
		return oValue;
	}
	
	/**
	 * Set the original value to a specific value
	 * @param oValue the oValue to set
	 * @see #oValue
	 */
	public void setOValue(String oValue) {
		this.oValue = oValue;
	}

	/**
	 * Set the boolean variable to a specific value
	 * @param corrected the corrected to set
	 * @see #corrected
	 */
	public void setCorrected(boolean corrected) {
		this.corrected = corrected;
	}
}