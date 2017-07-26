package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISentence;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;
/**
 * Structure that grammar's module needs to understand what the user says and to generate an appropriate answer. * 
 * @see ISentence 
 * @author Andrea Rivitto && Eugenio Schintu
 */
public class Sentence implements ISentence {
	/**
	 * Sentence typology.
	 * @see SentenceT 
	 */
	private SentenceT type;
	/**
	 * Original string provided in input by the user.
	 */
	private String original;
	/**
	 * Tokens' list.
	 * @see IToken
	 */
	private List<IToken> tokens;
	/**
	 * Get type.
	 * @return type
	 * @see #type
	 */
	@Override
	public SentenceT getType() {
		return type;
	}
	/**
	 * Get original.
	 * @return original
	 * @see #original 
	 */
	@Override
	public String getOriginal() {
		return original;
	}	
	/**
	 * Get tokens.
	 * @return tokens
	 * @see #tokens
	 * @see Token
	 * @see IToken
	 */
	@Override
	public List<IToken> getTokens() {
		return tokens;
	}
	/**
	 * Set a new {@link #type} value provided in input.
	 * @param type
	 * @see #type
	 */
	public void setType(SentenceT type) {
		this.type = type;
	}
	
	/**
	 * Add a new token to {@link #tokens} .
	 * @param token
	 * @see #tokens
	 * @see Token
	 */
	public void addToken(IToken token) {
		this.tokens.add(token);
	}	
}