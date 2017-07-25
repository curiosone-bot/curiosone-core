package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.POST;
import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PointerT;

import java.util.List;
import java.util.Map;

public interface ISynset {
	/**
	 * Syntact/Semantic attribute of a word
	 * Cfr. https://projects.csail.mit.edu/jwi/api/index.html
	 */

	public POST getPOS();
	public LexT  getLexType();
	public String getLemma();
	public String getGloss();
	public Map<PointerT, List<String>> getRelations();
}
