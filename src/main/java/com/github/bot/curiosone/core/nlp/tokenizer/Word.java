package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IWord;

import edu.mit.jwi.item.IWordID;

import java.util.List;
import java.util.Map;

/**
 * Syntax/Semantic information of a Word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see IWord
 */
public class Word implements IWord {

  /**
   * Part of speech type.
   * @see POST
   */

  private PosT pos;

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
   * IWordID.
   * @see IWordID
   */

  private IWordID wordId;

  /**
   * Glossary information.
   */

  private String gloss;

  /**
   * Semantic relation with other word.
   * @see PointerT
   */

  /**
   * Number of occurrence.
   */
  private int number;

  /**
   * Semantic relation with other word.
   * @see PointerT
   */

  private Map<PointerT, List<String>> relations;

  /**
   * Get {@link #pos}.
   * @return {@link #pos}
   */
  @Override
  public PosT getPos() {
    return pos;
  }

  /**
   * Get {@link #lexType}.
   * @return {@link #lexType}
   */
  @Override
  public LexT getLexType() {
    return lexType;
  }

  /**
   * Get {@link #lemma}.
   * @return {@link #lemma}
   */
  @Override
  public String getLemma() {
    return lemma;
  }

  /**
   * Get {@link #wordId}.
   * @return {@link #wordId}
   */
  @Override
  public IWordID getWordId() {
    return wordId;
  }

  /**
   * Get {@link #gloss}.
   * @return {@link #gloss}
   */
  @Override
  public String getGloss() {
    return gloss;
  }

  /**
   * Get {@link #number}.
   * @return {@link #number}
   */
  @Override
  public int getNum() {
    return number;
  }

  /**
   * Get {@link #relations}.
   * @return {@link #relations}
   */
  @Override
  public Map<PointerT, List<String>> getRelations() {
    return relations;
  }

  /**
   * Set {@link #wordId} to a new value provided in input.
   * @param wordId new value of {@link #wordId}
   */
  @Override
  public void setWordId(IWordID wordId) {
    this.wordId = wordId;
  }

  /**
   * Set {@link #pos} to a new value provided in input.
   * @param pos new value of {@link #pos}
   */
  @Override
  public void setPos(PosT pos) {
    this.pos = pos;
  }

  /**
   * Set {@link #lexType} to a new value provided in input.
   * @param lexType new value of {@link #lexType}
   */
  @Override
  public void setLexType(LexT lexType) {
    this.lexType = lexType;
  }

  /**
   * Set {@link #lemma} to a new value provided in input.
   * @param lemma new value of {@link #lemma}
   */
  @Override
  public void setLemma(String lemma) {
    this.lemma = lemma;
  }

  /**
   * Set {@link #gloss} to a new value provided in input.
   * @param gloss new value of {@link #gloss}
   */
  @Override
  public void setGloss(String gloss) {
    this.gloss = gloss;
  }

  /**
   * Set {@link #number} to a new value provided in input.
   * @param num new value of {@link #number}
   */
  @Override
  public void setNum(int num) {
    this.number = num;
  }

  /**
   * Add a new couple key-value to {@link #relations}.
   * @param p key of new element
   * @param value value of new element
   */
  @Override
  public void addRelation(PointerT p, List<String> value) {
    relations.put(p, value);
  }

  /**
   * Set {@link #relations} to a new value provided in input.
   * @param relations ne value of {@link #relations}
   */
  @Override
  public void setRelations(Map<PointerT, List<String>> relations) {
    this.relations = relations;
  }
}