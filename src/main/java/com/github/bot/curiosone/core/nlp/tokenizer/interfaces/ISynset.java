package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.POST;
import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PointerT;

import java.util.List;
import java.util.Map;
/**
 * Syntact/Semantic attribute of a word
 * @see https://projects.csail.mit.edu/jwi/api/index.html
 * @see POST
 * @see LexT
 * @see PointerT
 * @author Andrea Rivitto && Eugenio Schintu
 */
public interface ISynset {
	POST getPOS();
	LexT  getLexType();
	String getLemma();
	String getGloss();
	Map<PointerT, List<String>> getRelations();
}
