package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.SentenceT;

import java.util.List;
/**
 * Sentence info
 * @see SentenceT
 * @see IToken
 * @author Andrea Rivitto && Eugenio Schintu
 */
public interface ISentence {
	SentenceT getType();
	String getOriginal();
	List<IToken> getTokens();
}
