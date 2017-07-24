package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import java.util.List;
import java.util.Map;

import com.github.bot.curiosone.core.nlp.tokenizer.*;

public interface IMeaning {
	/**
	 * Semantic meaning of a word
	 * Cfr. https://projects.csail.mit.edu/jwi/api/index.html
	 */

	public WordT getType();
	public WordST getSubType();
	public String getLemma();
	public String getGloss();
	public Map<PointerT, List<String>> getLinkMap();
}
