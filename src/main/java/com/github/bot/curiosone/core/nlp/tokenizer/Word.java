package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import java.util.List;
/**
 * Word attribute and relative Synset
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IWord
 * @see Synset
 */
public class Word implements IWord {
	/**
	 * Value of the single word in the original Sentence
	 * @see Sentence
	 */
	private String value;
	/**
	 * Various semantic informations' list
	 * @see Synset
	 * @see ISynset
	 */
	private List<ISynset> synsets;
	/**
	 * Flag used to verify if we have found a corrispondence in dictonary 
	 */
	private boolean known;
	/**
	 * Lemma of a specific word
	 */
	private String lemma;
	/**
	 * Constructor
	 * @param value
	 * @param known
	 * @param lemma
	 */
	public Word(String value, boolean known, String lemma) {
		this.value = value;
		this.known = known;
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
	 * @see #known
	 */
	@Override
	public boolean isKnown() {
		return known;
	}	
	/**
	 * @return the List of Synset
	 * @see #synsets 
	 */
	@Override
	public List<ISynset> getSynsets() {
		return synsets;
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
	 * Set known to a specific value
	 * @param new known
	 * @see #value
	 */
	public void setKnown(boolean known) {
		this.known = known;
	}	
	/**
	 * Set value to a specific value
	 * @param new value
	 * @see #value
	 */
	public void setValue(String value) {
		this.value = value;
	}	
	/**
	 * Set lemma to a specific value
	 * @param new lemma
	 * @see #lemma
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}	
	/**
	 * Add to synset a new instance of Synset
	 * @param new Synset
	 * @see #synsets
	 * 
	 */
	public void addSynset(ISynset synset) {
		this.synsets.add(synset);
	}
	
}
