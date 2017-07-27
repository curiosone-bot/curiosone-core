package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.POST;
import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PointerT;
import com.github.bot.curiosone.core.nlp.tokenizer.Synset;

import java.util.List;
import java.util.Map;

/**
 * Syntact/Semantic attribute of a word.
 * @see https://projects.csail.mit.edu/jwi/api/index.html
 * @author Andrea Rivitto && Eugenio Schintu
 */
public interface ISynset {
	/**
	 * Get pos.
	 * @see Synset#getPOS()
	 */
	public POST getPOS();
	/**
	 * Get lexType.
	 * @see Synset#getLexType()
	 */
	public LexT  getLexType();
	/**
	 * Get lemma.
	 * @see Synset#getLemma()
	 */
	public String getLemma();
	/**
	 * Get gloss.
	 * @see Synset#getGloss()
	 */
	public String getGloss();
	/**
	 * Get relations.
	 * @see Synset#getRelations()
	 */
	public Map<PointerT, List<String>> getRelations();
}