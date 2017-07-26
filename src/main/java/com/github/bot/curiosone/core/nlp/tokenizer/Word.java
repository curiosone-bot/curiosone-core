package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.ISynset;
import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import java.util.List;
/**
 * Word attribute and relative Synset.
 * @see IWord
 * @see Synset
 * @author Andrea Rivitto && Eugenio Schintu
 */
public class Word implements IWord {
    /**
     * Value of the single word in the original Sentence.
     * @see Sentence
     */
    private String value;
    /**
     * Various semantic informations' list.
     * @see Synset
     * @see ISynset
     */
    private List<ISynset> synsets;
    /**
     * Flag used to verify if we have found a corrispondence in dictonary. 
     */
    private boolean known;
    /**
     * Lemma of a specific word.
     */
    private String lemma;
    /**
     * Constructor.
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
     * Get value.
     * @return the value
     * @see #value
     */
    @Override
    public String getValue() {
        return value;
    }   
    /**
     * Get known.
     * @return the known
     * @see #known
     */
    @Override
    public boolean isKnown() {
        return known;
    }   
    /**
     * Get synsets.
     * @return the List of Synset
     * @see #synsets 
     */
    @Override
    public List<ISynset> getSynsets() {
        return synsets;
    }
    /**
     * Get lemma.
     * @return the lemma
     * @see #lemma
     */
    @Override
    public String getLemma() {
        return lemma;
    }   
    /**
     * Set a new {@link #known} value that is provided in input.
     * @param known
     * @see #known
     */
    public void setKnown(boolean known) {
        this.known = known;
    }   
    /**
     * Set a new {@link #value} value that is provided in input.
     * @param value
     * @see #value
     */
    public void setValue(String value) {
        this.value = value;
    }   
    /**
     * Set a new {@link #lemma} value that is provided in input.
     * @param new lemma
     * @see #lemma
     */
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }   
    /**
     * Add to synset a new instance of Synset.
     * @param new Synset
     * @see #synsets
     * 
     */
    public void addSynset(ISynset synset) {
        this.synsets.add(synset);
    }   
}
