package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.*;

import java.util.List;

public interface ISentence {
	/**
	 * Sentence info
	 * @author riva
	 */
	public SentenceT getType();
	public String getOriginal();
	public List<IToken> getTokens();
}
