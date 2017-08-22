package com.github.bot.curiosone.core.nlp.tokenizer.interfaces;

import com.github.bot.curiosone.core.nlp.tokenizer.LexT;
import com.github.bot.curiosone.core.nlp.tokenizer.PointerT;
import com.github.bot.curiosone.core.nlp.tokenizer.PosT;
import com.github.bot.curiosone.core.nlp.tokenizer.Word;

import edu.mit.jwi.item.IWordID;

import java.util.List;
import java.util.Map;

/**
 * Syntact/Semantic attribute of a word.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://projects.csail.mit.edu/jwi/api/index.html
 */
public interface IWord {

  /**
   * Get pos.
   *
   * @see Word#getPos()
   */

  PosT getPos();

  /**
   * Get lexType.
   *
   * @see Word#getLexType()
   */

  LexT getLexType();

  /**
   * Get lemma.
   *
   * @see Word#getLemma()
   */

  String getLemma();

  /**
   * Get WordID.
   *
   * @see IWordID()
   */

  IWordID getWordId();

  /**
   * Get gloss.
   *
   * @see Word#getGloss()
   */

  String getGloss();

  /**
   * Get number of occurence.
   *
   */

  int getNum();

  /**
   * Get relations.
   *
   * @see Word#getRelations()
   */

  Map<PointerT, List<String>> getRelations();

  /**
   * Set a new {@link #wordId} value that is provided in input.
   *
   * @see #wordId
   */

  public void setWordId(IWordID wordId);

  /**
   * Set a new {@link #pos} value that is provided in input.
   *
   * @see #pos
   */

  public void setPos(PosT pos);

  /**
   * Set a new {@link #lexType} value that is provided in input.
   *
   * @see #lexType
   */

  public void setLexType(LexT lexType);

  /**
   * Set a new {@link #lemma} value that is provided in input.
   *
   * @see #lemma
   */

  public void setLemma(String lemma);

  /**
   * Set a new {@link #gloss} value that is provided in input.
   *
   * @see #gloss
   */

  public void setGloss(String gloss);

  /**
   * Set the number of occurrence of word.
   *
   */
  public void setNum(int num);

  /**
   * Add a new element to relations.
   *
   * @see #relations
   */

  public void addRelation(PointerT p, String value);

  /**
   * Set the map of relations.
   *
   * @see #relations
   */

  public void setRelations(Map<PointerT, List<String>> relations);

}