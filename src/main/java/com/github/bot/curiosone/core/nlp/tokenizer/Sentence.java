package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISentence;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;
/**
 * Structure that grammar's module needs to understand what the user says and to generate an appropriate answer
 * 
 *  @see ISentence
 *  
 * @author Andrea Rivitto && Eugenio Schintu
 *
 */
public class Sentence implements ISentence {
	
	/**
	 * Tipology of a sentence
	 * @see SentenceT
	 */
	private SentenceT type;
	
	/**
	 * String provided in input
	 */
	private String original;
	
	/**
	 * List of all tokens that are in the string provided in input
	 * @see #original
	 * @see Token
	 * @see IToken
	 */
	private List<IToken> tokens;

	/**
	 * @return the type of the sentence
	 * @see #type
	 * @see Sentence
	 */
	@Override
	public SentenceT getType() {
		return type;
	}

	/**
	 * @return the original string parovided in input
	 * @see #original
	 */
	@Override
	public String getOriginal() {
		return original;
	}

	/**
	 * @return the list of sentence's token
	 * @see #tokens
	 * @see Token
	 * @see IToken
	 */
	@Override
	public List<IToken> getTokens() {
		return tokens;
	}

	public void setType(SentenceT type) {
		this.type = type;
	}

	/**
	 * Add a token to the List named tokens
	 * @param token to add
	 * @return tokens with a new element
	 * @see tokens
	 * @see Token
	 */
	public void addToken(IToken token) {
		this.tokens.add(token);
	}
}