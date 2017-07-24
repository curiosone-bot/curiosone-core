package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import java.util.List;

public interface IWord {
	/**
	 * Word info from Dictionary
	 * @author riva
	 */
	public String getValue();
	public List<IMeaning> getMeanings();
	public boolean isKnown();
}
