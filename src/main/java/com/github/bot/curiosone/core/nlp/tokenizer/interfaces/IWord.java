package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import java.util.List;

public interface IWord {
	/**
	 * Word info from Dictionary
	 * @author riva
	 */
	public String getValue();
	public List<ISynset> getSynsets();
	public String getLemma();
	public boolean isKnown();
}
