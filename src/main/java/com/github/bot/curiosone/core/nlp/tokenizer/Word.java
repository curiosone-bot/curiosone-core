package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import java.util.List;

public class Word implements IWord {
	/**
	 * Word attribute and relative Synset
	 * @author riva
	 */
	private String value;
	private List<ISynset> synsets; 
	private boolean known;
	private String lemma;
	
	public Word(String s, boolean k, String lemma) {
		this.value = s;
		this.known = k;
		this.lemma = lemma;
	}
	
	/**
	 * @return the value
	 */
	@Override
	public String getValue() {
		return value;
	}
	
	/**
	 * @return the known
	 */
	@Override
	public boolean isKnown() {
		return known;
	}
	
	/**
	 * @return the List of Synset 
	 */
	@Override
	public List<ISynset> getSynsets() {
		return synsets;
	}

	/**
	 * @return the lemma
	 */
	@Override
	public String getLemma() {
		return lemma;
	}
	
	/**
	 * @param known the known to set
	 */
	public void setKnown(boolean known) {
		this.known = known;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @param lemma the lemma to set
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	
	/**
	 * @param synset the synset to add
	 */
	public void addSynset(ISynset synset) {
		this.synsets.add(synset);
	}
	
}
