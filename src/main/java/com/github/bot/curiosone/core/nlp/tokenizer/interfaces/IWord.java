package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.Word;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;
/**
 * Word info from Dictionary
 * @see ISynset
 * @see Word
 * @author Andrea Rivitto && Eugenio Schintu
 */
public interface IWord {
	String getValue();
	List<ISynset> getSynsets();
	String getLemma();
	boolean isKnown();
}
