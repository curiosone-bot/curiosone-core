package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Type of String in Dictionary call. It is used for organize the calling to the Dictionary
 * 
 * @see Token
 * 
 * @author Andrea Rivitto && Eugenio Schintu
 */
public enum PositionT {	
	/**
	 * String before the one analyzed
	 */
	PRE,
	
	/**
	 * String after the one analyzed
	 */
	POS,
	
	/**
	 * String to analyz
	 */
	ELE
}
