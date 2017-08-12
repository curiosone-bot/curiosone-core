package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import edu.mit.jwi.item.IWordID;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Syntax/Semantic information of a Word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IWord
 */
public class Word implements IWord {

  /**
   * Part of speech.
   *
   * @see POST
   */

  private PosT pos;

  /**
   * Lexicographic file name.
   *
   * @see LexT
   */

  private LexT lexType;

  /**
   * Lemma of word.
   *
   * @see Word
   */

  private String lemma;

  /**
   * IWordID.
   *
   * @see IWordID
   */

  private IWordID wordID;
  
  /**
   * Glossary information.
   */

  private String gloss;

  /**
   * Semantic relation with other word.
   *
   * @see PointerT
   */

  /**
   * Number of occurrence 
   *
   */
  private int number;

  /**
   * Semantic relation with other word.
   *
   * @see PointerT
   */

  private Map<PointerT, List<String>> relations;

  /**
   * Get WordID.
   *
   * @return the WordID
   * @see #IWordID
   */

  @Override public IWordID getWordID() {
	    return wordID;
  }

  /**
   * Set a new {@link #wordID} value that is provided in input.
   *
   * @see #wordID
   */

  public void setWordID(IWordID wordID) {
    this.wordID = wordID;
  }

  /**
   * Get pos.
   *
   * @return the pos
   * @see #pos
   */

  @Override public PosT getPos() {
	    return pos;
  }

  /**
   * Set a new {@link #pos} value that is provided in input.
   *
   * @see #pos
   */

  public void setPos(PosT pos) {
    this.pos = pos;
  }

  /**
   * Get lexType.
   *
   * @return lexType
   * @see #lexType
   */

  @Override public LexT getLexType() {
    return lexType;
  }

  /**
   * Set a new {@link #lexType} value that is provided in input.
   *
   * @see #lexType
   */

  @Override public void setLexType(LexT lexType) {
    this.lexType = lexType;
  }

  /**
   * Get lemma.
   *
   * @return the lemma
   * @see #lemma
   */

  @Override public String getLemma() {
    return lemma;
  }

  /**
   * Set a new {@link #lemma} value that is provided in input.
   *
   * @see #lemma
   */

  @Override public void setLemma(String lemma) {
    this.lemma = lemma;
  }

  /**
   * Get gloss.
   *
   * @return the gloss
   * @see #gloss
   */
  @Override public String getGloss() {
    return gloss;
  }

  /**
   * Set a new {@link #gloss} value that is provided in input.
   *
   * @see #gloss
   */
  @Override public void setGloss(String gloss) {
    this.gloss = gloss;
  }

  /**
   * Get the number of occurrence.
   *
   */
  @Override public int getNum() {
    return this.number;
  }

  /**
   * Set the number of occurrence of word.
   *
   */
  @Override public void setNum(int num) {
    this.number = num;
  }

  /**
   * Get relations.
   *
   * @return the relations
   * @see #relations
   */
  @Override public Map<PointerT, List<String>> getRelations() {
    return relations;
  }

  /**
   * Add a new element to relations.
   *
   * @see #relations
   */

  @Override public void addRelation(PointerT p, List<String> value) {
	  this.relations.put(p, value);
  }

  /**
   * Set the map of relations.
   *
   * @see #relations
   */

  @Override public void setRelations(Map<PointerT, List<String>> relations) {
	  this.relations.clear();
	  this.relations.putAll(relations);
  }

  /**
   * toString.
   *
   */

  @Override public String toString() {
	return "WordID=" + this.wordID 
			+ " Lemma=" + this.lemma
			+ " POS=" + this.pos
			+ " LextT=" + this.lexType
			+ " Gloss=" + this.gloss
			+ " Occurrence=" + this.number;
  }
  
  @Override public boolean equals(Object obj) {
	  if (obj == null || !(obj instanceof Word)) return false;
	  if (obj == this) return true;
	  Word w = (Word) obj;
	  return this.getWordID().equals(w.getWordID());
  }
	
  @Override public int hashCode() {
	  return Objects.hash(getGloss());
  }
  
}
