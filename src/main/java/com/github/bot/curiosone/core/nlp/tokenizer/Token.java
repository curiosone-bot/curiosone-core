package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.*;

public class Token implements IToken {
	/**
	 * Class used to point to grammar and semantic word information
	 * @author riva
	 */
	private IWord word;
	private String oValue;
	private boolean corrected;
	
	public Token(IWord w, String oValue, boolean corrected) {
		/**
		 * Construct 
		 */
		this.word = w;
		this.oValue = oValue;
		this.corrected = corrected;
	}
	
	/**
	 * @return the word
	 */
	@Override
	public IWord getWord() {
		return this.word;
	}
	
	/**
	 * @return the oValue
	 */
	public String getOValue() {
		return oValue;
	}
	
	/**
	 * @param oValue the oValue to set
	 */
	public void setOValue(String oValue) {
		this.oValue = oValue;
	}

	@Override
	/**
	 * @return the corrected
	 */
	public boolean isCorrected() {
		return corrected;
	}

	/**
	 * @param corrected the corrected to set
	 */
	public void setCorrected(boolean corrected) {
		this.corrected = corrected;
	}

}
