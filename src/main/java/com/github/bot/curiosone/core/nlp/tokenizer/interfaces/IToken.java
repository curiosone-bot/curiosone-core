package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;
/**
 * Token info from Tokenizer
 * @see IWord
 * @see Token
 * @author Andrea Rivitto && Eugenio Schintu
 */
public interface IToken {	
	IWord getWord();
	String getOValue();
	boolean isCorrected();
}
