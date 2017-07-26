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
     * Part of speech.
     * @see POST
     */
    private POST pos;
    /**
     * Lexicographic file name.
     * @see LexT
     */
    private LexT lexType;
    /**
     * Lemma of word.
     * @see Word
     */
    private String lemma;
    /**
     * Glossary information.
     */
    private String gloss;
    /**
     * Semantic relation with other word.
     * @see PointerT
     */
    private Map<PointerT, List<String>> relations;
    /**
     * Get pos.
     * @return the pos
     * @see #pos
     */
    @Override
    public POST getPOS() {
        return pos;
    }    
    /**
     * Set a new {@link #pos} value that is provided in input.
     * @param pos
     * @see #pos 
     */
    public void setPOS(POST pos) {
        this.pos = pos;
    }
    /**
     * Get lexType.
     * @return lexType
     * @see #lexType
     */
    @Override
    public LexT getLexType() {
        return lexType;
    }
    /**
     * Set a new {@link #lexType} value that is provided in input.
     * @param lexType
     * @see #lexType
     */
    public void setLexType(LexT lexType) {
        this.lexType = lexType;
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
     * Set a new {@link #lemma} value that is provided in input.
     * @param lemma
     * @see #lemma
     */
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }
    /**
     * Get gloss.
     * @return the gloss
     * @see #gloss
     */
    @Override
    public String getGloss() {
        return gloss;
    }
    /**
     * Set a new {@link #gloss} value that is provided in input.
     * @param gloss
     * @see #gloss
     */
    public void setGloss(String gloss) {
        this.gloss = gloss;
    }
    /**
     * Get relations.
     * @return the relations
     * @see #relations
     */
    public Map<PointerT, List<String>> getRelations() {
        return relations;
    }
    /**
     * Add a new element to reletions.
     * @param p
     * @param value
     * @see #relations
     */
    public void addRelation(PointerT p, List<String> value) {
        this.relations.put(p, value);
    }   
}