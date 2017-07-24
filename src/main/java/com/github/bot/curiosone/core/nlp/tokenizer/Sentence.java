package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.*;

public class Sentence implements ISentence {
	
	private SentenceT type;
	private String original;
	private List<IToken> tokens;

	@Override
	public SentenceT getType() {
		return type;
	}

	@Override
	public String getOriginal() {
		return original;
	}

	@Override
	public List<IToken> getTokens() {
		return tokens;
	}

	public void setType(SentenceT type) {
		this.type = type;
	}

	public void addToken(IToken token) {
		this.tokens.add(token);
	}
	
}
