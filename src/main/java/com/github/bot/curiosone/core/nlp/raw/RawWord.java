package com.github.bot.curiosone.core.nlp.raw;

import com.github.bot.curiosone.core.nlp.LEX;
import com.github.bot.curiosone.core.nlp.POS;
import edu.mit.jwi.item.IWordID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Syntax/Semantic information of a RawWord.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 */
public class RawWord {

  /**
   * Part of speech.
   *
   * @see POST
   */
  private POS pos;

  /**
   * Lexicographic file name.
   *
   * @see LEX
   */
  private LEX lexType;

  /**
   * Lemma of word.
   *
   * @see RawWord
   */
  private String lemma;

  /**
   * IWordID.
   *
   * @see IWordID
   */
  private IWordID wordId;

  /**
   * Glossary information.
   */
  private String gloss;

  /**
   * Number of occurrence.
   *
   */
  private int number;

  /**
   * Semantic relation with other word.
   *
   * @see String
   */
  private Map<String, List<String>> relations;

  /**
   * Get WordID.
   *
   * @return the WordID
   * @see #IWordID
   */
  public IWordID getWordId() {
    return wordId;
  }

  /**
   * Constructor.
   */
  public RawWord() {
    relations = new HashMap<String, List<String>>();
  }


  /**
   * Set a new {@link #wordId} value that is provided in input.
   *
   * @see #wordId
   */
  public void setWordId(IWordID wordId) {
    this.wordId = wordId;
  }

  /**
   * Get pos.
   *
   * @return the pos
   * @see #pos
   */
  public POS getPos() {
    return pos;
  }

  /**
   * Set a new {@link #pos} value that is provided in input.
   *
   * @see #pos
   */
  public void setPos(POS pos) {
    this.pos = pos;
  }

  /**
   * Get lexType.
   *
   * @return lexType
   * @see #lexType
   */
  public LEX getLexType() {
    return lexType;
  }

  /**
   * Set a new {@link #lexType} value that is provided in input.
   *
   * @see #lexType
   */
  public void setLexType(LEX lexType) {
    this.lexType = lexType;
  }

  /**
   * Get lemma.
   *
   * @return the lemma
   * @see #lemma
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * Set a new {@link #lemma} value that is provided in input.
   *
   * @see #lemma
   */
  public void setLemma(String lemma) {
    this.lemma = lemma;
  }

  /**
   * Get gloss.
   *
   * @return the gloss
   * @see #gloss
   */
  public String getGloss() {
    return gloss;
  }

  /**
   * Set a new {@link #gloss} value that is provided in input.
   *
   * @see #gloss
   */
  public void setGloss(String gloss) {
    this.gloss = gloss;
  }

  /**
   * Get the number of occurrence.
   *
   */
  public int getNum() {
    return this.number;
  }

  /**
   * Set the number of occurrence of word.
   *
   */
  public void setNum(int num) {
    this.number = num;
  }

  /**
   * Get relations.
   *
   * @return the relations
   * @see #relations
   */
  public Map<String, List<String>> getRelations() {
    return relations;
  }

  /**
   * Get relations by String.
   *
   * @see RawWord#getRelationsByString()
   */
  public List<String> getRelationsByString(String pointer) {
    return new ArrayList<String>(relations.getOrDefault(pointer, null));
  }

  /**
   * Add a new element to relations.
   *
   * @see #relations
   */
  public void addRelation(String p, String v) {
    this.relations.merge(
        p, new ArrayList<String>(
            Arrays.asList(v)), (v1,v2) -> {
            v1.add(v);
            return v1;
        });
  }

  /**
   * Set the map of relations.
   *
   * @see #relations
   */
  public void setRelations(Map<String, List<String>> relations) {
    this.relations.clear();
    this.relations.putAll(relations);
  }

  /**
   * toString.
   *
   */
  public String toString() {

    String out = "WordId = " + this.wordId
        + " Lemma = " + this.lemma
        + " POS = " + this.pos
        + " LextT = " + this.lexType
        + " Gloss = " + this.gloss
        + " Occurrence = " + this.number;

    out += "\n" + relations.entrySet().toString();
    return out;
  }

  /**
   * Compares this RawWord to the given object.
   * @param obj the objects to be compared against
   * @return <code>true</code> if the given object represents the same RawWord
   *         of this instance;
             <code>false</code> otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof RawWord)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    RawWord w = (RawWord) obj;
    return this.getWordId().equals(w.getWordId());
  }

  /**
   * Returns the hashcode for this RawWord.
   */
  public int hashCode() {
    return Objects.hash(getGloss());
  }

}
