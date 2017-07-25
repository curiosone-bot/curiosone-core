package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISentence;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;

public class Sentence implements ISentence {
	/**
	 * Tokenizer result
	 * @author riva
	 */
	
	private SentenceT type;
	private String original;
	private List<IToken> tokens;

	@Override
	/**
	 * @return type
	 */
	public SentenceT getType() {
		return type;
	}

	@Override
	/**
	 * @return original String in input to tokenizer 
	 */
	public String getOriginal() {
		return original;
	}
	
	/**
	 * @return list of token
	 */
	@Override
	public List<IToken> getTokens() {
		return tokens;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SentenceT type) {
		this.type = type;
	}
	
	/**
	 * @param token the token to add
	 */
	public void addToken(IToken token) {
		this.tokens.add(token);
	}
	
}
