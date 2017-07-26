package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

public interface IToken {
	/**
	 * Token info from Tokenizer
	 * @author riva
	 */
	
	public IWord getWord();
	public String getOValue();
	public boolean isCorrected();
}
