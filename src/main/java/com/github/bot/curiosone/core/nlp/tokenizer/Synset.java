package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.List;
import java.util.Map;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;

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
	private String gloss;
	private Map<PointerT, List<String>> relations;
	
	/**
	 * @return the pos
	 */
	@Override
	public POST getPOS() {
		return pos;
	}
	
	/**
	 * @param pos the pos to set
	 */
	public void setPOS(POST pos) {
		this.pos = pos;
	}

	/**
	 * @return the lexType
	 */
	@Override
	public LexT getLexType() {
		return lexType;
	}

	/**
	 * @param lexType the lexType to set
	 */
	public void setLexType(LexT lexType) {
		this.lexType = lexType;
	}

	/**
	 * @return the lemma
	 */
	@Override
	public String getLemma() {
		return lemma;
	}

	/**
	 * @param lemma the lemma to set
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	/**
	 * @return the gloss
	 */
	@Override
	public String getGloss() {
		return gloss;
	}

	/**
	 * @param gloss the gloss to set
	 */
	public void setGloss(String gloss) {
		this.gloss = gloss;
	}

	/**
	 * @return the relations
	 */
	public Map<PointerT, List<String>> getRelations() {
		return relations;
	}

	/**
	 * @param relations the relations to set
	 */
	public void setRelations(Map<PointerT, List<String>> relations) {
		this.relations = relations;
	}
	
}
