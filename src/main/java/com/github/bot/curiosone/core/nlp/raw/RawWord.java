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
 * Stores a Word with its Syntax/Semantic information.
 */
public class RawWord {

  /**
   * Part of Speech of this RawWord.
   */
  private POS pos;

  /**
   * Lexicographic type of this RawWord.
   */
  private LEX lexType;

  /**
   * Lemma of this RawWord.
   */
  private String lemma;

  /**
   * Stores the WordNet ID of this RawWord.
   * @see  IWordID
   */
  private IWordID wordId;

  /**
   * Stores the glossary information.
   */
  private String gloss;

  /**
   * Stores the number of occurrence.
   */
  private int number;

  /**
   * Maps the Semantic Relations of this RawWord with other words.
   */
  private Map<String, List<String>> relations;

  /**
   * Gets the WordID.
   * @return  the WordID
   * @see IWordID
   * @see #wordId
   */
  public IWordID getWordId() {
    return wordId;
  }

  /**
   * Constructs an empty RawWord.
   */
  public RawWord() {
    relations = new HashMap<String, List<String>>();
  }


  /**
   * Sets the WordID of this RawWord.
   * @param  wordId
   *         the word ID to be set
   */
  public void setWordId(IWordID wordId) {
    this.wordId = wordId;
  }

  /**
   * Gets the POS of this RawWord.
   * @return  the POS value of this RawWord
   */
  public POS getPos() {
    return pos;
  }

  /**
   * Sets the {@link #pos} of this RawWord.
   * @param  pos
   *         the POS value to be set
   */
  public void setPos(POS pos) {
    this.pos = pos;
  }

  /**
   * Gets the LEX of this RawWord.
   * @return the LEX value of this RawWord
   */
  public LEX getLexType() {
    return lexType;
  }

  /**
   * Sets the {@link #lexType} of this RawWord.
   * @param  lexType
   *         the LEX value to be set
   */
  public void setLexType(LEX lexType) {
    this.lexType = lexType;
  }

  /**
   * Gets the lemma of this RawWord.
   * @return  the lemma of this RawWord
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * Sets the lemma of this RawWord.
   * @param  lemma
   *         the String representation of the lemma to be set
   * @see #lemma
   */
  public void setLemma(String lemma) {
    this.lemma = lemma;
  }

  /**
   * Gets the gloss of this RawWord.
   * @return  the gloss of this RawWord
   */
  public String getGloss() {
    return gloss;
  }

  /**
   * Sets the gloss of this RawWord.
   * @param  gloss
   *         String representation of the gloss to be set
   */
  public void setGloss(String gloss) {
    this.gloss = gloss;
  }

  /**
   * Gets the number of occurrence of this RawWord.
   * @return  the number of occurrence of this RawWord
   */
  public int getNum() {
    return this.number;
  }

  /**
   * Sets the number of occurrence of this RawWord.
   * @param  num
   *         the number of occurrence to be set
   */
  public void setNum(int num) {
    this.number = num;
  }

  /**
   * Gets all the relations of this RawWord.
   * @return  a Map containing all the relations of this RawWord
   */
  public Map<String, List<String>> getRelations() {
    return relations;
  }

  /**
   * Gets relations by String.
   * Returns null, if no Relation by String is found.
   * @param  pointer
   *         the String representation of the source.
   *         This String is supposed to be a key in the #relations Map.
   * @return  a List containing all the Words in relation with the given Word
   */
  public List<String> getRelationsByString(String pointer) {
    return new ArrayList<String>(relations.getOrDefault(pointer, null));
  }

  /**
   * Adds a new Relation to this RawWord.
   * @param  p
   *         the source of the new Relation
   * @param  v
   *         the target of the new Relation
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
   * Sets the relations of this RawWord.
   * @param  relations
   *         the Map containing the relations of this RawWord to be set
   * @see #relations
   */
  public void setRelations(Map<String, List<String>> relations) {
    this.relations.clear();
    this.relations.putAll(relations);
  }

  /**
   * Returns a String representation of this RawWord.
   * @return  a String representation of this RawWord
   *
   */
  @Override
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
   * Checks wheter this RawWord equals to the given object.
   * @param  obj
   *         the object to be compared against
   * @return  {@code true} if the given object represents the same RawWord of this instance;
              {@code false} otherwise
   */
  @Override
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
   * Calculates the hashCode of this RawWord.
   * The hashCode depends on the gloss of this RawWord.
   * @return  the hashCode of this RawWord
   */
  @Override
  public int hashCode() {
    return Objects.hash(getGloss());
  }

}
