package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;
import java.util.Map;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;

/**
 * Syntax/Semantic information of a Word 
 * @see ISynset
 * @author Andrea Rivitto && Eugenio Schintu
 */
public class Synset implements ISynset{
	/**
	 * Syntax/Semantic information of a Word:
	 * pos: part of speech
	 * lex: lexicographic file name
	 * lemma: lemma of word
	 * gloss: glossary information
	 * realation: semantic relation with other word
	 * 
	 * @author riva
	 */
	
	private POST pos;
	private LexT lexType;
	private String lemma;
	/**
	 * Glossary information
	 */
	private String gloss;
	/**
	 * Semantic relation with other word
	 * @see PointerT
	 */
	private Map<PointerT, List<String>> relations;
	
	/**
	 * @return the pos
	 * @see #pos
	 */
	@Override
	public POST getPOS() {
		return pos;
	}
	
	/**
	 * Set the pos to a specific value
	 * @param new pos
	 * @see #pos 
	 */
	public void setPOS(POST pos) {
		this.pos = pos;
	}

	/**
	 * @return the lexType
	 * @see #lexType
	 */
	@Override
	public LexT getLexType() {
		return lexType;
	}

	/**
	 * Set le lexType to a specific value
	 * @param new lexType
	 * @see #lexType
	 */
	public void setLexType(LexT lexType) {
		this.lexType = lexType;
	}

	/**
	 * @return the lemma
	 * @see #lemma
	 */
	@Override
	public String getLemma() {
		return lemma;
	}

	/**
	 * Set the lemma to a specific value
	 * @param new lemma
	 * @see #lemma
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	/**
	 * @return the gloss
	 * @see #gloss
	 */
	@Override
	public String getGloss() {
		return gloss;
	}

	/**
	 * Set the gloss to a speficic value
	 * @param new gloss
	 * @see #gloss
	 */
	public void setGloss(String gloss) {
		this.gloss = gloss;
	}

	/**
	 * @return the relations
	 * @see #relations
	 */
	public Map<PointerT, List<String>> getRelations() {
		return relations;
	}

	/**
	 * Add a new element to reletions
	 * @param p
	 * @param value
	 * @see #relations
	 */
	public void addRelation(PointerT p, List<String> value) {
		this.relations.put(p, value);
	}
	
}
